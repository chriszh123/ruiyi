package com.ruoyi.fac.mapper;

import com.ruoyi.fac.domain.FocusMap;

import java.util.List;

/**
 * 焦点图 数据层
 *
 * @author ruoyi
 * @date 2018-12-24
 */
public interface FocusMapMapper {
    /**
     * 查询焦点图信息
     *
     * @param id 焦点图ID
     * @return 焦点图信息
     */
    FocusMap selectFocusMapById(Integer id);

    /**
     * 查询焦点图列表
     *
     * @param focusMap 焦点图信息
     * @return 焦点图集合
     */
    List<FocusMap> selectFocusMapList(FocusMap focusMap);

    /**
     * 新增焦点图
     *
     * @param focusMap 焦点图信息
     * @return 结果
     */
    int insertFocusMap(FocusMap focusMap);

    /**
     * 修改焦点图
     *
     * @param focusMap 焦点图信息
     * @return 结果
     */
    int updateFocusMap(FocusMap focusMap);

    /**
     * 删除焦点图
     *
     * @param id 焦点图ID
     * @return 结果
     */
    int deleteFocusMapById(Integer id);

    /**
     * 批量删除焦点图
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFocusMapByIds(String[] ids);

}