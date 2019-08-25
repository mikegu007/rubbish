package com.garbage.classify.service.inf;

import com.garbage.classify.model.dto.UserInfo.UserInfoDto;
import com.garbage.classify.model.po.TmUser;

import java.util.List;

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

    /**
     * 根据uuid获取用户信息
     * @param uuid
     * @return
     */
    TmUser queryUserInfoByUuid(String uuid);

    /**
     * 获取所有用户信息
     * @return
     */
    List<TmUser> queryAllUserInfo();
}
