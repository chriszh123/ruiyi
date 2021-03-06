/**
 * Copyright (C) 2006-2019 zgf All rights reserved
 * Author：zhangguifeng
 * Date：2019/1/25
 * Description: OrderItemVo
 */
package com.ruoyi.fac.vo.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderItemVo
 *
 * @author zhangguifeng
 * @create 2019-01-25 17:35
 **/
public class OrderVo implements Serializable {
    private static final long serialVersionUID = -8112017472434362767L;

    private double amount = 0.00;
    private double amountLogistics = 0.00;
    // 订单实际总价
    private double amountReal = 0.00;
    private String dateAdd;
    private String dateClose;
    private int goodsNumber = 0;
    private boolean hasRefund = false;
    private long id;
    private boolean isCanHx = false;
    private boolean isNeedLogistics = false;
    private boolean isPay = false;
    private boolean isSuccessPingtuan = false;
    private String orderNumber;
    private String remark;
    private int score = 0;
    private long shopId;
    private int status = 0;
    private String statusStr;
    private int type;
    private long uid;
    private long userId;
    private long prodId;
    // 核销码
    private String writeOffCode;
    // 当前订单包含的商品信息
    private List<OrderProductVo> orderProducts = new ArrayList<>();

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountLogistics() {
        return amountLogistics;
    }

    public void setAmountLogistics(double amountLogistics) {
        this.amountLogistics = amountLogistics;
    }

    public double getAmountReal() {
        return amountReal;
    }

    public void setAmountReal(double amountReal) {
        this.amountReal = amountReal;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public boolean isHasRefund() {
        return hasRefund;
    }

    public void setHasRefund(boolean hasRefund) {
        this.hasRefund = hasRefund;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCanHx() {
        return isCanHx;
    }

    public void setCanHx(boolean canHx) {
        isCanHx = canHx;
    }

    public boolean isNeedLogistics() {
        return isNeedLogistics;
    }

    public void setNeedLogistics(boolean needLogistics) {
        isNeedLogistics = needLogistics;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public boolean isSuccessPingtuan() {
        return isSuccessPingtuan;
    }

    public void setSuccessPingtuan(boolean successPingtuan) {
        isSuccessPingtuan = successPingtuan;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getWriteOffCode() {
        return writeOffCode;
    }

    public void setWriteOffCode(String writeOffCode) {
        this.writeOffCode = writeOffCode;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public List<OrderProductVo> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductVo> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
