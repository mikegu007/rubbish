package com.garbage.classify.controller.HomePage;

import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.DrawEnergyDto;
import com.garbage.classify.model.dto.UserFeedBack.FreeBackDto;
import com.garbage.classify.service.inf.EnergyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energy")
@Api(value = "用户能量", tags = "用户能量")
public class EnergyController {

    @Autowired
    private EnergyService energyService;

    @ApiOperation(value = "根据用户uuid获取可领取能量列表", notes = "根据用户uuid获取可领取能量列表")
    @RequestMapping(value = "/draw/list", method = RequestMethod.GET)
    public ResultData getEnergyListByUuid(
            @ApiParam(name = "uuid",value = "uuid",required = true)
            @RequestParam(name = "uuid") String uuid) {
        return new ResultData(ResultData.SUCCESS, "", "可领取能量列表获取成功！", energyService.getEnergyListByUuid(uuid));
    }

    @ApiOperation(value = "用户领取能量", notes = "用户领取能量")
    @RequestMapping(value = "/draw", method = RequestMethod.POST)
    public ResultData drawEnergy(
            @ApiParam(name = "drawEnergyDto",value = "领取绿色能量",required = true)
            @RequestBody @Validated DrawEnergyDto drawEnergyDto) {
        energyService.drawEnergy(drawEnergyDto);
        return new ResultData(ResultData.SUCCESS, "", "可领取能量列表获取成功！",null);
    }
}
