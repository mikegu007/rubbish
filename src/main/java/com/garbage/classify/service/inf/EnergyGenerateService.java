package com.garbage.classify.service.inf;

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
}
