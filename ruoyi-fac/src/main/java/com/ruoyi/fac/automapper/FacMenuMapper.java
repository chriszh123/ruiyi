package com.ruoyi.fac.automapper;

import com.ruoyi.fac.model.FacMenu;
import com.ruoyi.fac.model.FacMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    int countByExample(@Param("example") FacMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    int deleteByExample(@Param("example") FacMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    int insert(@Param("record") FacMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    int insertSelective(@Param("record") FacMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    List<FacMenu> selectByExample(@Param("example") FacMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FacMenu record, @Param("example") FacMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fac_menu
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FacMenu record, @Param("example") FacMenuExample example);
}