package com.garbage.classify.service.inf;

public interface RemoveCacheService {

    /**
     * 清除用户地址
     * @param userUuid
     */
    void removeUserAddressByUuid(String userUuid);
}
