package com.garbage.classify.service.inf;

import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.dto.RedPackage.RedPackageInfoListDto;
import com.garbage.classify.model.dto.RedPackage.RedPackageInfoListVo;

public interface RedPackageService {

    /**
     * 根据用户UUID获取红包数量
     * @param uuid
     * @return
     */
    Integer queryRedPacketCountByUuid(String uuid);

    /**
     * 根据用户UUID获取用户红包列表
     * @param redPackageInfoListDto
     * @return
     */
    PageBean<RedPackageInfoListVo> queryRedPacketListByUuid(RedPackageInfoListDto redPackageInfoListDto);
}
