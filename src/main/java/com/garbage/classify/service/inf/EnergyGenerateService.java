package com.garbage.classify.service.inf;

import com.garbage.classify.model.po.TtEnergyGenerate;

import java.util.List;

public interface EnergyGenerateService {
    /**
     * 获取订单绿色能量
     * @param userUuid
     */
    void createOrderEnergy(String userUuid);


    /**
     * 创建系统释放的能量
     * @param uuid
     */
    void createSystemEnergy(String uuid);

    /**
     * 抢单生成能量
     * @param uuid
     */
    void createGrabOrderEnergy(String uuid);

    /**
     * 获取已经过期的能量信息
     * @return
     */
    List<TtEnergyGenerate> queryExpireEnergy();

    /**
     * 删除已经过期的能量
     * @param id
     */
    void deleteExpireEnergy(Long id);
}
