package com.ruoyi.fac.mapper;

import com.ruoyi.fac.domain.FacProductWriteoff;
import com.ruoyi.fac.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 核销记录 数据层
 *
 * @author ruoyi
 * @date 2019-04-06
 */
public interface FacProductWriteoffMapper {
    /**
     * 查询核销记录信息
     *
     * @param id 核销记录ID
     * @return 核销记录信息
     */
    FacProductWriteoff selectFacProductWriteoffById(Integer id);

    /**
     * 查询核销记录列表
     *
     * @param facProductWriteoff 核销记录信息
     * @return 核销记录集合
     */
    List<FacProductWriteoff> selectFacProductWriteoffList(FacProductWriteoff facProductWriteoff);

    /**
     * 新增核销记录
     *
     * @param facProductWriteoff 核销记录信息
     * @return 结果
     */
    int insertFacProductWriteoff(FacProductWriteoff facProductWriteoff);

    /**
     * 修改核销记录
     *
     * @param facProductWriteoff 核销记录信息
     * @return 结果
     */
    int updateFacProductWriteoff(FacProductWriteoff facProductWriteoff);

    /**
     * 删除核销记录
     *
     * @param id 核销记录ID
     * @return 结果
     */
    int deleteFacProductWriteoffById(Integer id);

    /**
     * 批量删除核销记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFacProductWriteoffByIds(String[] ids);

    int deleteFacProductWriteoffByOrderNos(@Param("orderNos") List<String> orderNos);

    List<FacProductWriteoff> selectFacProductWriteoffListByOrderNos(@Param("orderNos") List<String> orderNos, @Param("status") List<Integer> status);

    /**
     * 商家核销商品订单
     *
     * @param facProductWriteoff 核销记录信息
     * @return 结果
     */
    int writeoffByOrderNo(FacProductWriteoff facProductWriteoff);

    int batchInsert(@Param("list") List<FacProductWriteoff> list);
}