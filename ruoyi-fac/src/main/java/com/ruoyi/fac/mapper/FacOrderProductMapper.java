package com.ruoyi.fac.mapper;

import com.ruoyi.fac.model.FacOrderProduct;
import com.ruoyi.fac.model.FacOrderProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacOrderProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    int countByExample(FacOrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    int deleteByExample(FacOrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    int insert(FacOrderProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    int insertSelective(FacOrderProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    List<FacOrderProduct> selectByExample(FacOrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FacOrderProduct record, @Param("example") FacOrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_order_product
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FacOrderProduct record, @Param("example") FacOrderProductExample example);
}