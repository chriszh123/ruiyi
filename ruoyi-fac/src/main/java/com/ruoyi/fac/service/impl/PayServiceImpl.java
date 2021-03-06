package com.ruoyi.fac.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.Global;
import com.ruoyi.fac.constant.ErrorMsg;
import com.ruoyi.fac.constant.FacConstant;
import com.ruoyi.fac.domain.Buyer;
import com.ruoyi.fac.domain.FacProductWriteoff;
import com.ruoyi.fac.domain.Product;
import com.ruoyi.fac.enums.KanjiaStatus;
import com.ruoyi.fac.enums.OrderStatus;
import com.ruoyi.fac.enums.ScoreTypeEnum;
import com.ruoyi.fac.exception.FacException;
import com.ruoyi.fac.mapper.*;
import com.ruoyi.fac.model.*;
import com.ruoyi.fac.service.IPayService;
import com.ruoyi.fac.util.DecimalUtils;
import com.ruoyi.fac.util.FacCommonUtils;
import com.ruoyi.fac.util.TimeUtils;
import com.ruoyi.fac.vo.AppMchVo;
import com.ruoyi.fac.vo.wxpay.WxPrePayReq;
import com.ruoyi.fac.vo.wxpay.WxPrePayRes;
import com.ruoyi.fac.wxpay.PayCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zgf
 * https://www.cnblogs.com/yi1036943655/p/7211275.html  或者 https://www.cnblogs.com/yimiyan/p/5603657.html
 * 微信官方给的代码例子：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=11_1
 * Date 2019/2/18 17:16
 * 注意事项：
 * 　　1)、所有的签名和发送微信服务器的数据必须一致 包括Key的大小写 否则签名失败
 * 　　2)、微信小程序 前端调用 接口的时候 文档上并没有写appId参数 该参数一定要穿 并且是大写
 * 　　3)、交易类型 为 JSAPI 的时候 则必须传入openid
 * 　　4)、body格式问题 写的是UTF-8 实际要的格式则是ISO8859-1 而且单独对body进行设置好像不好使 所以必须全部都改成该格式
 * 　　5)、生成签名 最后加上key的那块 加的格式是 &key = KEY 这种 而且不是直接 + key 这个地方需要注意一下 我碰了个坑 文档没看仔细
 * 　　6)、数据包ID 格式 不是 value直接设置成 数据包ID就可以 前面需要加 "prepay_id="
 * 　　7)、最后一点强调 生成签名的数据和发送服务器的数据 必须保持一致
 * <p>
 * https://www.cnblogs.com/yimiyan/p/5603657.html
 */
@Slf4j
@Service("payService")
public class PayServiceImpl implements IPayService {
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BuyerMapper buyerMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private FacProductWriteoffMapper facProductWriteoffMapper;
    @Autowired
    private FacBuyerMapper facBuyerMapper;
    @Autowired
    private FacBuyerSignMapper facBuyerSignMapper;
    @Autowired
    private FacOrderMapper facOrderMapper;
    @Autowired
    private FacOrderProductMapper facOrderProductMapper;
    @Autowired
    private FacBuyerBusinessMapper facBuyerBusinessMapper;
    @Autowired
    private FacProductMapper facProductMapper;
    @Autowired
    private FacCashMapper facCashMapper;
    @Autowired
    private FacKanjiaMapper kanjiaMapper;
    @Autowired
    private FacKanjiaJoinerMapper kanjiaJoinerMapper;

