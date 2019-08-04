package com.garbage.classify.service.impl;

import com.garbage.classify.dao.TmRedPackageMapper;
import com.garbage.classify.service.inf.RedPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedPackageServiceImpl implements RedPackageService {

    @Autowired
    private TmRedPackageMapper tmRedPackageMapper;

    /**
     * 根据uuid获取红包数量
     * @param uuid
     * @return
     */
    @Override
    public Integer queryRedPacketCountByUuid(String uuid) {
        log.info("根据用户UUID查询红包数量 UUID[{}]",uuid);
        return tmRedPackageMapper.selectRedPacketCountByUuid(uuid);
    }
}
