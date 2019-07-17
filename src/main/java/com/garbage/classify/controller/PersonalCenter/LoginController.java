package com.garbage.classify.controller.PersonalCenter;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LG
 * @data 2019/7/17 18:43
 */
@RestController
@RequestMapping("/login")
@Api(value = "用户登录", tags = "用户登录")
public class LoginController {

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "新增订单", notes = "新增订单")
    public ResultData addOrder(
            @RequestBody OrderDto orderDto) {

        return new ResultData(ResultData.SUCCESS, Constant.SUCCESS, Constant.SUCCESS, null);
    }

}
