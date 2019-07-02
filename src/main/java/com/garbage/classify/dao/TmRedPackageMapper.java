package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmRedPackage;
import org.springframework.stereotype.Repository;

@Repository
public interface TmRedPackageMapper {
    int insert(TmRedPackage record);

    int insertSelective(TmRedPackage record);

    TmRedPackage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmRedPackage record);

    int updateByPrimaryKey(TmRedPackage record);
}