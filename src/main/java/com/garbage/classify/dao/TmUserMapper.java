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

    /**
     * 设置用户默认地址
     * @param id
     * @param userUuid
     * @return
     */
    Long updateDefaultAddressByUuid(@Param("id") Long id,@Param("userUuid") String userUuid);

    /**
     * 根据用户UUID查询用户信息
     * @param uuid
     * @return
     */
    TmUser queryUserInfoByUuid(@Param("uuid") String uuid);
}