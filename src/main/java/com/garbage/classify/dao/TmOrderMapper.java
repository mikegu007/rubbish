package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmOrder;
import com.garbage.classify.model.vo.OrderVo;
import org.springframework.stereotype.Repository;

@Repository
public interface TmOrderMapper {
    int insert(TmOrder record);

    int insertSelective(TmOrder record);

    TmOrder selectByPrimaryKey(Long id);


    OrderVo selectVoInfoByOrderNo(String orderNo);

    TmOrder selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(TmOrder record);

    int updateByPrimaryKey(TmOrder record);
}