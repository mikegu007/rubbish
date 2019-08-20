package com.garbage.classify.service.inf;


import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.model.dto.OrderListDto;
import com.garbage.classify.model.vo.OrderVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: Mike
 * @Date: 2019/1/3 9:35
 */
public interface OrderService {

    void addOrder(OrderDto orderDto);

    void cancelUserOrder(String orderNo,String remark);

    void cancelWokerOrder(String orderNo,String remark);
    //到达目的地
    void updateDealWithStatus(String orderNo);
    //完成订单
    void finishOrder(String orderNo);
    //抢单
    void grabOrder(String orderNo,String uuid, String workName);

    void payRollback(String orderNo,String payNo);

    OrderVo getOrderInfo(String orderNo);
    //抢单列表
    List<OrderVo> grabOrderList(String longitude,String latitude);
    //订单列表
    PageBean<OrderVo> myOrderList(OrderListDto orderListDto);

}
