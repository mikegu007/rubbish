package com.garbage.classify.controller.RedPackageManage;

import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.RedPackage.RedPackageInfoListDto;
import com.garbage.classify.model.dto.RedPackage.RedPackageInfoListVo;
import com.garbage.classify.model.dto.UserInfo.UserInfoDto;
import com.garbage.classify.model.vo.RedPackageVo;
import com.garbage.classify.service.inf.RedPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/red/packet")
@Api(value = "红包信息", tags = "红包信息")
public class RedPackageController {

    @Autowired
    private RedPackageService redPackageService;

    @ApiOperation(value = "根据UUID获取用户有效红包数量",notes = "根据UUID获取用户有效红包数量")
    @RequestMapping(value = "/uuid",method = RequestMethod.GET)
    public ResultData<Integer> queryRedPacketCountByUuid(
            @ApiParam(name = "uuid",value = "uuid",required = true)
            @RequestParam(name = "uuid") String uuid){
        return new ResultData<Integer>(ResultData.SUCCESS,"","用户信息新增和编辑成功！",redPackageService.queryRedPacketCountByUuid(uuid));
    }

    @ApiOperation(value = "根据UUID获取用户有效红包列表",notes = "根据UUID获取用户有效红包列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageBean<RedPackageInfoListVo> queryRedPacketListByUuid(
            @ApiParam(name = "redPackageInfoListDto",value = "用户红包信息列表查询入参")
            @RequestBody RedPackageInfoListDto redPackageInfoListDto){
        return redPackageService.queryRedPacketListByUuid(redPackageInfoListDto);
    }
}
