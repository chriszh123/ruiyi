package com.ruoyi.mry.model;

import java.util.Date;

public class MryStaff {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.shop_id
     *
     * @mbggenerated
     */
    private Short shopId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.sex
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.birthday
     *
     * @mbggenerated
     */
    private String birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.phone_number
     *
     * @mbggenerated
     */
    private String phoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.join_time
     *
     * @mbggenerated
     */
    private Date joinTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.go_time
     *
     * @mbggenerated
     */
    private Date goTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.operator_id
     *
     * @mbggenerated
     */
    private Long operatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.operator_name
     *
     * @mbggenerated
     */
    private String operatorName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mry_staff.is_deleted
     *
     * @mbggenerated
     */
    private Boolean isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.id
     *
     * @return the value of mry_staff.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.id
     *
     * @param id the value for mry_staff.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.shop_id
     *
     * @return the value of mry_staff.shop_id
     *
     * @mbggenerated
     */
    public Short getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.shop_id
     *
     * @param shopId the value for mry_staff.shop_id
     *
     * @mbggenerated
     */
    public void setShopId(Short shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.name
     *
     * @return the value of mry_staff.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.name
     *
     * @param name the value for mry_staff.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.sex
     *
     * @return the value of mry_staff.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.sex
     *
     * @param sex the value for mry_staff.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.birthday
     *
     * @return the value of mry_staff.birthday
     *
     * @mbggenerated
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.birthday
     *
     * @param birthday the value for mry_staff.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.address
     *
     * @return the value of mry_staff.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.address
     *
     * @param address the value for mry_staff.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.phone_number
     *
     * @return the value of mry_staff.phone_number
     *
     * @mbggenerated
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.phone_number
     *
     * @param phoneNumber the value for mry_staff.phone_number
     *
     * @mbggenerated
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.status
     *
     * @return the value of mry_staff.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.status
     *
     * @param status the value for mry_staff.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.join_time
     *
     * @return the value of mry_staff.join_time
     *
     * @mbggenerated
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.join_time
     *
     * @param joinTime the value for mry_staff.join_time
     *
     * @mbggenerated
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.go_time
     *
     * @return the value of mry_staff.go_time
     *
     * @mbggenerated
     */
    public Date getGoTime() {
        return goTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.go_time
     *
     * @param goTime the value for mry_staff.go_time
     *
     * @mbggenerated
     */
    public void setGoTime(Date goTime) {
        this.goTime = goTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.remark
     *
     * @return the value of mry_staff.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.remark
     *
     * @param remark the value for mry_staff.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.create_time
     *
     * @return the value of mry_staff.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.create_time
     *
     * @param createTime the value for mry_staff.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.update_time
     *
     * @return the value of mry_staff.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.update_time
     *
     * @param updateTime the value for mry_staff.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.operator_id
     *
     * @return the value of mry_staff.operator_id
     *
     * @mbggenerated
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.operator_id
     *
     * @param operatorId the value for mry_staff.operator_id
     *
     * @mbggenerated
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.operator_name
     *
     * @return the value of mry_staff.operator_name
     *
     * @mbggenerated
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.operator_name
     *
     * @param operatorName the value for mry_staff.operator_name
     *
     * @mbggenerated
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mry_staff.is_deleted
     *
     * @return the value of mry_staff.is_deleted
     *
     * @mbggenerated
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mry_staff.is_deleted
     *
     * @param isDeleted the value for mry_staff.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}