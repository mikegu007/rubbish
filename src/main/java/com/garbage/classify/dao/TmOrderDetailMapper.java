package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmOrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface TmOrderDetailMapper {
    int insert(TmOrderDetail record);

    int insertSelective(TmOrderDetail record);

    TmOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmOrderDetail record);

    int updateByPrimaryKey(TmOrderDetail record);
}