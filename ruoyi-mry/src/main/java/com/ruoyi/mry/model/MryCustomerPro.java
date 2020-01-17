package com.ruoyi.mry.model;

import java.math.BigDecimal;
import java.util.Date;

public class MryCustomerPro {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.shop_id
     *
     * @mbggenerated
     */
    private Short shopId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.customer_id
     *
     * @mbggenerated
     */
    private Long customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.pro_id
     *
     * @mbggenerated
     */
    private Short proId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.service_start
     *
     * @mbggenerated
     */
    private Date serviceStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.service_end
     *
     * @mbggenerated
     */
    private Date serviceEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.staff1_id
     *
     * @mbggenerated
     */
    private Long staff1Id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.staff2_id
     *
     * @mbggenerated
     */
    private Long staff2Id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.staff3_id
     *
     * @mbggenerated
     */
    private Long staff3Id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.customer_remark
     *
     * @mbggenerated
     */
    private String customerRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.staff_remark
     *
     * @mbggenerated
     */
    private String staffRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.custom_price
     *
     * @mbggenerated
     */
    private BigDecimal customPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.operator_id
     *
     * @mbggenerated
     */
    private Long operatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.operator_name
     *
     * @mbggenerated
     */
    private String operatorName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_customer_pro.is_deleted
     *
     * @mbggenerated
     */
    private Boolean isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.id
     *
     * @return the value of mry_customer_pro.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.id
     *
     * @param id the value for mry_customer_pro.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.shop_id
     *
     * @return the value of mry_customer_pro.shop_id
     *
     * @mbggenerated
     */
    public Short getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.shop_id
     *
     * @param shopId the value for mry_customer_pro.shop_id
     *
     * @mbggenerated
     */
    public void setShopId(Short shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.customer_id
     *
     * @return the value of mry_customer_pro.customer_id
     *
     * @mbggenerated
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.customer_id
     *
     * @param customerId the value for mry_customer_pro.customer_id
     *
     * @mbggenerated
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.pro_id
     *
     * @return the value of mry_customer_pro.pro_id
     *
     * @mbggenerated
     */
    public Short getProId() {
        return proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.pro_id
     *
     * @param proId the value for mry_customer_pro.pro_id
     *
     * @mbggenerated
     */
    public void setProId(Short proId) {
        this.proId = proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.service_start
     *
     * @return the value of mry_customer_pro.service_start
     *
     * @mbggenerated
     */
    public Date getServiceStart() {
        return serviceStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.service_start
     *
     * @param serviceStart the value for mry_customer_pro.service_start
     *
     * @mbggenerated
     */
    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.service_end
     *
     * @return the value of mry_customer_pro.service_end
     *
     * @mbggenerated
     */
    public Date getServiceEnd() {
        return serviceEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.service_end
     *
     * @param serviceEnd the value for mry_customer_pro.service_end
     *
     * @mbggenerated
     */
    public void setServiceEnd(Date serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.staff1_id
     *
     * @return the value of mry_customer_pro.staff1_id
     *
     * @mbggenerated
     */
    public Long getStaff1Id() {
        return staff1Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.staff1_id
     *
     * @param staff1Id the value for mry_customer_pro.staff1_id
     *
     * @mbggenerated
     */
    public void setStaff1Id(Long staff1Id) {
        this.staff1Id = staff1Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.staff2_id
     *
     * @return the value of mry_customer_pro.staff2_id
     *
     * @mbggenerated
     */
    public Long getStaff2Id() {
        return staff2Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.staff2_id
     *
     * @param staff2Id the value for mry_customer_pro.staff2_id
     *
     * @mbggenerated
     */
    public void setStaff2Id(Long staff2Id) {
        this.staff2Id = staff2Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.staff3_id
     *
     * @return the value of mry_customer_pro.staff3_id
     *
     * @mbggenerated
     */
    public Long getStaff3Id() {
        return staff3Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.staff3_id
     *
     * @param staff3Id the value for mry_customer_pro.staff3_id
     *
     * @mbggenerated
     */
    public void setStaff3Id(Long staff3Id) {
        this.staff3Id = staff3Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.customer_remark
     *
     * @return the value of mry_customer_pro.customer_remark
     *
     * @mbggenerated
     */
    public String getCustomerRemark() {
        return customerRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.customer_remark
     *
     * @param customerRemark the value for mry_customer_pro.customer_remark
     *
     * @mbggenerated
     */
    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark == null ? null : customerRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.staff_remark
     *
     * @return the value of mry_customer_pro.staff_remark
     *
     * @mbggenerated
     */
    public String getStaffRemark() {
        return staffRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.staff_remark
     *
     * @param staffRemark the value for mry_customer_pro.staff_remark
     *
     * @mbggenerated
     */
    public void setStaffRemark(String staffRemark) {
        this.staffRemark = staffRemark == null ? null : staffRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.custom_price
     *
     * @return the value of mry_customer_pro.custom_price
     *
     * @mbggenerated
     */
    public BigDecimal getCustomPrice() {
        return customPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.custom_price
     *
     * @param customPrice the value for mry_customer_pro.custom_price
     *
     * @mbggenerated
     */
    public void setCustomPrice(BigDecimal customPrice) {
        this.customPrice = customPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.remark
     *
     * @return the value of mry_customer_pro.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.remark
     *
     * @param remark the value for mry_customer_pro.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.create_time
     *
     * @return the value of mry_customer_pro.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.create_time
     *
     * @param createTime the value for mry_customer_pro.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.update_time
     *
     * @return the value of mry_customer_pro.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.update_time
     *
     * @param updateTime the value for mry_customer_pro.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.operator_id
     *
     * @return the value of mry_customer_pro.operator_id
     *
     * @mbggenerated
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.operator_id
     *
     * @param operatorId the value for mry_customer_pro.operator_id
     *
     * @mbggenerated
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.operator_name
     *
     * @return the value of mry_customer_pro.operator_name
     *
     * @mbggenerated
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.operator_name
     *
     * @param operatorName the value for mry_customer_pro.operator_name
     *
     * @mbggenerated
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_customer_pro.is_deleted
     *
     * @return the value of mry_customer_pro.is_deleted
     *
     * @mbggenerated
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_customer_pro.is_deleted
     *
     * @param isDeleted the value for mry_customer_pro.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}