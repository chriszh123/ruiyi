package com.ruoyi.fac.model;

import java.util.Date;

public class FacOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.order_no
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.prod_ids
     *
     * @mbggenerated
     */
    private String prodIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.token
     *
     * @mbggenerated
     */
    private String token;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.open_id
     *
     * @mbggenerated
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.nick_name
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.user_score
     *
     * @mbggenerated
     */
    private Short userScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.remark_mngt
     *
     * @mbggenerated
     */
    private String remarkMngt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.pay_time
     *
     * @mbggenerated
     */
    private Date payTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.writeoff_time
     *
     * @mbggenerated
     */
    private Date writeoffTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.ship
     *
     * @mbggenerated
     */
    private Byte ship;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.ship_id
     *
     * @mbggenerated
     */
    private Long shipId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.ship_code
     *
     * @mbggenerated
     */
    private String shipCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.cacel_id
     *
     * @mbggenerated
     */
    private Long cacelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.cacel_name
     *
     * @mbggenerated
     */
    private String cacelName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.cacel_time
     *
     * @mbggenerated
     */
    private Date cacelTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.prepay_id
     *
     * @mbggenerated
     */
    private Long prepayId;

    private Long kanjiaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.operator_id
     *
     * @mbggenerated
     */
    private Long operatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.operator_name
     *
     * @mbggenerated
     */
    private String operatorName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fac_order.is_deleted
     *
     * @mbggenerated
     */
    private Boolean isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.id
     *
     * @return the value of fac_order.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.id
     *
     * @param id the value for fac_order.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.order_no
     *
     * @return the value of fac_order.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.order_no
     *
     * @param orderNo the value for fac_order.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.prod_ids
     *
     * @return the value of fac_order.prod_ids
     *
     * @mbggenerated
     */
    public String getProdIds() {
        return prodIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.prod_ids
     *
     * @param prodIds the value for fac_order.prod_ids
     *
     * @mbggenerated
     */
    public void setProdIds(String prodIds) {
        this.prodIds = prodIds == null ? null : prodIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.status
     *
     * @return the value of fac_order.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.status
     *
     * @param status the value for fac_order.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.token
     *
     * @return the value of fac_order.token
     *
     * @mbggenerated
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.token
     *
     * @param token the value for fac_order.token
     *
     * @mbggenerated
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.open_id
     *
     * @return the value of fac_order.open_id
     *
     * @mbggenerated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.open_id
     *
     * @param openId the value for fac_order.open_id
     *
     * @mbggenerated
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.user_id
     *
     * @return the value of fac_order.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.user_id
     *
     * @param userId the value for fac_order.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.user_name
     *
     * @return the value of fac_order.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.user_name
     *
     * @param userName the value for fac_order.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.nick_name
     *
     * @return the value of fac_order.nick_name
     *
     * @mbggenerated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.nick_name
     *
     * @param nickName the value for fac_order.nick_name
     *
     * @mbggenerated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.user_score
     *
     * @return the value of fac_order.user_score
     *
     * @mbggenerated
     */
    public Short getUserScore() {
        return userScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.user_score
     *
     * @param userScore the value for fac_order.user_score
     *
     * @mbggenerated
     */
    public void setUserScore(Short userScore) {
        this.userScore = userScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.remark
     *
     * @return the value of fac_order.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.remark
     *
     * @param remark the value for fac_order.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.remark_mngt
     *
     * @return the value of fac_order.remark_mngt
     *
     * @mbggenerated
     */
    public String getRemarkMngt() {
        return remarkMngt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.remark_mngt
     *
     * @param remarkMngt the value for fac_order.remark_mngt
     *
     * @mbggenerated
     */
    public void setRemarkMngt(String remarkMngt) {
        this.remarkMngt = remarkMngt == null ? null : remarkMngt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.pay_time
     *
     * @return the value of fac_order.pay_time
     *
     * @mbggenerated
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.pay_time
     *
     * @param payTime the value for fac_order.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.writeoff_time
     *
     * @return the value of fac_order.writeoff_time
     *
     * @mbggenerated
     */
    public Date getWriteoffTime() {
        return writeoffTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.writeoff_time
     *
     * @param writeoffTime the value for fac_order.writeoff_time
     *
     * @mbggenerated
     */
    public void setWriteoffTime(Date writeoffTime) {
        this.writeoffTime = writeoffTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.ship
     *
     * @return the value of fac_order.ship
     *
     * @mbggenerated
     */
    public Byte getShip() {
        return ship;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.ship
     *
     * @param ship the value for fac_order.ship
     *
     * @mbggenerated
     */
    public void setShip(Byte ship) {
        this.ship = ship;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.ship_id
     *
     * @return the value of fac_order.ship_id
     *
     * @mbggenerated
     */
    public Long getShipId() {
        return shipId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.ship_id
     *
     * @param shipId the value for fac_order.ship_id
     *
     * @mbggenerated
     */
    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.ship_code
     *
     * @return the value of fac_order.ship_code
     *
     * @mbggenerated
     */
    public String getShipCode() {
        return shipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.ship_code
     *
     * @param shipCode the value for fac_order.ship_code
     *
     * @mbggenerated
     */
    public void setShipCode(String shipCode) {
        this.shipCode = shipCode == null ? null : shipCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.cacel_id
     *
     * @return the value of fac_order.cacel_id
     *
     * @mbggenerated
     */
    public Long getCacelId() {
        return cacelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.cacel_id
     *
     * @param cacelId the value for fac_order.cacel_id
     *
     * @mbggenerated
     */
    public void setCacelId(Long cacelId) {
        this.cacelId = cacelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.cacel_name
     *
     * @return the value of fac_order.cacel_name
     *
     * @mbggenerated
     */
    public String getCacelName() {
        return cacelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.cacel_name
     *
     * @param cacelName the value for fac_order.cacel_name
     *
     * @mbggenerated
     */
    public void setCacelName(String cacelName) {
        this.cacelName = cacelName == null ? null : cacelName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.cacel_time
     *
     * @return the value of fac_order.cacel_time
     *
     * @mbggenerated
     */
    public Date getCacelTime() {
        return cacelTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.cacel_time
     *
     * @param cacelTime the value for fac_order.cacel_time
     *
     * @mbggenerated
     */
    public void setCacelTime(Date cacelTime) {
        this.cacelTime = cacelTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.prepay_id
     *
     * @return the value of fac_order.prepay_id
     *
     * @mbggenerated
     */
    public Long getPrepayId() {
        return prepayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.prepay_id
     *
     * @param prepayId the value for fac_order.prepay_id
     *
     * @mbggenerated
     */
    public void setPrepayId(Long prepayId) {
        this.prepayId = prepayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.create_time
     *
     * @return the value of fac_order.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.create_time
     *
     * @param createTime the value for fac_order.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.update_time
     *
     * @return the value of fac_order.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.update_time
     *
     * @param updateTime the value for fac_order.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.operator_id
     *
     * @return the value of fac_order.operator_id
     *
     * @mbggenerated
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.operator_id
     *
     * @param operatorId the value for fac_order.operator_id
     *
     * @mbggenerated
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.operator_name
     *
     * @return the value of fac_order.operator_name
     *
     * @mbggenerated
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.operator_name
     *
     * @param operatorName the value for fac_order.operator_name
     *
     * @mbggenerated
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fac_order.is_deleted
     *
     * @return the value of fac_order.is_deleted
     *
     * @mbggenerated
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fac_order.is_deleted
     *
     * @param isDeleted the value for fac_order.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getKanjiaId() {
        return kanjiaId;
    }

    public void setKanjiaId(Long kanjiaId) {
        this.kanjiaId = kanjiaId;
    }
}