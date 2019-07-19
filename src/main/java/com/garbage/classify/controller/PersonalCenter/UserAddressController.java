package com.garbage.classify.controller.PersonalCenter;

import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.UserAddress.UserAddressDto;
import com.garbage.classify.service.inf.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户地址
 */
@RestController
@RequestMapping("/user/address")
@Api(value = "用户地址", tags = "用户地址")
public class UserAddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "新增或编辑用户地址",notes = "新增或编辑用户地址")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ResultData<Long> editUserAddress(
            @ApiParam(name = "userAddressDto",value = "用户地址维护")
            @RequestBody UserAddressDto userAddressDto){
        return new ResultData<Long>(ResultData.SUCCESS,"","用户地址维护成功！",addressService.editUserAddress(userAddressDto));
    }


    @ApiOperation(value = "查询用户地址列表（分页）",notes = "查询用户地址列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageBean<Long> getUserAddressByUuid(){
        // TODO: 2019/7/19 分页查询用户地址列表 
        return null;
    }



}
