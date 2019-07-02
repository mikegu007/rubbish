package com.garbage.classify.service.impl;


import com.garbage.classify.constant.Constant;
import com.garbage.classify.dao.TmOrderMapper;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.service.inf.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Mike
 * @Date: 2019/1/3 9:36
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Autowired
    private TmOrderMapper tmOrderMapper;


    @Override
    public void addOrder(OrderDto orderDto) {

    }
}
