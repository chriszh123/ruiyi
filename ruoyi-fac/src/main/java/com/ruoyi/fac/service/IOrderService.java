package com.ruoyi.fac.service;

import com.ruoyi.fac.domain.Order;
import com.ruoyi.fac.vo.FacStaticVo;
import com.ruoyi.fac.vo.OrderDiagramVo;
import com.ruoyi.fac.vo.OrderItemVo;

import java.util.List;

/**
 * 订单 服务层
 *
 * @author ruoyi
 * @date 2018-12-24
 */
public interface IOrderService {
    /**
     * 查询订单信息
     *
     * @param id 订单ID
     * @return 订单信息
     */
    Order selectOrderById(Long id);

    /**
     * 查询订单列表
     *
     * @param order 订单信息
     * @return 订单集合
     */
    List<Order> selectOrderList(Order order);

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 结果
     */
    int insertOrder(Order order);

    /**
     * 修改订单
     *
     * @param order 订单信息
     * @return 结果
     */
    int updateOrder(Order order);

    /**
     * 删除订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOrderByIds(String ids);

    /**
     * 取消订单
     *
     * @param ids
     * @return 结果
     */
    int cancelOrderByIds(String ids);

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    OrderItemVo detailOrderById(Long id);

    /**
     * 查询指定日期内的订单信息
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    OrderDiagramVo queryRecentOrderInfo(String startDateStr, String endDateStr);

    /**
     * 指定日期内FAC的统计信息
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    FacStaticVo queryFacStaticInfo(String startDateStr, String endDateStr);
}
