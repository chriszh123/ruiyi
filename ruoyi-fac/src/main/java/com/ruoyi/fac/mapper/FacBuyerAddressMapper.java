package com.ruoyi.fac.mapper;

import com.ruoyi.fac.model.FacBuyerAddress;
import com.ruoyi.fac.model.FacBuyerAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacBuyerAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    int countByExample(FacBuyerAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    int deleteByExample(FacBuyerAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    int insert(FacBuyerAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    int insertSelective(FacBuyerAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    List<FacBuyerAddress> selectByExample(FacBuyerAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FacBuyerAddress record, @Param("example") FacBuyerAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_buyer_address
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FacBuyerAddress record, @Param("example") FacBuyerAddressExample example);
}