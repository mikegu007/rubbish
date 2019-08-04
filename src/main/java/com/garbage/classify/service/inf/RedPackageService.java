package com.garbage.classify.service.inf;

public interface RedPackageService {

    /**
     * 根据用户UUID获取红包数量
     * @param uuid
     * @return
     */
    Integer queryRedPacketCountByUuid(String uuid);
}
