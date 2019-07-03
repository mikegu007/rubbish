package com.garbage.classify.service.inf;


import com.garbage.classify.model.dto.UserAddressDto;
import com.garbage.classify.model.vo.UserAddressVo;

import java.util.List;

/**
 * @Author: Mike
 * @Date: 2019/1/3 9:35
 */
public interface AddressService {

    void addAddress(UserAddressDto userAddressDto);

    void saveAddress(UserAddressDto userAddressDto);

    List<UserAddressVo> getAddressList(String uuid);
}
