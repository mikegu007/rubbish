package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmOrderDetail;
import com.garbage.classify.model.vo.OrderDetailVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmOrderDetailMapper {
    int insert(TmOrderDetail record);

    int insertSelective(TmOrderDetail record);

    TmOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmOrderDetail record);

    int updateByPrimaryKey(TmOrderDetail record);

    List<OrderDetailVo> selectByOrderNo(String orderNo);
}