    /**
     * 微信支付预支付接口
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WxPrePayRes getWxPrePayInfo(WxPrePayReq req, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info(String.format("====================[getWxPrePayInfo] start req:%s", JSON.toJSONString(req)));
        //设置最终返回对象
        final WxPrePayRes res = new WxPrePayRes();
        String openid = req.getToken();
        // id为订单号
        String orderNo = req.getNextAction().getId().trim();
        FacOrderExample orderExample = new FacOrderExample();
        orderExample.createCriteria().andIsDeletedEqualTo(false).andOrderNoEqualTo(orderNo);
        List<FacOrder> orders = this.facOrderMapper.selectByExample(orderExample);
        if (CollectionUtils.isEmpty(orders)) {
            throw new Exception(ErrorMsg.ORDER_NOT_EXIST);
        }
        if (!OrderStatus.PAYING.getCode().equals(Integer.valueOf(orders.get(0).getStatus()))) {
            throw new Exception(ErrorMsg.ORDER_STATUS_NOT_WRITEODDING);
        }
        FacOrder order = orders.get(0);
        Date nowDate = new Date();
        // 存在砍价活动场景
        FacKanjia kanjia = null;
        if (order.getKanjiaId() != null && !order.getKanjiaId().equals(0L)) {
            final FacKanjiaExample kanjiaExample = new FacKanjiaExample();
            kanjiaExample.createCriteria().andIsDeletedEqualTo(false).andIdEqualTo(order.getKanjiaId());
            List<FacKanjia> kanjias = this.kanjiaMapper.selectByExample(kanjiaExample);
            if (org.apache.commons.collections4.CollectionUtils.isEmpty(kanjias)) {
                log.info(String.format("-----------getWxPrePayInfo: kanjia set is not exist, token:%s, kanjiaId:%s", order.getToken()
                        , order.getKanjiaId()));
                throw new FacException("当前砍价活动信息不存在，请联系管理员");
            }
            kanjia = kanjias.get(0);
            if (nowDate.compareTo(kanjia.getStartDate()) < 0) {
                throw new FacException("砍价活动暂未开始");
            }
            if (nowDate.compareTo(kanjia.getStopDate()) > 0) {
                throw new FacException("砍价活动已结束");
            }

            // 当前砍价活动，一个用户只能参与一次
            final FacOrderExample kjOrderExample = new FacOrderExample();
            kjOrderExample.createCriteria().andIsDeletedEqualTo(false).andTokenEqualTo(order.getToken()).andKanjiaIdEqualTo(kanjia.getId());
            List<FacOrder> kjOrders = this.facOrderMapper.selectByExample(kjOrderExample);
            if (CollectionUtils.isEmpty(kjOrders)) {
                throw new FacException("当前商品砍价活动中没有您的参与信息，请联系管理员");
            }
            if (kjOrders.size() != 1) {
                throw new FacException("砍价活动您已经参与过了哦，请把机会留给别人吧~~~");
            }
        }

        FacOrderProductExample orderProductExample = new FacOrderProductExample();
        orderProductExample.createCriteria().andIsDeletedEqualTo(false).andOrderNoEqualTo(orderNo);
        List<FacOrderProduct> orderProducts = this.facOrderProductMapper.selectByExample(orderProductExample);
        // 如果当前订单中出现多个不同商品，需要校验这些商品必须是来自同一个卖家名下的商品
        if (CollectionUtils.isNotEmpty(orderProducts) && orderProducts.size() >= 2) {
            List<Long> prodIds = new ArrayList<>();
            for (FacOrderProduct item : orderProducts) {
                prodIds.add(item.getProdId());
            }
            FacBuyerBusinessExample buyerBusinessExample = new FacBuyerBusinessExample();
            buyerBusinessExample.createCriteria().andIsDeletedEqualTo(false).andBusinessProdIdIn(prodIds);
            List<FacBuyerBusiness> buyerBusinesses = this.facBuyerBusinessMapper.selectByExample(buyerBusinessExample);
            if (CollectionUtils.isNotEmpty(buyerBusinesses)) {
                // <businessId, [prodId]>
                Map<Long, List<Long>> biz2ProdIds = new HashMap<>();
                for (FacBuyerBusiness item : buyerBusinesses) {
                    if (!biz2ProdIds.containsKey(item.getBusinessId())) {
                        biz2ProdIds.put(item.getBusinessId(), new ArrayList<>());
                    }
                    biz2ProdIds.get(item.getBusinessId()).add(item.getBusinessProdId());
                }
                if (biz2ProdIds.size() != 1) {
                    throw new FacException(ErrorMsg.ORDER_PRODUCT_NOT_IN_SAME_BUSINESS);
                }
                for (Map.Entry<Long, List<Long>> entry : biz2ProdIds.entrySet()) {
                    List<Long> prodId2Business = entry.getValue();
                    for (Long prodId : prodIds) {
                        if (!prodId2Business.contains(prodId)) {
                            // 用户选择的商品存在于不同的卖家中，即
                            throw new FacException("当前订单中的商品在不同卖家中，不能创建订单");
                        }
                    }
                }
            } else {
                throw new FacException("当前商品暂未绑定商家，请联系管理员");
            }
        }

        // 当前订单总消费金额(后台计算出来的)
        BigDecimal totalConsumeAmout = this.getTotalConsumAmout(orderProducts);
        int userScore = orders.get(0).getUserScore();
        if (userScore > 0) {
            // 如果用户使用了积分，预支付金额需要扣除相应积分数对应的金额：一个积分一分钱
            totalConsumeAmout = DecimalUtils.subtract(totalConsumeAmout, DecimalUtils.division(userScore, 100));
        }
        totalConsumeAmout = DecimalUtils.formatDecimal(totalConsumeAmout);
        logger.info(String.format("-------------[getWxPrePayInfo] orderNo:%s, totalConsumeAmout:%s, userScore:%s, money/paramter:%s"
                , orderNo, totalConsumeAmout, userScore, req.getMoney()));

        // 校验订单总金额 客户端传给后端的和后端自己统计出来的金额是否一致
        if (totalConsumeAmout.compareTo(new BigDecimal(req.getMoney())) != 0) {
            log.info(String.format("-----------------[getWxPrePayInfo] consumeAmout has error, money/paramter:%s, backStatic:%s", req.getMoney()
                    , totalConsumeAmout.toString()));
            throw new Exception("当前订单商品总额与实际消费不一致，请核实");
        }
        // 校验当前商品是否还可以购买:库存数据
        Long[] ids = new Long[orderProducts.size()];
        // <prodId, FacOrderProduct>
        Map<Long, FacOrderProduct> prod2Order = new HashMap<>(orderProducts.size());
        for (int i = 0, size = orderProducts.size(); i < size; i++) {
            ids[i] = orderProducts.get(i).getProdId();
            prod2Order.put(orderProducts.get(i).getProdId(), orderProducts.get(i));
        }
        List<Product> products = this.productMapper.selectProductsByIds(ids);
        for (Product item : products) {
            if (item == null || item.getIsDeleted() == 1) {
                throw new Exception(String.format("商品【%s】已被删除，请选择其它商品购买", item.getName()));
            }
            if (kanjia != null) {
                int total = kanjia.getTotal();
                int sales = kanjia.getSales();
                int left = total - sales;
                if (left < prod2Order.get(item.getId()).getProdNumber()) {
                    throw new FacException(String.format("商品【%s】库存数量已不足，请选择其它商品购买", item.getName()));
                }
            } else if (prod2Order.containsKey(item.getId()) && item.getInventoryQuantity() < prod2Order.get(item.getId()).getProdNumber()) {
                throw new Exception(String.format("商品【%s】库存数量已不足，请选择其它商品购买", item.getName()));
            }
            if (item.getStatus().intValue() == 2) {
                throw new Exception(String.format("商品【%s】已下架，请选择其它商品购买", item.getName()));
            }
            if (kanjia == null && prod2Order.get(item.getId()).getProdNumber() > item.getLimitQuantity()) {
                throw new FacException(String.format("商品【%s】每人限购%s份", item.getName(), item.getLimitQuantity()));
            }
            if (kanjia == null && nowDate.compareTo(item.getRushEnd()) > 0) {
                throw new Exception(String.format("商品【%s】抢购时间已结束，请选择其它商品购买", item.getName()));
            }
        }
        // 设置商户订单号
        String outTradeNo = orderNo;
        try {
            // 测试金额:1分钱
//            BigDecimal totalAmount = new BigDecimal("0.01");
            BigDecimal totalAmount = totalConsumeAmout;
            AppMchVo appMchVo = new AppMchVo();
            appMchVo.setAppId(Global.getFacAppId());
            appMchVo.setMchId(Global.getFacMchId());
            appMchVo.setApiKey(Global.getFacMchSecret());
            String body = "JB FAC";
            String notifyUrl = Global.getDomain() + "/fac/client/pay/wx/payCallback";
            SortedMap<String, Object> preResult = PayCommonUtil.WxPublicPay(outTradeNo, totalAmount, body, "prepayfac", openid, notifyUrl, appMchVo, request);
            if (MapUtils.isNotEmpty(preResult)) {
                logger.info(String.format("[getWxPrePayInfo] orderNo:%s, preResult:%s", orderNo, JSON.toJSONString(preResult)));
                res.setTimeStamp(String.valueOf(preResult.get("timeStamp")));
                res.setNonceStr(String.valueOf(preResult.get("nonceStr")));
                res.setPrepayId(String.valueOf(preResult.get("package")));
                res.setSign(String.valueOf(preResult.get("paySign")));
                logger.info(String.format("=========================[getWxPrePayInfo] success, orderNo:%s,result:%s============================"
                        , orderNo, JSON.toJSONString(res)));
                return res;
            } else {
                throw new Exception("预支付接口响应数据为空");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new Exception(ex.getMessage(), ex);
        }
    }

    /**
     * 微信支付结果通知接口
     *
     * @param request
     * @param response
     */
    @Override
    public String payCallback(HttpServletRequest request, HttpServletResponse response) {
        logger.info("----------------------------payCallback:微信回调接口 操作逻辑 start");
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String resultxml = new String(outSteam.toByteArray(), "utf-8");
            logger.info(String.format("------------------------------------[payCallback] resultxml:%s", resultxml));
            Map<String, String> params = null;
            try {
                params = PayCommonUtil.doXMLParse(resultxml);
            } catch (JDOMException e) {
                logger.error("-----------------------[payCallback] JDOMException:" + e.getMessage(), e);
            }
            outSteam.close();
            inStream.close();
            if (!PayCommonUtil.isTenpaySign(params)) {
                logger.info("===========payCallback====付款失败==============");
                // 支付失败
                return "fail";
            } else {
                logger.info("==========payCallback=====付款成功==============");
                // ------------------------------
                // 处理业务开始
                logger.info("=================微信回调返回是否支付成功：是, " + JSON.toJSONString(params));
                //获得 返回的商户订单号
                String outTradeNo = params.get("out_trade_no");
                logger.info("===========微信回调返回商户订单号：" + outTradeNo);
                //访问DB
                FacOrderExample orderExample = new FacOrderExample();
                orderExample.createCriteria().andIsDeletedEqualTo(false).andOrderNoEqualTo(outTradeNo);
                List<FacOrder> orders = this.facOrderMapper.selectByExample(orderExample);
                if (CollectionUtils.isNotEmpty(orders)) {
                    logger.info("===========微信回调 根据订单号查询订单状态：" + orders.get(0).getStatus());
                    if (OrderStatus.PAYING.getCode().equals(Integer.valueOf(orders.get(0).getStatus()))) {
                        //修改支付状态:订单支付成功后状态变为待核销状态、支付时间
                        int sqlRow = this.orderMapper.updateOrderStatusAfterPayed(outTradeNo, OrderStatus.TOWRITEOFF.getCode().intValue());
                        logger.info(String.format("---------------updateOrderStatusAfterPayed sqlRow:%s, orderNo:%s", sqlRow, outTradeNo));
                        if (sqlRow >= 1) {
                            // 支付成功后，处理当前订单、商品、用户相关信息
                            this.dealOrderAndProdDataAfterPayed(orders);
                            logger.info("微信回调  订单号：" + outTradeNo + ",修改状态成功");
                        }
                    }
                }
                logger.info("==============微信回调接口 操作逻辑 success, at:" + TimeUtils.getCurrentTimeSSS());
                // 处理业务完毕

                return "success";
            }
        } catch (Exception ex) {
            logger.error("=========payCallback======付款异常==============" + ex.getMessage(), ex);
        }

