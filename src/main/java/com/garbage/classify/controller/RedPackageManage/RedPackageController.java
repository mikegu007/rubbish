package com.garbage.classify.controller.RedPackageManage;

import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.UserInfo.UserInfoDto;
import com.garbage.classify.service.inf.RedPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/red/packet")
@Api(value = "红包信息", tags = "红包信息")
public class RedPackageController {

    @Autowired
    private RedPackageService redPackageService;

    @ApiOperation(value = "根据UUID获取用户有效红包数量",notes = "根据UUID获取用户有效红包数量")
    @RequestMapping(value = "/openId",method = RequestMethod.GET)
    public ResultData<UserInfoDto> queryUserInfoByOpenId(
            @ApiParam(name = "openId",value = "微信openId",required = true)
            @RequestParam(name = "openId") String openId){
        return new ResultData<UserInfoDto>(ResultData.SUCCESS,"","用户信息新增和编辑成功！",userService.queryUserInfoByOpenId(openId));
    }
}
