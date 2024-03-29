package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmUserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmUserAddressMapper {

    int insertSelective(TmUserAddress record);

    TmUserAddress selectByPrimaryKey(Long id);

    List<TmUserAddress> selectByUuid(String userUuid);

    int updateByPrimaryKeySelective(TmUserAddress record);

    List<TmUserAddress> queryUserAddressListByUuid(@Param("uuid") String uuid);

    int delUserAddressById(@Param("id") Long id);
}