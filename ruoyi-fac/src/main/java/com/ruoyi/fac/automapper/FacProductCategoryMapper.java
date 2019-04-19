package com.ruoyi.fac.automapper;

import com.ruoyi.fac.model.FacProductCategory;
import com.ruoyi.fac.model.FacProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacProductCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    int countByExample(@Param("example") FacProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    int deleteByExample(@Param("example") FacProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    int insert(@Param("record") FacProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    int insertSelective(@Param("record") FacProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    List<FacProductCategory> selectByExample(@Param("example") FacProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FacProductCategory record, @Param("example") FacProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_product_category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FacProductCategory record, @Param("example") FacProductCategoryExample example);
}