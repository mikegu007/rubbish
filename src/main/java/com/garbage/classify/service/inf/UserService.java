package com.garbage.classify.service.inf;

import com.garbage.classify.model.dto.UserInfo.UserInfoDto;

public interface UserService {
    /**
     * 用户信息新增和维护
     * @param userInfoDto
     * @return
     */
    String userInfoMainTain(UserInfoDto userInfoDto);

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    UserInfoDto queryUserInfoByOpenId(String openId);
}
