package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmUserAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface TmUserAddressMapper {
    int insert(TmUserAddress record);

    int insertSelective(TmUserAddress record);

    TmUserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmUserAddress record);

    int updateByPrimaryKey(TmUserAddress record);
}