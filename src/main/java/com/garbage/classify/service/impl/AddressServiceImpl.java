package com.garbage.classify.service.impl;


import com.garbage.classify.constant.Constant;
import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TmUserAddressMapper;
import com.garbage.classify.dao.TmUserMapper;
import com.garbage.classify.model.dto.UserAddress.UserAddressDto;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmUser;
import com.garbage.classify.model.po.TmUserAddress;
import com.garbage.classify.model.vo.UserAddressVo;
import com.garbage.classify.service.inf.AddressService;
import com.garbage.classify.service.inf.RemoveCacheService;
import com.garbage.classify.utils.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mike
 * @Date: 2019/1/3 9:36
 */
@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Autowired
    private TmUserAddressMapper tmUserAddressMapper;

    @Autowired
    private TmUserMapper tmUserMapper;

    @Autowired
    private RemoveCacheService removeCacheService;


    @Override
    public Long addAddress(UserAddressDto userAddressDto) {
        logger.info("用户地址新增 详情[{}]",userAddressDto);
        userAddressDto.validateAndInit();
        TmUserAddress tmUserAddress = new TmUserAddress();
        BeanUtils.copyProperties(userAddressDto, tmUserAddress);
        tmUserAddressMapper.insertSelective(tmUserAddress);
        return tmUserAddress.getId();
    }

    @Override
    public void updateAddress(UserAddressDto userAddressDto) {
        logger.info("用户地址更新 详情[{}]",userAddressDto);
        userAddressDto.validateAndInit();
        if (ToolUtil.isEmpty(userAddressDto.getId())) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "id 不能为空");
        }
        TmUserAddress tmUserAddress = new TmUserAddress();
        BeanUtils.copyProperties(userAddressDto, tmUserAddress);
        tmUserAddressMapper.updateByPrimaryKeySelective(tmUserAddress);
    }

    @Override
    public List<UserAddressVo> getAddressList(String uuid) {
        if (ToolUtil.isEmpty(uuid)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "uuid 不能为空");
        }
        List<TmUserAddress> tmUserAddresses = tmUserAddressMapper.selectByUuid(uuid);
        return this.tranformTmToVo(tmUserAddresses);
    }

    @Override
    public Long editUserAddress(UserAddressDto userAddressDto) {
        logger.info("新增或者编辑用户信息 详情[{}]",userAddressDto.toString());
        Long id = userAddressDto.getId();
        if(ToolUtil.isEmpty(id)){
            // 新增用户地址信息
            id = addAddress(userAddressDto);
        }else {
            // 编辑用户地址信息
            updateAddress(userAddressDto);
        }
        // 设置默认地址
        Boolean defaultAddress = userAddressDto.getDefaultAddress();
        if(defaultAddress){
            logger.info("设置默认地址 地址[{}] uuid[{}]",id,userAddressDto.getUserUuid());
            tmUserMapper.updateDefaultAddressByUuid(id,userAddressDto.getUserUuid());
        }
        // 清除缓存
        removeCacheService.removeUserAddressByUuid(userAddressDto.getUserUuid());
        return null;
    }


    public List<UserAddressVo> tranformTmToVo(List<TmUserAddress> tmUserAddresses) {
        List<UserAddressVo> userAddressVos = new ArrayList<>();
        tmUserAddresses.forEach(that -> {
            UserAddressVo userAddressVo = new UserAddressVo(that);
            userAddressVos.add(userAddressVo);
        });
        return userAddressVos;
    }

    @Override
    @Cacheable(value = "rubbish:user:address:list:uuid",key = "'rubbish:user:address:list:uuid:'+#p0")
    public List<UserAddressDto> getUserAddressByUuid(String uuid) {
        // 获取用户信息
        TmUser tmUser = tmUserMapper.queryUserInfoByUuid(uuid);
        if(ToolUtil.isEmpty(tmUser)){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"未查询到用户信息");
        }
        // 获取用户地址
        List<TmUserAddress> tmUserAddressList = tmUserAddressMapper.queryUserAddressListByUuid(uuid);
        // 数据转换
        List<UserAddressDto> userAddressDtoList =new ArrayList<>();
        tmUserAddressList.stream().forEach(x->{
            userAddressDtoList.add(transformUserAddressDto(x));
        });
        // 默认地址
        for (UserAddressDto userAddressDto:userAddressDtoList){
            if(userAddressDto.getId().equals(tmUser.getDefaultAddressId())){
                userAddressDto.setDefaultAddress(true);
                break;
            }
        }
        return userAddressDtoList;
    }

    /**
     * 数据类型转换
     * @param tmUserAddress
     * @return
     */
    private UserAddressDto transformUserAddressDto(TmUserAddress tmUserAddress){
        if(!ToolUtil.isEmpty(tmUserAddress)){
            UserAddressDto userAddressDto=new UserAddressDto();
            BeanUtils.copyProperties(tmUserAddress,userAddressDto);
            userAddressDto.setDefaultAddress(false);
            return userAddressDto;
        }
        return null;
    }

    @Override
    public void delUserAddressById(Long id) {
        // 查询用户地址
        TmUserAddress tmUserAddress = tmUserAddressMapper.selectByPrimaryKey(id);
        if(tmUserAddress!=null){
            tmUserAddressMapper.delUserAddressById(id);
            // 清除缓存
            removeCacheService.removeUserAddressByUuid(tmUserAddress.getUserUuid());
        }
    }
}