        return "fail";
    }

    @Transactional(rollbackFor = Exception.class)
    public void dealOrderAndProdDataAfterPayed(List<FacOrder> orders) {
        logger.info(String.format("[dealOrderAndProdDataAfterPayed] orders:%s"
                , CollectionUtils.isNotEmpty(orders) ? JSON.toJSONString(orders) : "has no order."));
        // 支付成功后，处理当前订单、商品、用户相关信息
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        String orderNo = orders.get(0).getOrderNo();
        FacOrderProductExample orderProductExample = new FacOrderProductExample();
        orderProductExample.createCriteria().andIsDeletedEqualTo(false).andOrderNoEqualTo(orderNo);
        List<FacOrderProduct> orderProducts = this.facOrderProductMapper.selectByExample(orderProductExample);
        if (CollectionUtils.isEmpty(orderProducts)) {
            return;
        }
        // 批量更新当前商品对应的销售数量
        // <inviterId, [prodId]>
        Map<Long, List<Long>> inviterId2ProdId = new HashMap<>();
        List<Product> products = new ArrayList<>();
        // 是被分享的商品ids
        List<Long> ids = new ArrayList<>();
        BigDecimal totalAmount = DecimalUtils.getDefaultDecimal();
        // 商品ids
        List<Long> prodIds = new ArrayList<>();
        for (FacOrderProduct orderProduct : orderProducts) {
            prodIds.add(orderProduct.getProdId());
            Product product = new Product();
            product.setId(orderProduct.getProdId());
            product.setSales(Integer.valueOf(orderProduct.getProdNumber()));
            products.add(product);
            // 商品是通过别人分享购买的
            if (orderProduct.getInviterId() != null) {
                if (!inviterId2ProdId.containsKey(orderProduct.getInviterId())) {
                    inviterId2ProdId.put(orderProduct.getInviterId(), new ArrayList<>());
                }
                inviterId2ProdId.get(orderProduct.getInviterId()).add(orderProduct.getProdId());
                ids.add(orderProduct.getProdId());
            }
            // 当前订单对应的商品总金额
            totalAmount = DecimalUtils.add(totalAmount, DecimalUtils.mul(orderProduct.getPrice(), new BigDecimal(orderProduct.getProdNumber().toString())));
        }
        FacProductExample productExample = new FacProductExample();
        productExample.createCriteria().andIsDeletedEqualTo(false).andIdIn(prodIds);
        List<FacProduct> facProducts = this.facProductMapper.selectByExample(productExample);
        if (CollectionUtils.isNotEmpty(facProducts)) {
            for (FacProduct item : facProducts) {
                for (FacOrderProduct orderProduct : orderProducts) {
                    if (item.getId().equals(orderProduct.getProdId())) {
                        FacProduct product = new FacProduct();
                        // 销量
                        int oldSales = item.getSales() != null ? Integer.valueOf(item.getSales()) : 0;
                        int sales = oldSales + Integer.valueOf(orderProduct.getProdNumber());
                        product.setSales(Short.valueOf(String.valueOf(sales)));
                        // 订单数量
                        int oldOrderCount = item.getOrderCount() != null ? Integer.valueOf(item.getOrderCount()) : 0;
                        int orderCount = oldOrderCount + 1;
                        product.setOrderCount(Short.valueOf(String.valueOf(orderCount)));
                        // 库存数量
                        int oldInventoryQuantity = item.getInventoryQuantity() != null ? Integer.valueOf(item.getInventoryQuantity()) : 0;
                        int inventoryQuantity = oldInventoryQuantity - Integer.valueOf(orderProduct.getProdNumber());
                        product.setInventoryQuantity(Short.valueOf(String.valueOf(inventoryQuantity)));
                        // 更新
                        productExample = new FacProductExample();
                        productExample.createCriteria().andIsDeletedEqualTo(false).andIdEqualTo(orderProduct.getProdId());
                        int result = this.facProductMapper.updateByExampleSelective(product, productExample);
                        logger.info("---------------------[dealOrderAndProdDataAfterPayed] updateProductSales success,result:%s, orderNo:%s, product:%s"
                                , result, orderNo, JSON.toJSONString(product));
                    }
                }
            }
        }
        // 给分享人加积分/佣金啥的
        if (MapUtils.isNotEmpty(inviterId2ProdId)) {
            Long[] idsArr = new Long[ids.size()];
            idsArr = ids.toArray(idsArr);
            List<Product> productList = this.productMapper.selectProductsByIds(idsArr);
            if (CollectionUtils.isNotEmpty(productList)) {
                Date nowDate = new Date();
                Map<Long, Product> productMap = new HashMap<>(productList.size());
                for (Product item : productList) {
                    productMap.put(item.getId(), item);
                }
                // <inviterId, [prodId]>
                FacBuyerExample buyerExample = new FacBuyerExample();
                buyerExample.createCriteria().andIsDeletedEqualTo(false).andIdIn(new ArrayList<>(inviterId2ProdId.keySet()));
                List<FacBuyer> facBuyers = this.facBuyerMapper.selectByExample(buyerExample);
                Map<Long, FacBuyer> id2Buyer = new HashMap<>();
                if (CollectionUtils.isNotEmpty(facBuyers)) {
                    for (FacBuyer buyer : facBuyers) {
                        id2Buyer.put(buyer.getId(), buyer);
                    }
                }
                for (Map.Entry<Long, List<Long>> entry : inviterId2ProdId.entrySet()) {
                    FacBuyer buyer = new FacBuyer();
                    // 积分
                    FacBuyer oldBuyer = id2Buyer.get(entry.getKey());
                    if (oldBuyer != null) {
                        int oldPoint = oldBuyer.getPoints() != null ? Integer.valueOf(oldBuyer.getPoints()) : 0;
                        int newPoints = oldPoint;
                        BigDecimal oldBalance = oldBuyer.getBalance() != null ? oldBuyer.getBalance() : DecimalUtils.getDefaultDecimal();
                        BigDecimal balance = oldBalance;
                        for (Long prodId : entry.getValue()) {
                            if (productMap.containsKey(prodId)) {
                                newPoints = newPoints
                                        + (productMap.get(prodId).getBonusPoints() != null ? productMap.get(prodId).getBonusPoints().intValue() : 0);
                                balance = DecimalUtils.add(balance
                                        , (productMap.get(prodId).getDistribution() != null ? productMap.get(prodId).getDistribution() : DecimalUtils.getDefaultDecimal()));
                            }
                        }
                        buyer.setPoints(Short.valueOf(String.valueOf(newPoints)));
                        buyer.setBalance(balance);
                        buyer.setUpdateTime(nowDate);
                        buyerExample = new FacBuyerExample();
                        buyerExample.createCriteria().andIsDeletedEqualTo(false).andIdEqualTo(entry.getKey());
                        int result = this.facBuyerMapper.updateByExampleSelective(buyer, buyerExample);
                        logger.info(String.format("---------------------[dealOrderAndProdDataAfterPayed] update point and balance,result:%s, orderNo:%s, buyerId:%s, point:%s, balance:%s"
                                , result, orderNo, entry.getKey(), newPoints, balance));
                        // 给分享人增加一条奖励积分或者分享金的记录
                        // 积分、分享金有改变
                        int changePoint = newPoints - oldPoint;
                        BigDecimal changeBalance = DecimalUtils.subtract(balance, oldBalance);
                        this.insertBuyerSign(oldBuyer, changePoint, changeBalance, nowDate);
                    }
                }
            }
        }

        // 用户付款成功后订单涉及到的每个商品再创建一条对应的待核销记录数据
        Date nowDate = new Date();
        FacProductWriteoff productWriteoff;
        List<FacProductWriteoff> productWriteoffs = new ArrayList<>();
        String writeOffCode;
        for (FacOrderProduct item : orderProducts) {
            // 核销码动态随机生成:8位整数随机码
            writeOffCode = FacCommonUtils.randomInt(FacConstant.PRODUCT_WRITEOFF_CODE_LENGTH);
            productWriteoff = new FacProductWriteoff();
            productWriteoff.setOrderNo(item.getOrderNo());
            productWriteoff.setProductId(item.getProdId());
            productWriteoff.setBuyerId(item.getUserId());
            productWriteoff.setCode(writeOffCode);
            // 核销状态:1-已核销,2-待核销
            productWriteoff.setStatus(2);
            productWriteoff.setWriteoffTime(null);
            productWriteoff.setCreateTime(nowDate);
            productWriteoff.setUpdateTime(nowDate);
            productWriteoff.setOperatorId(item.getUserId());
            productWriteoff.setOperatorName(item.getUserName());
            productWriteoff.setIsDeleted(0);
            productWriteoffs.add(productWriteoff);
        }
        this.facProductWriteoffMapper.batchInsert(productWriteoffs);
        logger.info(String.format("---------------------[dealOrderAndProdDataAfterPayed] batchInsert success.---------%s"
                , JSON.toJSONString(productWriteoffs)));

        Buyer buyer = this.buyerMapper.selectBuyerByOpenId(orders.get(0).getToken());
        // 如果当前订单使用了积分抵扣，需要扣除当前用户相应的消费积分数,并且记录消费记录
        int useScore = orders.get(0).getUserScore();
        if (useScore > 0) {
            if (buyer != null) {
                FacBuyer facBuyer = new FacBuyer();
                int remainderPoints = buyer.getPoints() - useScore;
                facBuyer.setPoints(Short.valueOf(String.valueOf(remainderPoints)));
                facBuyer.setUpdateTime(nowDate);
                FacBuyerExample buyerExample = new FacBuyerExample();
                buyerExample.createCriteria().andIsDeletedEqualTo(false).andTokenEqualTo(orders.get(0).getToken());
                this.facBuyerMapper.updateByExampleSelective(facBuyer, buyerExample);
                logger.info(String.format("==========update buyer point after consume:buyer:%s,data:%s==========", JSON.toJSON(buyer), JSON.toJSON(facBuyer)));
                // 增加消费记录
                FacBuyerSign record = new FacBuyerSign();
                record.setToken(buyer.getToken());
                record.setNickName(buyer.getNickName());
                // 积分类型：0-签到;1-购物反赠积分,2-购物消费,3-用户每次订单实际消费金额
                record.setType(ScoreTypeEnum.COUNSUMER.getValue());
                record.setPoint(Short.valueOf(String.valueOf(useScore)));
                record.setSignTime(nowDate);
                record.setCreateTime(nowDate);
                record.setUpdateTime(nowDate);
                record.setIsDeleted(false);
                this.facBuyerSignMapper.insertSelective(record);
                logger.info(String.format("============add consume record:%s===============", JSON.toJSONString(record)));
            } else {
                logger.info(String.format("==================current user is not exist, token:%s================", orders.get(0).getToken()));
            }
            // 实际消费金额记录
            totalAmount = DecimalUtils.subtract(totalAmount, DecimalUtils.division(useScore, 100));
        }
        // 记录消费金额记录
        if (buyer != null) {
            FacBuyerSign record = new FacBuyerSign();
            record.setToken(buyer.getToken());
            record.setNickName(buyer.getNickName());
            // 积分类型：0-签到;1-购物反赠积分,2-购物消费,3-用户每次订单实际消费金额
            record.setType(ScoreTypeEnum.COUNSUMER_AMOUNT.getValue());
            record.setMount(totalAmount);
            record.setSignTime(nowDate);
            record.setCreateTime(nowDate);
            record.setUpdateTime(nowDate);
            record.setIsDeleted(false);
            this.facBuyerSignMapper.insertSelective(record);
            logger.info(String.format("---------------add counsumer amount success, token:%s, orderNo:%s, totalAmount:%s",
                    buyer.getToken(), orderNo, totalAmount));
        }

        // 如果当前订单对应的商品是砍价活动的商品需要更新砍价那边对应的相关数据
        FacOrder order = orders.get(0);
        if (order.getKanjiaId() != null && !order.getKanjiaId().equals(0L)) {
            // 更新fac_kanjia表立商品销量
            final FacKanjiaExample kanjiaExample = new FacKanjiaExample();
            kanjiaExample.createCriteria().andIsDeletedEqualTo(false).andIdEqualTo(order.getKanjiaId());
            List<FacKanjia> kanjias = this.kanjiaMapper.selectByExample(kanjiaExample);
            if (CollectionUtils.isNotEmpty(kanjias)) {
                FacKanjia kanjia = kanjias.get(0);
                short sales = kanjia.getSales();
                sales = (short) (sales + orderProducts.get(0).getProdNumber());
                kanjia.setSales(sales);
                kanjia.setUpdateTime(nowDate);
                kanjia.setOperatorId(buyer.getOperatorId());
                kanjia.setOperatorName(buyer.getOperatorName());
                int rows = this.kanjiaMapper.updateByExampleSelective(kanjia, kanjiaExample);
                log.info(String.format("--------------update kanjia sales, kanjiaId:%s, rows:%s", order.getKanjiaId(), rows));

                // 更新参与用户相应砍价状态
                final FacKanjiaJoinerExample kanjiaJoinerExample = new FacKanjiaJoinerExample();
                kanjiaJoinerExample.createCriteria().andIsDeletedEqualTo(false).andKanjiaIdEqualTo(order.getKanjiaId())
                        .andTokenEqualTo(order.getToken());
                final FacKanjiaJoiner kanjiaJoiner = new FacKanjiaJoiner();
                kanjiaJoiner.setStatus(KanjiaStatus.COMPLETED.getValue());
                kanjiaJoiner.setUpdateTime(nowDate);
                kanjiaJoiner.setOperatorId(buyer.getOperatorId());
                kanjiaJoiner.setOperatorName(buyer.getOperatorName());
                this.kanjiaJoinerMapper.updateByExampleSelective(kanjiaJoiner, kanjiaJoinerExample);
                log.info(String.format("--------------update kanjia joner status, kanjiaId:%s, token:%s, rows:%s", order.getKanjiaId()
                        , order.getToken(), rows));
            } else {
                log.info(String.format("-----------update kanjia, has no kanjia data,kanjiaId:%s", order.getKanjiaId()));
            }
        }
    }

    private void insertBuyerSign(FacBuyer buyer, int points, BigDecimal balance, Date nowDate) {
        FacBuyerSign facBuyerSign = new FacBuyerSign();
        if (points != 0) {
            facBuyerSign.setToken(buyer.getToken());
            facBuyerSign.setNickName(buyer.getNickName());
            facBuyerSign.setType(ScoreTypeEnum.INVITER_POINT.getValue());
            facBuyerSign.setPoint(Short.valueOf(String.valueOf(points)));
            facBuyerSign.setSignTime(nowDate);
            facBuyerSign.setCreateTime(nowDate);
            facBuyerSign.setUpdateTime(nowDate);
            facBuyerSign.setIsDeleted(false);
            int row = this.facBuyerSignMapper.insertSelective(facBuyerSign);
            logger.info(String.format("-----------------------[insertBuyerSign4 inviter] point result:%s, buyerId:%s,points:%s"
                    , row, buyer.getId(), facBuyerSign.getPoint()));
        }
        if (balance.compareTo(DecimalUtils.getDefaultDecimal()) != 0) {
            facBuyerSign.setToken(buyer.getToken());
            facBuyerSign.setNickName(buyer.getNickName());
            facBuyerSign.setType(ScoreTypeEnum.INVITER_BALANCE.getValue());
            facBuyerSign.setMount(balance);
            facBuyerSign.setSignTime(nowDate);
            facBuyerSign.setCreateTime(nowDate);
            facBuyerSign.setUpdateTime(nowDate);
            facBuyerSign.setIsDeleted(false);
            int row = this.facBuyerSignMapper.insertSelective(facBuyerSign);
            logger.info(String.format("-----------------------[insertBuyerSign4 inviter] balance result:%s, buyerId:%s,balance:%s"
                    , row, buyer.getId(), facBuyerSign.getMount()));
            // 这个场景下需要往提现表里增加一条待处理的提现记录
            FacCash facCash = new FacCash();
            facCash.setUserId(buyer.getId());
            facCash.setCash(balance);
            facCash.setReceivingAccount("");
            facCash.setPhoneNumber("");
            facCash.setApplyTime(nowDate);
            facCash.setStatus(new Byte("1"));
            facCash.setCreateTime(nowDate);
            facCash.setUpdateTime(nowDate);
            facCash.setOperatorId(-1L);
            facCash.setOperatorName("default");
            facCash.setIsDeleted(false);
            row = this.facCashMapper.insertSelective(facCash);
            logger.info(String.format("-----------------------[insertBuyerSign4 inviter] cash result:%s, buyerId:%s,cash:%s"
                    , row, buyer.getId(), facBuyerSign.getMount()));
        }
    }

    private BigDecimal getTotalConsumAmout(List<FacOrderProduct> orderProducts) throws Exception {
        if (CollectionUtils.isEmpty(orderProducts)) {
            throw new Exception("当前订单下没有商品信息，请联系管理员");
        }

        BigDecimal total = DecimalUtils.getDefaultDecimal();
        for (FacOrderProduct orderProduct : orderProducts) {
            total = DecimalUtils.add(total, DecimalUtils.mul(orderProduct.getPrice(), new BigDecimal(String.valueOf(orderProduct.getProdNumber()))));
        }
        return total;
    }

    public static void main(String[] args) {
        BigDecimal total = new BigDecimal(38);
        float ddd = (float) 4 / 100;
        BigDecimal data = new BigDecimal(ddd);
        total = total.subtract(data);
        System.out.println(total);
    }
}
