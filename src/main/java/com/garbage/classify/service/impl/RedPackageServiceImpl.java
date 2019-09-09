package com.garbage.classify.service.impl;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.dao.TmRedPackageMapper;
import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.dto.RedPackage.RedPackageInfoListDto;
import com.garbage.classify.model.dto.RedPackage.RedPackageInfoListVo;
import com.garbage.classify.model.po.TmRedPackage;
import com.garbage.classify.service.inf.RedPackageService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    /**
     * 根据用户UUID获取用户红包列表
     * @param redPackageInfoListDto
     * @return
     */
    @Override
    public PageBean<RedPackageInfoListVo> queryRedPacketListByUuid(RedPackageInfoListDto redPackageInfoListDto) {
        log.info("获取用户红包列表信息 请求参数[{}]",redPackageInfoListDto.toString());
        PageBean pageBean=new PageBean();
        String userUuid = redPackageInfoListDto.getUserUuid();
        int length = redPackageInfoListDto.getLength();
        int start = redPackageInfoListDto.getStart();
        try{
            Integer count = tmRedPackageMapper.selectRedPacketCountByUuid(userUuid);
            PageHelper.startPage(start,length);
            List<TmRedPackage> tmRedPackageList = tmRedPackageMapper.queryRedPacketListByUuid(userUuid);
            pageBean.setCount(count);
            pageBean.setData(tmRedPackageList);
            pageBean.setCode(Constant.LAY_TABLE_SUCCESS);
            pageBean.setMsg("红包列表信息查询成功！");
        }catch (Throwable e){
            log.error("用户红包列表信息获取失败!",e);
            pageBean.setCount(0);
            pageBean.setCode(Constant.LAY_TABLE_SUCCESS);
            pageBean.setMsg("红包列表信息查询成功！");
            pageBean.setData(Collections.EMPTY_LIST);
        }
        return pageBean;
    }
}
