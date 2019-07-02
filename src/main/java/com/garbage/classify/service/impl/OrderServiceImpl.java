package com.garbage.classify.service.impl;


import com.garbage.classify.constant.Constant;
import com.garbage.classify.dao.TmOrderMapper;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.model.enums.EnumOrderStatus;
import com.garbage.classify.model.po.TmOrder;
import com.garbage.classify.service.inf.OrderService;
import com.garbage.classify.utils.ToolUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: Mike
 * @Date: 2019/1/3 9:36
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Resource(name = "authCenterRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private TmOrderMapper tmOrderMapper;


    @Override
    @Transactional
    public void addOrder(OrderDto orderDto) {
        orderDto.validateAndInit();
        TmOrder tmOrder = new TmOrder();
        BeanUtils.copyProperties(orderDto, tmOrder);
        tmOrder.setOrderNo(this.getOrderNo("oder"));
        tmOrder.setOrderStatus(EnumOrderStatus.toPay.getStatusCode());
    }

    /**
     * 合同编号生成
     *
     * @param orgCode
     * @return
     */
    public String getOrderNo(String orgCode) {
        String contractIdKey = orgCode + DateFormatUtils.format(new Date(), "yyyyMMdd");
        long todayNum = 0;
        // 取redis缓存
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(contractIdKey, redisTemplate.getConnectionFactory());
        todayNum = entityIdCounter.getAndIncrement();
        long nextNum = todayNum + 1;
        // 设置redis缓存
        // 拼接后缀值
        String suffix = String.valueOf(nextNum);
        int suffixLength = suffix.length();
        if (suffixLength < 4) {
            for (int i = 0; i < 4 - suffixLength; i++) {
                suffix = "0" + suffix;
            }
        }
        return contractIdKey + suffix;
    }
}
