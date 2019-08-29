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
import java.util.List;

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
        insertNewEnergy(Constant.ENERGY_TYPE_ENERGY,userUuid,50);
    }

    /**
     * 创建系统释放的能量
     * @param uuid
     */
    @Override
    public void createSystemEnergy(String uuid) {
        insertNewEnergy(Constant.ENERGY_TYPE_ENERGY,uuid,30);
    }

    @Override
    public void createGrabOrderEnergy(String uuid) {
        insertNewEnergy(Constant.ENERGY_TYPE_ENERGY,uuid,10);
    }

    /**
     * 获取已经过期的能量信息
     * @return
     */
    @Override
    public List<TtEnergyGenerate> queryExpireEnergy() {
        log.info("获取所有已经过期的能量列表信息！");
        return ttEnergyGenerateMapper.queryExpireEnergy();
    }

    /**
     * 删除已经过期的能量
     * @param id
     */
    @Override
    public void deleteExpireEnergy(Long id) {
        log.info("删除已经过期的能量信息 ID[{}]",id);
        TtEnergyGenerate ttEnergyGenerate=new TtEnergyGenerate();
        ttEnergyGenerate.setId(id);
        ttEnergyGenerate.setExpireTime(new Date());
        ttEnergyGenerate.setIsDel(Constant.DATA_IS_DEL);
        ttEnergyGenerateMapper.updateBySelective(ttEnergyGenerate);
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
