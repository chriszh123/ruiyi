package com.ruoyi.fac.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.support.Convert;
import com.ruoyi.fac.domain.Buyer;
import com.ruoyi.fac.domain.Order;
import com.ruoyi.fac.domain.Product;
import com.ruoyi.fac.enums.OrderStatus;
import com.ruoyi.fac.enums.ProductStatus;
import com.ruoyi.fac.exception.FacException;
import com.ruoyi.fac.mapper.BusinessMapper;
import com.ruoyi.fac.mapper.BuyerMapper;
import com.ruoyi.fac.mapper.OrderMapper;
import com.ruoyi.fac.mapper.ProductMapper;
import com.ruoyi.fac.service.IOrderService;
import com.ruoyi.fac.util.DecimalUtils;
import com.ruoyi.fac.util.TimeUtils;
import com.ruoyi.fac.vo.FacStaticVo;
import com.ruoyi.fac.vo.OrderDiagramVo;
import com.ruoyi.fac.vo.OrderItemVo;
import com.ruoyi.fac.vo.QueryVo;
import com.ruoyi.fac.vo.client.*;
import com.ruoyi.fac.vo.condition.QueryGoodVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单 服务层实现
 *
 * @author ruoyi
 * @date 2018-12-24
 */
@Service
public class OrderServiceImpl implements IOrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BuyerMapper buyerMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询订单信息
     *
     * @param id 订单ID
     * @return 订单信息
     */
    @Override
    public Order selectOrderById(Long id) {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询订单列表
     *
     * @param order 订单信息
     * @return 订单集合
     */
    @Override
    public List<Order> selectOrderList(Order order) {
        order.setIsDeleted(0);
        List<Order> orders = orderMapper.selectOrderList(order);
        if (CollectionUtils.isNotEmpty(orders)) {
            for (Order item : orders) {
                item.setPrice(DecimalUtils.mul(item.getPrice(), new BigDecimal(item.getProdNumber())));
            }
        }
        return orders;
    }

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(String ids) {
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 取消订单
     *
     * @param ids
     * @return 结果
     */
    @Override
    public int cancelOrderByIds(String ids) {
        return this.orderMapper.cancelOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @Override
    public OrderItemVo detailOrderById(Long id) {
        OrderItemVo orderItemVo = new OrderItemVo();
        Order order = this.orderMapper.selectOrderById(id);
        if (order != null) {
            orderItemVo.setId(order.getId().toString());
            orderItemVo.setOrderNo(order.getOrderNo());
            orderItemVo.setUserName(order.getUserName());
            Buyer buyer = this.buyerMapper.selectBuyerById(order.getUserId());
            if (buyer != null) {
                // 取那个默认的收获地址里的电话号码:zgf
                orderItemVo.setPhoneNumber("");
            }
            orderItemVo.setRemark(order.getRemark());
            orderItemVo.setShipCode(order.getShipCode());
            orderItemVo.setRemarkMngt(order.getRemarkMngt());
            orderItemVo.setShip(order.getShip().toString());
        }
        return orderItemVo;
    }

    /**
     * 查询指定日期内的订单信息
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    @Override
    public OrderDiagramVo queryRecentOrderInfo(String startDateStr, String endDateStr) {
        OrderDiagramVo vo = new OrderDiagramVo();
        Date startDate = null, endDate = null;
        try {
            if (!StringUtils.isEmpty(startDateStr) && !StringUtils.isEmpty(endDateStr)) {
                startDate = TimeUtils.parseTime(startDateStr, TimeUtils.DEFAULT_DATE_FORMAT);
                endDate = TimeUtils.parseTime(endDateStr, TimeUtils.DEFAULT_DATE_FORMAT);
            } else {
                // 最近一周日期: 2019-01-04, end = 2019-01-11
                endDate = new Date();
                startDate = TimeUtils.getDateByHours(endDate, -168);
            }
            if (startDate == null || endDate == null) {
                return vo;
            }
            List<Date> dateList = TimeUtils.getStaticDates(startDate, endDate);
            String[] xAxisData = new String[dateList.size()];
            String[] seriesOrderCount = new String[dateList.size()];
            String[] seriesOrderAmount = new String[dateList.size()];
            for (int i = 0, size = dateList.size(); i < size; i++) {
                xAxisData[i] = TimeUtils.date2Str(dateList.get(i), "");
                seriesOrderCount[i] = "0";
                seriesOrderAmount[i] = "0.0";
            }
            vo.setxAxisData(xAxisData);
            vo.setSeriesOrderCount(seriesOrderCount);
            vo.setSeriesOrderAmount(seriesOrderAmount);
            // 当前时间范围内产生的订单
            QueryVo queryVo = new QueryVo();
            queryVo.setStartDate(startDate);
            queryVo.setEndDate(endDate);
            List<Order> orders = this.orderMapper.queryRecentOrderInfo(queryVo);
            if (CollectionUtils.isEmpty(orders)) {
                return vo;
            }
            Map<Date, Integer> date2OrderCount = new HashMap<>(16);
            Map<Date, BigDecimal> date2OrderAmount = new HashMap<>(16);
            Date date;
            int tempCount = 0;
            BigDecimal tempAmount = new BigDecimal("0.0");
            for (Order order : orders) {
                date = TimeUtils.parseTime(order.getCreateTime(), TimeUtils.DEFAULT_DATE_FORMAT);
                if (!date2OrderCount.containsKey(date)) {
                    date2OrderCount.put(date, 0);
                }
                tempCount = date2OrderCount.get(date);
                date2OrderCount.put(date, ++tempCount);
                if (!date2OrderAmount.containsKey(date)) {
                    date2OrderAmount.put(date, new BigDecimal("0.0"));
                }
                tempAmount = date2OrderAmount.get(date);
                tempAmount = tempAmount.add(order.getPrice());
                date2OrderAmount.put(date, tempAmount);
            }
            for (int i = 0, size = dateList.size(); i < size; i++) {
                seriesOrderCount[i] = date2OrderCount.containsKey(dateList.get(i)) ? date2OrderCount.get(dateList.get(i)).toString() : "0";
                seriesOrderAmount[i] = date2OrderAmount.containsKey(dateList.get(i)) ? date2OrderAmount.get(dateList.get(i)).toString() : "0.0";
            }
            vo.setxAxisData(xAxisData);
            vo.setSeriesOrderCount(seriesOrderCount);
            vo.setSeriesOrderAmount(seriesOrderAmount);
        } catch (Exception ex) {
            log.info("[queryRecentUserInfo] error", ex);
        }

        return vo;
    }

    /**
     * 指定日期内FAC的统计信息
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    @Override
    public FacStaticVo queryFacStaticInfo(String startDateStr, String endDateStr) {
        FacStaticVo vo = new FacStaticVo();
        Date startDate = null, endDate = null;
        if (!StringUtils.isEmpty(startDateStr)) {
            try {
                startDate = TimeUtils.parseTime(startDateStr, TimeUtils.DEFAULT_DATE_FORMAT);
            } catch (Exception ex) {
                log.info("[queryFacStaticInfo] error", ex);
            }
        }
        if (!StringUtils.isEmpty(endDateStr)) {
            try {
                endDate = TimeUtils.parseTime(endDateStr, TimeUtils.DEFAULT_DATE_FORMAT);
            } catch (Exception ex) {
                log.info("[queryFacStaticInfo] error", ex);
            }
        }
        // 统计项目
        String[] xAxis = new String[4];
        xAxis[0] = "订单";
        xAxis[1] = "用户";
        xAxis[2] = "商家";
        xAxis[3] = "商品";
        Integer[] seriesData = new Integer[4];
        // 1.当前订单数
        QueryVo queryVo = new QueryVo(startDate, endDate);
        int orderCount = this.orderMapper.countOrders(queryVo);
        seriesData[0] = orderCount;
        // 2.当前用户数
        int buyCount = this.buyerMapper.countBuyers(queryVo);
        seriesData[1] = buyCount;
        // 3.当前商家数
        int bizCount = this.businessMapper.countBusinesses(queryVo);
        seriesData[2] = bizCount;
        // 4.当前商品数
        int productCount = this.productMapper.countProducts(queryVo);
        seriesData[3] = productCount;

        vo.setStaticXAxis(xAxis);
        vo.setStaticData(seriesData);
        return vo;
    }

    /**
     * 用户客户端创建订单
     *
     * @param orderCreateVo
     */
    @Override
    public OrderCreateRes createOrderFromClient(OrderCreateVo orderCreateVo) throws FacException {
        OrderCreateRes res = new OrderCreateRes();
        if (StringUtils.isBlank(orderCreateVo.getGoodsJsonStr()) || StringUtils.isEmpty(orderCreateVo.getToken())) {
            return res;
        }
        List<GoodsJsonStrVo> goodsJsonStr = JSON.parseArray(orderCreateVo.getGoodsJsonStr(), GoodsJsonStrVo.class);
        // 当前用户信息
        Buyer buyer = this.buyerMapper.selectBuyerByOpenId(orderCreateVo.getToken());
        if (buyer == null) {
            return res;
        }
        List<Long> prodIds = new ArrayList<>();
        for (GoodsJsonStrVo good : goodsJsonStr) {
            prodIds.add(good.getGoodsId());
        }
        // 当前用户选择的商品
        QueryGoodVo vo = new QueryGoodVo();
        vo.setGoodIds(prodIds);
        vo.setPage(null);
        List<Product> products = this.productMapper.goodsList(vo);
        if (CollectionUtils.isEmpty(products)) {
            return res;
        }
        Map<Long, Product> productMap = new HashMap<>();
        // 下架的商品
        StringBuilder lowerProds = new StringBuilder();
        for (Product product : products) {
            // 下架的商品能创建订单
            if (product.getStatus().equals(ProductStatus.LOWER_SHELF)) {
                lowerProds.append(product.getName()).append(",");
            }
            productMap.put(product.getId(), product);
        }
        if (StringUtils.isNotBlank(lowerProds.toString())) {
            lowerProds = lowerProds.deleteCharAt(lowerProds.toString().length() - 1);
            throw new FacException("以下商品已下架，不能创建订单:\n" + lowerProds.toString());
        }
        // 转换用户选择的商品信息
        List<Order> orders = new ArrayList<>();
        Date nowDate = new Date();
        BigDecimal amountLogistics = new BigDecimal("0");
        // 当前订单单号
        String orderNo = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSSS");
        for (GoodsJsonStrVo good : goodsJsonStr) {
            Product product = productMap.get(good.getGoodsId());
            if (product == null) {
                continue;
            }
            Order order = new Order();
            orders.add(order);

            order.setOrderNo(orderNo);
            order.setProdId(good.getGoodsId());
            order.setProdName(product.getName());
            order.setProdNumber(good.getNumber());
            order.setPrice(product.getPrice());
            order.setStatus(OrderStatus.PAYING.getCode());
            order.setToken(orderCreateVo.getToken());
            order.setOpenId(orderCreateVo.getToken());
            order.setUserId(buyer.getId());
            order.setUserName(buyer.getName());
            order.setNickName(buyer.getNickName());
            order.setRemark(orderCreateVo.getRemark());
            order.setShip(2);
            order.setCreateTime(nowDate);
            order.setUpdateTime(nowDate);
            order.setOperatorId(buyer.getId());
            order.setOperatorName(buyer.getName());
            order.setIsDeleted(0);

            // 累积商品总价:暂时不考虑积分
            amountLogistics = DecimalUtils.add(amountLogistics,
                    DecimalUtils.mul(product.getPrice(), new BigDecimal(String.valueOf(good.getNumber()))));
        }
        // 批量保存用户选择的商品信息
        int goodsNumber = this.orderMapper.batchInsertOrders(orders);

        // 所有商品总价
        amountLogistics = DecimalUtils.formatDecimal(amountLogistics);
        // 创建返回结果
        res.setAmountLogistics(Double.valueOf(amountLogistics.toString()));
        res.setScore(0);
        res.setGoodsNumber(goodsNumber);
        res.setNeedLogistics(false);
        res.setAmountTotle(0);
        res.setDateAdd(TimeUtils.date2Str(nowDate, TimeUtils.DEFAULT_DATE_TIME_FORMAT_HH_MM_SS));
        res.setStatus(OrderStatus.PAYING.getCode());
        res.setStatusStr(OrderStatus.PAYING.getName());

        return res;
    }

    @Override
    public OrderStatisticsVo orderStatistics(String token) {
        OrderStatisticsVo vo = new OrderStatisticsVo();
        // 当前用户下的所有订单
        QueryVo queryVo = new QueryVo();
        queryVo.setToken(token);
        List<Order> orders = this.orderMapper.orderList(queryVo);
        if (CollectionUtils.isEmpty(orders)) {
            return vo;
        }
        // 1.待付款
        int count_id_no_pay = 0;
        for (Order item : orders) {
            if (item.getStatus() == null) {
                log.error("order status is null, orderId = " + item.getId());
                continue;
            }
            if (OrderStatus.PAYING.getCode().intValue() == item.getStatus().intValue()) {
                count_id_no_pay = count_id_no_pay + 1;
            }
        }
        vo.setCount_id_no_pay(count_id_no_pay);

        return vo;
    }

    /**
     * 客户端商品查询接口
     *
     * @param token
     * @param status
     * @return
     */
    @Override
    public OrderListVo orderList(String token, int status) {
        OrderListVo vo = new OrderListVo();
        if (StringUtils.isEmpty(token)) {
            return vo;
        }
        QueryVo queryVo = new QueryVo();
        queryVo.setToken(token);
        queryVo.setStatus(status);
        // 当前条件下所有商品
        List<Order> orders = this.orderMapper.orderList(queryVo);
        if (CollectionUtils.isEmpty(orders)) {
            return vo;
        }
        // 商品信息
        this.convertOrders(vo, orders);

        return vo;
    }

    /**
     * 取消订单
     *
     * @param token
     * @param orderIds
     */
    @Override
    public void closeOrder(String token, String orderIds) {
        QueryVo queryVo = new QueryVo();
        queryVo.setToken(token);
        queryVo.setOpenId(token);
        queryVo.setOrderIds(Convert.toLongArray(orderIds));
        queryVo.setStatus(OrderStatus.CACELED.getCode());
        this.orderMapper.updateOrderStatus(queryVo);
    }

    /**
     * 指定订单详情
     *
     * @param id    订单id
     * @param token 用户token
     * @return OrderDetailVo
     */
    @Override
    public OrderDetailVo orderDetail(long id, String token) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        Order order = this.orderMapper.selectOrderByIdAndToken(id, token);
        if (order == null) {
            return null;
        }
        // 1.当前订单信息
        OrderInfoVo orderInfo = new OrderInfoVo();
        orderDetailVo.setOrderInfo(orderInfo);
        // 商品金额
        orderInfo.setAmount(Double.valueOf(order.getPrice().toString()));
        // 应付总额
        BigDecimal orderTotalPrice = DecimalUtils.mul(order.getPrice(), new BigDecimal(String.valueOf(order.getProdNumber())));
        orderInfo.setAmountReal(Double.valueOf(orderTotalPrice.toString()));
        // 订单状态
        orderInfo.setStatus(order.getStatus().intValue());
        orderInfo.setStatusStr(OrderStatus.getNameByCode(order.getStatus()));

        // 2.订单商品信息:暂时一个订单就涉及一个商品
        List<GoodSpecification> goods = new ArrayList<>();
        orderDetailVo.setGoods(goods);
        GoodSpecification good = new GoodSpecification();
        goods.add(good);
        good.setGoodsName(order.getProdName());
        good.setNumber(order.getProdNumber());
        good.setAmount(Double.valueOf(order.getPrice().toString()));
        // 商品图片
        Product product = this.productMapper.selectProductById(order.getProdId());
        if (product != null && StringUtils.isNotBlank(product.getPicture())) {
            good.setPic(product.getPicture().split(",")[0]);
        }

        return orderDetailVo;
    }

    private void convertOrders(OrderListVo vo, List<Order> orders) {
        List<OrderVo> orderVos = new ArrayList<>();
        // <goodId, Order>
        Map<Long, Order> good2Order = new HashMap<>(16);
        BigDecimal orderTotalPrice;
        double amountReal;
        for (Order order : orders) {
            OrderVo orderVo = new OrderVo();
            orderVos.add(orderVo);
            orderVo.setDateAdd(TimeUtils.date2Str(order.getCreateTime(), TimeUtils.DEFAULT_DATE_TIME_FORMAT_HH_MM_SS));
            orderVo.setGoodsNumber(1);
            orderVo.setId(order.getId());
            orderVo.setPay(order.getPayTime() != null);
            orderVo.setOrderNumber(order.getOrderNo());
            orderVo.setRemark(order.getRemark());
            orderVo.setShopId(order.getShipId());
            orderVo.setStatus(order.getStatus());
            orderVo.setStatusStr(OrderStatus.getNameByCode(order.getStatus()));
            orderVo.setUserId(order.getUserId());
            // 当前订单的实际总价
            orderTotalPrice = DecimalUtils.mul(order.getPrice(), new BigDecimal(String.valueOf(order.getProdNumber())));
            amountReal = Double.valueOf(orderTotalPrice.toString());
            orderVo.setAmountReal(amountReal);

            good2Order.put(order.getProdId(), order);
        }
        // 订单信息
        vo.setOrderList(orderVos);
        // 商品信息
        List<Long> goodIds = new ArrayList<>(good2Order.keySet());
        Long[] ids = new Long[goodIds.size()];
        goodIds.toArray(ids);
        List<Product> products = this.productMapper.selectProductsByIds(ids);
        // 当前订单对应商品信息
        if (!CollectionUtils.isEmpty(products)) {
            this.convertProducts(vo, products, good2Order);
        }
    }

    private void convertProducts(OrderListVo vo, List<Product> products, Map<Long, Order> good2Order) {
        Map<String, List<GoodSpecification>> goodsMap = new HashMap();
        vo.setGoodsMap(goodsMap);
        List<GoodSpecification> goodInfos = null;
        for (Product product : products) {
            if (!good2Order.containsKey(product.getId())) {
                continue;
            }
            goodInfos = goodsMap.get(product.getId().toString());
            if (CollectionUtils.isEmpty(goodInfos)) {
                goodInfos = new ArrayList<>();
            }
            goodsMap.put(product.getId().toString(), goodInfos);

            GoodSpecification goodSpecification = new GoodSpecification();
            goodInfos.add(goodSpecification);
            goodSpecification.setGoodsId(product.getId());
            goodSpecification.setGoodsName(product.getName());
            // id值暂时同商品id
            goodSpecification.setId(product.getId());
            // 购买商品数量
            goodSpecification.setNumber(good2Order.get(product.getId()).getProdNumber());
            goodSpecification.setOrderId(good2Order.get(product.getId()).getId());
            goodSpecification.setPic(product.getPicture());
            goodSpecification.setUserId(good2Order.get(product.getId()).getUserId());
        }
    }


    public static void main(String[] args) {
        String orderNo = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSSS");
        System.out.println("orderNo = " + orderNo);
    }
}
