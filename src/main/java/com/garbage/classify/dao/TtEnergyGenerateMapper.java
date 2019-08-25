package com.garbage.classify.dao;

import com.garbage.classify.model.po.TtEnergyGenerate;
import org.springframework.stereotype.Repository;

@Repository
public interface TtEnergyGenerateMapper {
    int insert(TtEnergyGenerate record);

    int insertSelective(TtEnergyGenerate record);
}