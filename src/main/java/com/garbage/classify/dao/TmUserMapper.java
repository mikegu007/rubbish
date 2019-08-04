package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TmUserMapper {

    int insertSelective(TmUser record);

    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    TmUser queryUserInfoByOpenId(@Param("openId") String openId);

    /**
     * 更新用户信息
     * @param tmUser
     */
    Long updateByPrimaryKeySelective(TmUser tmUser);
}