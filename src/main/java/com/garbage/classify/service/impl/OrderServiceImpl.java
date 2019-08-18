package com.garbage.classify.service.impl;


import com.garbage.classify.constant.Constant;
import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TmOrderDetailMapper;
import com.garbage.classify.dao.TmOrderMapper;
import com.garbage.classify.dao.TmRedPackageMapper;
import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.model.dto.OrderListDto;
import com.garbage.classify.model.enums.EnumClassifyType;
import com.garbage.classify.model.enums.EnumOrderStatus;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmOrder;
import com.garbage.classify.model.po.TmOrderDetail;
import com.garbage.classify.model.po.TmRedPackage;
import com.garbage.classify.model.vo.OrderVo;
import com.garbage.classify.service.inf.OrderService;
import com.garbage.classify.utils.DateUtil;
import com.garbage.classify.utils.ToolUtil;
import com.github.pagehelper.PageHelper;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private TmOrderDetailMapper tmOrderDetailMapper;

    @Autowired
    private TmRedPackageMapper tmRedPackageMapper;

    @Override
    @Transactional
    public void addOrder(OrderDto orderDto) {
        orderDto.validateAndInit();
        BigDecimal payPrice = new BigDecimal(0);
        TmOrder tmOrder = new TmOrder();
        BeanUtils.copyProperties(orderDto, tmOrder);
        tmOrder.setOrderNo(this.getOrderNo("oder"));
        tmOrder.setOrderStatus(EnumOrderStatus.toPay.getStatusCode());
        orderDto.getOrderDetailDtos().forEach(that ->{
            TmOrderDetail tmOrderDetail = new TmOrderDetail();
            BeanUtils.copyProperties(that, tmOrderDetail);
            tmOrderDetail.setOrderNo(tmOrder.getOrderNo());
            BigDecimal calPrice = new BigDecimal(0);
            if (that.getClassifyType().equals(EnumClassifyType.big.getStatusCode())){
                calPrice = Constant.BIG_ORDER_PRICE.multiply(new BigDecimal(that.getClassifyCount()));
            }else if (that.getClassifyType().equals(EnumClassifyType.middle.getStatusCode())){
                calPrice = Constant.MIDDLE_ORDER_PRICE.multiply(new BigDecimal(that.getClassifyCount()));
            }else if (that.getClassifyType().equals(EnumClassifyType.small.getStatusCode())){
                calPrice = Constant.SMALL_ORDER_PRICE.multiply(new BigDecimal(that.getClassifyCount()));
            }
            BigDecimal discountPrice = new BigDecimal(0);
            if (ToolUtil.isNotEmpty(orderDto.getRedId())){
                TmRedPackage tmRedPackage = tmRedPackageMapper.selectByPrimaryKey(orderDto.getRedId());
                if (ToolUtil.isNotEmpty(tmRedPackage)&& (tmRedPackage.getExpireTime().getTime()>System.currentTimeMillis())){
                    discountPrice = tmRedPackageMapper.selectByPrimaryKey(orderDto.getRedId()).getPackagePrice();
                }
            }
            calPrice.subtract(discountPrice);
            payPrice.add(calPrice);
            tmOrderDetailMapper.insertSelective(tmOrderDetail);
        });
        tmOrder.setPayPrice(payPrice);
        tmOrderMapper.insertSelective(tmOrder);
    }

    @Override
    @Transactional
    public void cancelUserOrder(String orderNo, String remark) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        if (ToolUtil.isNotEmpty(remark)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "原因 不能为空");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)&&tmOrder.getOrderStatus().equals(EnumOrderStatus.toPay.getStatusCode())){
            tmOrder.setOrderStatus(EnumOrderStatus.cancel.getStatusCode());
            tmOrder.setRemark(tmOrder.getRemark()+";"+remark);
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
        }
    }

    @Override
    @Transactional
    public void cancelWokerOrder(String orderNo, String remark) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        if (ToolUtil.isNotEmpty(remark)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "原因 不能为空");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)&&tmOrder.getOrderStatus().equals(EnumOrderStatus.toFinish.getStatusCode())){
            tmOrder.setOrderStatus(EnumOrderStatus.toDealWith.getStatusCode());
            tmOrder.setWorkUuid(null);
            tmOrder.setWorkId(null);
            tmOrder.setWorkName(null);
            tmOrder.setStartTime(new Date());
            tmOrder.setRemark(tmOrder.getRemark()+";"+remark);
            tmOrderMapper.updateByPrimaryKey(tmOrder);
        }
    }

    @Override
    @Transactional
    public void updateDealWithStatus(String orderNo) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)){
            tmOrder.setOrderStatus(EnumOrderStatus.toFinish.getStatusCode());
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
        }
    }

    @Override
    @Transactional
    public void finishOrder(String orderNo) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)){
            tmOrder.setFinishTime(new Date());
            tmOrder.setOrderStatus(EnumOrderStatus.finish.getStatusCode());
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
        }
    }

    @Override
    @Transactional
    public void grabOrder(String orderNo, String uuid, Long workId, String workName) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        if (ToolUtil.isNotEmpty(uuid)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "抢单人uuid 不能为空");
        }
        if (tmOrderMapper.selectCountByworkId(uuid)>2){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "只允许同时进行两单");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)&&(!tmOrder.getOrderStatus().equals(EnumOrderStatus.toDealWith.getStatusCode()))){
            tmOrder.setOrderStatus(EnumOrderStatus.toDealWith.getStatusCode());
            tmOrder.setWorkUuid(uuid);
            tmOrder.setWorkId(workId);
            tmOrder.setWorkName(workName);
            tmOrder.setStartTime(new Date());
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
        }
    }

    @Override
    @Transactional
    public void payRollback(String orderNo, String payNo) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        if (ToolUtil.isNotEmpty(payNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "支付订单号 不能为空");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)){
            tmOrder.setOrderStatus(EnumOrderStatus.toDealWith.getStatusCode());
            tmOrder.setPayOrder(payNo);
            tmOrder.setPayTime(new Date());
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
        }
    }

    @Override
    public OrderVo getOrderInfo(String orderNo) {
        if (ToolUtil.isNotEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        return tmOrderMapper.selectVoInfoByOrderNo(orderNo);
    }

    @Override
    public List<OrderVo> grabOrderList(String longitude,String latitude) {
        return tmOrderMapper.grabOrderList(longitude, latitude);
    }

    @Override
    public PageBean<OrderVo> myOrderList(OrderListDto orderListDto) {
        PageBean<OrderVo> pageBean = new PageBean<>();
        orderListDto.validateAndInit();
        int count = tmOrderMapper.getCountMyOrder(orderListDto);
        PageHelper.startPage(orderListDto.getStart(), orderListDto.getLength());
        pageBean.setData(tmOrderMapper.getMyOrder(orderListDto));
        pageBean.setCount(count);
        return pageBean;
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
