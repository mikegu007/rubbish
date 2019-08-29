package com.garbage.classify.dao;

import com.garbage.classify.model.po.TtEnergyGenerate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TtEnergyGenerateMapper {
    int insert(TtEnergyGenerate record);

    int insertSelective(TtEnergyGenerate record);

    /**
     * 获取用户能量信息
     * @param uuid
     * @return
     */
    List<TtEnergyGenerate> getEnergyListByUuid(@Param("uuid") String uuid,@Param("type") Integer type);

    /**
     * 获取能量
     * @param energyId
     * @return
     */
    TtEnergyGenerate selectById(@Param("id") Long energyId);

    /**
     * 获取所有已经过期的能量
     * @return
     */
    List<TtEnergyGenerate> queryExpireEnergy();

    Long updateBySelective(TtEnergyGenerate ttEnergyGenerate);
}