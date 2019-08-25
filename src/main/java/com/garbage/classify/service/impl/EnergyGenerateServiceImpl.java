package com.garbage.classify.service.impl;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.dao.TtEnergyGenerateMapper;
import com.garbage.classify.model.po.TtEnergyGenerate;
import com.garbage.classify.service.inf.EnergyGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 绿色能量
 */
@Service
@Slf4j
public class EnergyGenerateServiceImpl implements EnergyGenerateService {

    @Autowired
    private TtEnergyGenerateMapper ttEnergyGenerateMapper;


    /**
     * 获取订单绿色能量
     * @param userUuid
     */
    @Override
    @Transactional
    public void createOrderEnergy(String userUuid) {

    }


    private Long insertNewEnergy(int type,String uuid,long energy){
        log.info("添加新的绿色能量记录 type[{}] uuid[{}] energy[{}]",type,uuid,energy);
        TtEnergyGenerate ttEnergyGenerate=new TtEnergyGenerate();
        ttEnergyGenerate.setCreateTime(new Date());
        ttEnergyGenerate.setEnergy(energy);
        ttEnergyGenerate.setIsDel(Constant.DATA_NOT_DEL);
        ttEnergyGenerate.setType(type);
        ttEnergyGenerate.setUuid(uuid);
        ttEnergyGenerateMapper.insertSelective(ttEnergyGenerate);
        return ttEnergyGenerate.getId();
    }
}
