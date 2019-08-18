package com.garbage.classify.dao;

import com.garbage.classify.model.dto.OrderListDto;
import com.garbage.classify.model.po.TmOrder;
import com.garbage.classify.model.vo.OrderVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmOrderMapper {
    int insert(TmOrder record);

    int insertSelective(TmOrder record);

    TmOrder selectByPrimaryKey(Long id);


    OrderVo selectVoInfoByOrderNo(String orderNo);

    TmOrder selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(TmOrder record);

    int updateByPrimaryKey(TmOrder record);

    Integer selectCountByworkId(@Param("workId")String workId);

    Integer getCountMyOrder(OrderListDto orderListDto);

    List<OrderVo> getMyOrder(OrderListDto orderListDto);

    List<OrderVo> grabOrderList(String longitude,String latitude);


}