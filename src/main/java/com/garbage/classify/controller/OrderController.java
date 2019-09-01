package com.garbage.classify.controller;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.model.dto.OrderListDto;
import com.garbage.classify.model.vo.OrderVo;
import com.garbage.classify.service.inf.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: Mike
 * @Date: 2019/1/3 9:31
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单相关API", tags = "订单相关API")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "用户下订单", notes = "用户下订单")
    public ResultData addOrder(
            @ApiParam(name = "orderDto", value = "订单保存对象", required = true)
            @RequestBody OrderDto orderDto) {
        String payOrderNo = orderService.addOrder(orderDto);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, payOrderNo);
    }


    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ApiOperation(value = "用户取消订单", notes = "用户取消订单")
    public ResultData cancelOrder(
            @ApiParam(name = "orderNo",value = "orderNo",required = true)
            @RequestParam(name = "orderNo") String orderNo,
            @ApiParam(name = "remark",value = "remark",required = true)
            @RequestParam(name = "remark") String remark) {
        orderService.cancelUserOrder(orderNo,remark);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }

    @RequestMapping(value = "/cancelWokerOrder", method = RequestMethod.POST)
    @ApiOperation(value = "worker取消订单", notes = "worker取消订单")
    public ResultData cancelWokerOrder(
            @ApiParam(name = "orderNo",value = "orderNo",required = true)
            @RequestParam(name = "orderNo") String orderNo,
            @ApiParam(name = "remark",value = "remark",required = true)
            @RequestParam(name = "remark") String remark) {
        orderService.cancelWokerOrder(orderNo,remark);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }

    @RequestMapping(value = "/updateDealWithStatus", method = RequestMethod.POST)
    @ApiOperation(value = "到达目的地", notes = "到达目的地")
    public ResultData updateDealWithStatus(
            @ApiParam(name = "orderNo",value = "orderNo",required = true)
            @RequestParam(name = "orderNo") String orderNo) {
        orderService.updateDealWithStatus(orderNo);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }


    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    @ApiOperation(value = "到达目的地", notes = "到达目的地")
    public ResultData finishOrder(
            @ApiParam(name = "orderNo",value = "orderNo",required = true)
            @RequestParam(name = "orderNo") String orderNo) {
        orderService.finishOrder(orderNo);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }


    @RequestMapping(value = "/grabOrder", method = RequestMethod.POST)
    @ApiOperation(value = "抢单", notes = "抢单")
    public ResultData grabOrder(
            @ApiParam(name = "orderNo",value = "orderNo",required = true)
            @RequestParam(name = "orderNo") String orderNo,
            @ApiParam(name = "uuid",value = "uuid",required = true)
            @RequestParam(name = "uuid",required = true) String uuid) {
        orderService.grabOrder( orderNo, uuid);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }


    @RequestMapping(value = "/payRollback", method = RequestMethod.POST)
    @ApiOperation(value = "支付回调", notes = "支付回调，微信支付回调修改订单状态（待确定）")
    public ResultData payRollback(
            @ApiParam(name = "orderNo",value = "订单号",required = true)
            @RequestParam(name = "orderNo") String orderNo,
            @ApiParam(name = "payNo",value = "支付单号",required =false)
            @RequestParam(name = "payNo") String payNo) {
        orderService.payRollback(orderNo,payNo);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }

    @RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
    @ApiOperation(value = "订单详情", notes = "订单详情")
    public ResultData<OrderVo> orderInfo(
            @ApiParam(name = "orderNo",value = "orderNo",required = true)
            @RequestParam(name = "orderNo") String orderNo) {
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS,  orderService.getOrderInfo(orderNo));
    }


    @RequestMapping(value = "/grabOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "抢单列表", notes = "抢单列表")
    public ResultData<OrderVo> grabOrderList(
            @ApiParam(name = "longitude",value = "精度",required = true)
            @RequestParam(name = "longitude") String longitude,
            @ApiParam(name = "latitude",value = "维度",required = true)
            @RequestParam(name = "latitude") String latitude) {
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS,  orderService.grabOrderList(longitude,latitude));
    }


    @RequestMapping(value = "/myOrders", method = RequestMethod.POST)
    @ApiOperation(value = "我的列表", notes = "我的列表")
    public PageBean<OrderVo> myOrders(
            @ApiParam(name = "orderQueryConditionDTO", value = "查询条件", required = true)
            @RequestBody OrderListDto orderListDto) {
        return orderService.myOrderList(orderListDto);
    }


}
