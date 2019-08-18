package com.garbage.classify.service.impl;

import com.garbage.classify.service.inf.RemoveCacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class RemoveCacheServiceImpl implements RemoveCacheService {

    @Override
    @CacheEvict(value = "rubbish:user:address:list:uuid",key = "'rubbish:user:address:list:uuid:'+#p0")
    public void removeUserAddressByUuid(String userUuid) {

    }
}
