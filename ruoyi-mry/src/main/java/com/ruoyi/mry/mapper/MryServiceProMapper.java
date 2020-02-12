package com.ruoyi.mry.mapper;

import com.ruoyi.mry.model.MryServicePro;
import com.ruoyi.mry.model.MryServiceProExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MryServiceProMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int countByExample(MryServiceProExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int deleteByExample(MryServiceProExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int insert(MryServicePro record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int insertSelective(MryServicePro record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    List<MryServicePro> selectByExample(MryServiceProExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    MryServicePro selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MryServicePro record, @Param("example") MryServiceProExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MryServicePro record, @Param("example") MryServiceProExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MryServicePro record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mry_basic_service_pro
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MryServicePro record);
}