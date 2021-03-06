package com.ruoyi.fac.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表 fac_product
 *
 * @author ruoyi
 * @date 2018-12-24
 */
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "商品")
    private Long id;
    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String name;
    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;
    /**
     * 售价(抢购价)
     */
    @Excel(name = "售价")
    private BigDecimal price;
    /**
     * 销量
     */
    @Excel(name = "销量")
    private Integer sales;
    /**
     * 上架状态
     */
    @Excel(name = "状态", readConverterExp = "1=上架,2=下架")
    private Integer status;
    /**
     * 商品类目
     */
    private Integer categoryId;
    /**
     * 所属商家
     */
    private Integer businessId;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 库存数量
     */
    @Excel(name = "库存数量")
    private Integer inventoryQuantity;
    /**
     * 订单数量
     */
    @Excel(name = "订单数量")
    private Integer orderCount;
    /**
     * 每人限购数量
     */
    private Integer limitQuantity;
    /**
     * 虚拟购买人数
     */
    private Integer vmBuyerQuantity;
    /**
     * 分销奖金
     */
    private BigDecimal distribution;
    /**
     * 奖励积分
     */
    private Integer bonusPoints;
    /**
     * 抢购开始时间
     */
    @Excel(name = "抢购开始时间", dateFormat = "yyyy-MM-dd HH:mm")
    private Date rushStart;
    private String rushStartStr;
    /**
     * 抢购结束时间
     */
    @Excel(name = "抢购结束时间", dateFormat = "yyyy-MM-dd HH:mm")
    private Date rushEnd;
    private String rushEndStr;
    /**
     * 核销开始时间
     */
    @Excel(name = "核销开始时间", dateFormat = "yyyy-MM-dd HH:mm")
    private Date writeoffStart;
    private String writeoffStartStr;
    /**
     * 核销结束时间
     */
    @Excel(name = "核销结束时间", dateFormat = "yyyy-MM-dd HH:mm")
    private Date writeoffEnd;
    private String writeoffEndStr;
    /**
     * 商品图片
     */
    private String picture;
    /**
     * 商品介绍内容url
     */
    private String introduction;
    /**
     * 编辑场景下新的商品介绍内容
     */
    private String introductionEdit;
    /**
     * 发货方式
     */
    @Excel(name = "发货方式", readConverterExp = "1=送货上门,2=核销码")
    private Integer shipMode;
    /**
     * 运费
     */
    private BigDecimal shipCost;
    /**
     * 操作者ID
     */
    private Long operatorId;
    /**
     * 操作者姓名
     */
    private String operatorName;
    /**
     * 是否删除
     */
    private Integer isDeleted;
    /**
     * 已核销
     */
    private Integer writeOffed = 0;
    /**
     * 待核销
     */
    private Integer torWiteOff = 0;
    /**
     * 商品图片完全路径
     */
    private String imgPath;
    /**
     * 页面查询条件：距离核销结束时间多少天:查询条件中会精确折算成分钟
     */
    private Integer expireTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getSales() {
        return sales;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setLimitQuantity(Integer limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getLimitQuantity() {
        return limitQuantity;
    }

    public void setVmBuyerQuantity(Integer vmBuyerQuantity) {
        this.vmBuyerQuantity = vmBuyerQuantity;
    }

    public Integer getVmBuyerQuantity() {
        return vmBuyerQuantity;
    }

    public void setDistribution(BigDecimal distribution) {
        this.distribution = distribution;
    }

    public BigDecimal getDistribution() {
        return distribution;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setRushStart(Date rushStart) {
        this.rushStart = rushStart;
    }

    public Date getRushStart() {
        return rushStart;
    }

    public void setRushEnd(Date rushEnd) {
        this.rushEnd = rushEnd;
    }

    public Date getRushEnd() {
        return rushEnd;
    }

    public void setWriteoffStart(Date writeoffStart) {
        this.writeoffStart = writeoffStart;
    }

    public Date getWriteoffStart() {
        return writeoffStart;
    }

    public void setWriteoffEnd(Date writeoffEnd) {
        this.writeoffEnd = writeoffEnd;
    }

    public Date getWriteoffEnd() {
        return writeoffEnd;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setShipMode(Integer shipMode) {
        this.shipMode = shipMode;
    }

    public Integer getShipMode() {
        return shipMode;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sort", getSort())
                .append("name", getName())
                .append("price", getPrice())
                .append("sales", getSales())
                .append("status", getStatus())
                .append("categoryId", getCategoryId())
                .append("businessId", getBusinessId())
                .append("originalPrice", getOriginalPrice())
                .append("inventoryQuantity", getInventoryQuantity())
                .append("limitQuantity", getLimitQuantity())
                .append("vmBuyerQuantity", getVmBuyerQuantity())
                .append("distribution", getDistribution())
                .append("bonusPoints", getBonusPoints())
                .append("rushStart", getRushStart())
                .append("rushEnd", getRushEnd())
                .append("writeoffStart", getWriteoffStart())
                .append("writeoffEnd", getWriteoffEnd())
                .append("picture", getPicture())
                .append("introduction", getIntroduction())
                .append("shipMode", getShipMode())
                .append("shipCost", getShipCost())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("operatorId", getOperatorId())
                .append("operatorName", getOperatorName())
                .append("isDeleted", getIsDeleted())
                .toString();
    }

    public String getRushStartStr() {
        return rushStartStr;
    }

    public void setRushStartStr(String rushStartStr) {
        this.rushStartStr = rushStartStr;
    }

    public String getRushEndStr() {
        return rushEndStr;
    }

    public void setRushEndStr(String rushEndStr) {
        this.rushEndStr = rushEndStr;
    }

    public String getWriteoffStartStr() {
        return writeoffStartStr;
    }

    public void setWriteoffStartStr(String writeoffStartStr) {
        this.writeoffStartStr = writeoffStartStr;
    }

    public String getWriteoffEndStr() {
        return writeoffEndStr;
    }

    public void setWriteoffEndStr(String writeoffEndStr) {
        this.writeoffEndStr = writeoffEndStr;
    }

    public Integer getWriteOffed() {
        return writeOffed;
    }

    public void setWriteOffed(Integer writeOffed) {
        this.writeOffed = writeOffed;
    }

    public Integer getTorWiteOff() {
        return torWiteOff;
    }

    public void setTorWiteOff(Integer torWiteOff) {
        this.torWiteOff = torWiteOff;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getIntroductionEdit() {
        return introductionEdit;
    }

    public void setIntroductionEdit(String introductionEdit) {
        this.introductionEdit = introductionEdit;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }
}
