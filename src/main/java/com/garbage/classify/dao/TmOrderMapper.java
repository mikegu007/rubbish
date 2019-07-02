package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface TmOrderMapper {
    int insert(TmOrder record);

    int insertSelective(TmOrder record);

    TmOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmOrder record);

    int updateByPrimaryKey(TmOrder record);
}