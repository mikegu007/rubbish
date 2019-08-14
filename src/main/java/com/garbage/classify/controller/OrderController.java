package com.garbage.classify.controller;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.service.inf.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
    @ApiOperation(value = "新增订单", notes = "新增订单")
    public ResultData addOrder(
            @ApiParam(name = "orderDto", value = "订单保存对象", required = true)
            @RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }


}
