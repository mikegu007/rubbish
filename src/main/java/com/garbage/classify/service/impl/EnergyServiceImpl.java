package com.garbage.classify.service.impl;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TtEnergyGenerateMapper;
import com.garbage.classify.model.dto.DrawEnergyDto;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmUser;
import com.garbage.classify.model.po.TtEnergyGenerate;
import com.garbage.classify.model.vo.EnergyListVo;
import com.garbage.classify.service.inf.EnergyService;
import com.garbage.classify.service.inf.UserService;
import com.garbage.classify.utils.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EnergyServiceImpl implements EnergyService {

    @Autowired
    private TtEnergyGenerateMapper ttEnergyGenerateMapper;

    @Autowired
    private UserService userService;
    /**
     * 获取用户能量列表
     * @param uuid
     * @return
     */
    @Override
//    @Cacheable(value = "rubbish:user:energy:list:uuid",key = "'rubbish:user:energy:list:uuid:'+#p0")
    public List<EnergyListVo> getEnergyListByUuid(String uuid) {
        log.info("获取用户可领取能量列表 用户uuid[{}]",uuid);
        List<TtEnergyGenerate> ttEnergyGenerates = ttEnergyGenerateMapper.getEnergyListByUuid(uuid, Constant.ENERGY_TYPE_ENERGY);
        List<EnergyListVo> energyListVos=new ArrayList<>();
        if(ToolUtil.isNotEmpty(ttEnergyGenerates)&&ttEnergyGenerates.size()>0){
            ttEnergyGenerates.stream().forEach(x->{
                EnergyListVo energyListVo=new EnergyListVo();
                BeanUtils.copyProperties(x,energyListVo);
                energyListVos.add(energyListVo);
            });
        }
        return energyListVos;
    }

    /**
     * 用户领取能量
     * @param drawEnergyDto
     */
    @Override
    public void drawEnergy(DrawEnergyDto drawEnergyDto) {
        log.info("用户领取能量 [{}]",drawEnergyDto);
        String uuid = drawEnergyDto.getUuid();
        Long energyId = drawEnergyDto.getEnergyId();
        // 校验用户信息
        TmUser tmUser = userService.queryUserInfoByUuid(uuid);
        if(ToolUtil.isEmpty(tmUser)){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"用户信息不存在！");
        }
        // 校验能量
        TtEnergyGenerate ttEnergyGenerate = ttEnergyGenerateMapper.selectById(energyId);
        if(ToolUtil.isEmpty(ttEnergyGenerate)){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"当前绿色能量已过期或不存在！");
        }else if(!uuid.equals(ttEnergyGenerate.getUuid())){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"当前绿色能量不属于您！");
        }
        // 领取能量

    }
}
