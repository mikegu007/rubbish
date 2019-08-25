package com.garbage.classify.service.inf;

import com.garbage.classify.model.dto.DrawEnergyDto;
import com.garbage.classify.model.vo.EnergyListVo;

import java.util.List;

public interface EnergyService {

    /**
     *  获取用户能量列表
     * @param uuid
     * @return
     */
    List<EnergyListVo> getEnergyListByUuid(String uuid);

    /**
     * 用户领取能量
     * @param drawEnergyDto
     * @return
     */
    void drawEnergy(DrawEnergyDto drawEnergyDto);
}
