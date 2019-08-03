package com.garbage.classify.controller.PersonalCenter;

import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.UserAddress.UserAddressDto;
import com.garbage.classify.model.dto.UserInfo.UserInfoDto;
import com.garbage.classify.service.inf.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/user/info")
@Api(value = "用户信息", tags = "用户信息")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户信息新增和编辑",notes = "用户信息新增和编辑")
    @RequestMapping(value = "/mainTain",method = RequestMethod.POST)
    public ResultData<String> userInfoMainTain(
            @ApiParam(name = "userInfoDto",value = "用户信息",required = true)
            @RequestBody @Validated UserInfoDto userInfoDto){
        return new ResultData<String>(ResultData.SUCCESS,"","用户信息新增和编辑成功！",userService.userInfoMainTain(userInfoDto));
    }


    @ApiOperation(value = "根据openId查询用户信息",notes = "根据openId查询用户信息")
    @RequestMapping(value = "/openId",method = RequestMethod.GET)
    public ResultData<UserInfoDto> queryUserInfoByOpenId(
            @ApiParam(name = "openId",value = "微信openId",required = true)
            @RequestParam(name = "openId") String openId){
        return new ResultData<UserInfoDto>(ResultData.SUCCESS,"","用户信息新增和编辑成功！",userService.queryUserInfoByOpenId(openId));
    }
}
