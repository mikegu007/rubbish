package com.garbage.classify.service.impl;

import com.garbage.classify.model.po.TmUser;
import com.garbage.classify.model.po.TtEnergyGenerate;
import com.garbage.classify.service.inf.EnergyGenerateService;
import com.garbage.classify.service.inf.UserService;
import com.garbage.classify.utils.DateUtil;
import com.garbage.classify.utils.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 定时
 */

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class SystemEnergyTaskService {

    @Autowired
    private EnergyGenerateService energyGenerateService;

    @Autowired
    private UserService userService;

    /**
     * 定时任务每隔两个小时执行一次
     * 系统释放能量
     */
    @Scheduled(cron = "0 1 0/2 * * ?")
    private void configureTasks() {
        log.info("系统定时释放能量 时间[{}]", DateUtil.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        String format = dateFormat.format(new Date());
        // 判断时间
        if (format.equals("10") || format.equals("14") || format.equals("18") || format.equals("22")) {
            // 获取当前所有的用户
            List<TmUser> tmUserList = userService.queryAllUserInfo();
            if (ToolUtil.isNotEmpty(tmUserList) && tmUserList.size() > 0) {
                tmUserList.stream().forEach(x -> {
                    energyGenerateService.createSystemEnergy(x.getUuid());
                });
            }
        }
    }

    @Scheduled(cron = "0 0 0/2 * * ?")
    private void expireTasks() {
        log.info("系统定时任务回收能量 时间[{}]", DateUtil.getTime());
        List<TtEnergyGenerate> ttEnergyGenerateList = energyGenerateService.queryExpireEnergy();
        if (ToolUtil.isNotEmpty(ttEnergyGenerateList) && ttEnergyGenerateList.size() > 0) {
            ttEnergyGenerateList.stream().forEach(x -> {
                energyGenerateService.deleteExpireEnergy(x.getId());
            });
        }
    }

}
