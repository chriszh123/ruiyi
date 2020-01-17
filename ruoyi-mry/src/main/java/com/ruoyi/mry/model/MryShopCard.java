package com.ruoyi.mry.model;

import java.math.BigDecimal;
import java.util.Date;

public class MryShopCard {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.shop_id
     *
     * @mbggenerated
     */
    private Short shopId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.service_start
     *
     * @mbggenerated
     */
    private Date serviceStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.service_end
     *
     * @mbggenerated
     */
    private Date serviceEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.price
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.operator_id
     *
     * @mbggenerated
     */
    private Long operatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.operator_name
     *
     * @mbggenerated
     */
    private String operatorName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_basic_shop_card.is_deleted
     *
     * @mbggenerated
     */
    private Boolean isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.id
     *
     * @return the value of mry_basic_shop_card.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.id
     *
     * @param id the value for mry_basic_shop_card.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.shop_id
     *
     * @return the value of mry_basic_shop_card.shop_id
     *
     * @mbggenerated
     */
    public Short getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.shop_id
     *
     * @param shopId the value for mry_basic_shop_card.shop_id
     *
     * @mbggenerated
     */
    public void setShopId(Short shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.name
     *
     * @return the value of mry_basic_shop_card.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.name
     *
     * @param name the value for mry_basic_shop_card.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.service_start
     *
     * @return the value of mry_basic_shop_card.service_start
     *
     * @mbggenerated
     */
    public Date getServiceStart() {
        return serviceStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.service_start
     *
     * @param serviceStart the value for mry_basic_shop_card.service_start
     *
     * @mbggenerated
     */
    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.service_end
     *
     * @return the value of mry_basic_shop_card.service_end
     *
     * @mbggenerated
     */
    public Date getServiceEnd() {
        return serviceEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.service_end
     *
     * @param serviceEnd the value for mry_basic_shop_card.service_end
     *
     * @mbggenerated
     */
    public void setServiceEnd(Date serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.price
     *
     * @return the value of mry_basic_shop_card.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.price
     *
     * @param price the value for mry_basic_shop_card.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.create_time
     *
     * @return the value of mry_basic_shop_card.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.create_time
     *
     * @param createTime the value for mry_basic_shop_card.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.update_time
     *
     * @return the value of mry_basic_shop_card.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.update_time
     *
     * @param updateTime the value for mry_basic_shop_card.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.operator_id
     *
     * @return the value of mry_basic_shop_card.operator_id
     *
     * @mbggenerated
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.operator_id
     *
     * @param operatorId the value for mry_basic_shop_card.operator_id
     *
     * @mbggenerated
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.operator_name
     *
     * @return the value of mry_basic_shop_card.operator_name
     *
     * @mbggenerated
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.operator_name
     *
     * @param operatorName the value for mry_basic_shop_card.operator_name
     *
     * @mbggenerated
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_basic_shop_card.is_deleted
     *
     * @return the value of mry_basic_shop_card.is_deleted
     *
     * @mbggenerated
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_basic_shop_card.is_deleted
     *
     * @param isDeleted the value for mry_basic_shop_card.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}