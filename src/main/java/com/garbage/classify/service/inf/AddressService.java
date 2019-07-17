package com.garbage.classify.service.inf;

import com.garbage.classify.model.dto.UserAddress.UserAddressDto;
import com.garbage.classify.model.vo.UserAddressVo;

import java.util.List;

/**
 * @Author: Mike
 * @Date: 2019/1/3 9:35
 */
public interface AddressService {

    List<UserAddressVo> getAddressList(String uuid);

    Long addAddress(UserAddressDto userAddressDto);

    void updateAddress(UserAddressDto userAddressDto);

    /**
     * 新增或者编辑用户地址信息
     * @param userAddressDto
     * @return
     */
    Long editUserAddress(UserAddressDto userAddressDto);
}
