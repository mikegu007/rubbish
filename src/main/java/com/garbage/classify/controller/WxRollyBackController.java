package com.garbage.classify.controller;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.service.inf.WxPayCallbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wx")
@Api(value = "微信支付回调接口", tags = "微信支付回调接口")
public class WxRollyBackController {

    @Autowired
    private WxPayCallbackService wxPayCallbackService;


    @RequestMapping(value = "/payCallback", method = RequestMethod.POST)
    @ApiOperation(value = "微信支付回调接口", notes = "微信支付回调接口")
    public ResultData addOrder(
            HttpServletRequest request, HttpServletResponse response) {
        wxPayCallbackService.payCallback(request,response);
        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }
}
