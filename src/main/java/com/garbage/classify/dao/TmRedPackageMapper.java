package com.garbage.classify.dao;

import com.garbage.classify.model.po.TmRedPackage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmRedPackageMapper {
    int insert(TmRedPackage record);

    int insertSelective(TmRedPackage record);

    TmRedPackage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmRedPackage record);

    int updateByPrimaryKey(TmRedPackage record);

    Integer selectRedPacketCountByUuid(@Param("uuid") String uuid);

    List<TmRedPackage> queryRedPacketListByUuid(@Param("uuid") String userUuid);
}