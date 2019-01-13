package com.ruoyi.fac.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.fac.domain.Buyer;
import com.ruoyi.fac.domain.Order;
import com.ruoyi.fac.mapper.BusinessMapper;
import com.ruoyi.fac.mapper.BuyerMapper;
import com.ruoyi.fac.mapper.OrderMapper;
import com.ruoyi.fac.mapper.ProductMapper;
import com.ruoyi.fac.service.IOrderService;
import com.ruoyi.fac.util.TimeUtils;
import com.ruoyi.fac.vo.FacStaticVo;
import com.ruoyi.fac.vo.OrderDiagramVo;
import com.ruoyi.fac.vo.OrderVo;
import com.ruoyi.fac.vo.QueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return orderMapper.selectOrderList(order);
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
    public OrderVo detailOrderById(Long id) {
        OrderVo orderVo = new OrderVo();
        Order order = this.orderMapper.selectOrderById(id);
        if (order != null) {
            orderVo.setId(order.getId().toString());
            orderVo.setOrderNo(order.getOrderNo());
            orderVo.setUserName(order.getUserName());
            Buyer buyer = this.buyerMapper.selectBuyerById(order.getUserId());
            if (buyer != null) {
                orderVo.setPhoneNumber(buyer.getPhoneNumber());
                orderVo.setHarvestAddress(buyer.getHarvestAddress());
            }
            orderVo.setRemark(order.getRemark());
            orderVo.setShipCode(order.getShipCode());
            orderVo.setRemarkMngt(order.getRemarkMngt());
            orderVo.setShip(order.getShip().toString());
        }
        return orderVo;
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

}