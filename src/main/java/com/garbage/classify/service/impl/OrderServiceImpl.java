package com.garbage.classify.service.impl;


import com.garbage.classify.constant.Constant;
import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TmOrderDetailMapper;
import com.garbage.classify.dao.TmOrderMapper;
import com.garbage.classify.dao.TmRedPackageMapper;
import com.garbage.classify.model.Base.PageBean;
import com.garbage.classify.model.dto.OrderDetailDto;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.model.dto.OrderListDto;
import com.garbage.classify.model.enums.EnumClassifyType;
import com.garbage.classify.model.enums.EnumOrderStatus;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmOrder;
import com.garbage.classify.model.po.TmOrderDetail;
import com.garbage.classify.model.po.TmRedPackage;
import com.garbage.classify.model.po.TmUser;
import com.garbage.classify.model.vo.OrderVo;
import com.garbage.classify.service.inf.CreateWxOrderService;
import com.garbage.classify.service.inf.EnergyGenerateService;
import com.garbage.classify.service.inf.OrderService;
import com.garbage.classify.service.inf.UserService;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
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

    @Autowired
    private EnergyGenerateService energyGenerateService;

    @Autowired
    private UserService userService;

    @Autowired
    private CreateWxOrderService createWxOrderService;

    @Override
    @Transactional
    public String addOrder(OrderDto orderDto) {
        logger.info("下单 [{}]",orderDto);
        orderDto.validateAndInit();
        // 获取下单人信息
        TmUser tmUser = userService.queryUserInfoByUuid(orderDto.getUserUuid());
        if(ToolUtil.isEmpty(tmUser)){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"用户信息获取失败！");
        }
        BigDecimal payPrice = new BigDecimal(0);
        TmOrder tmOrder = new TmOrder();
        BeanUtils.copyProperties(orderDto, tmOrder);
        tmOrder.setOrderNo(this.getOrderNo("oder"));
        tmOrder.setOrderStatus(EnumOrderStatus.toPay.getStatusCode());
        for (OrderDetailDto that :orderDto.getOrderDetailDtos()){
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
            payPrice = payPrice.add(calPrice);
            tmOrderDetailMapper.insertSelective(tmOrderDetail);
        }

        tmOrder.setPayPrice(payPrice);
        // 获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 生成预支付订单
        String unifiedOrder = createWxOrderService.createUnifiedOrder(request, String.valueOf(payPrice.floatValue() * 100), tmUser.getOpenId(), tmOrder.getOrderNo());
        logger.info("下单预订单号 [{}]",unifiedOrder);
        tmOrder.setTradeNo(unifiedOrder);
        tmOrderMapper.insertSelective(tmOrder);
        return unifiedOrder;
    }

    @Override
    @Transactional
    public void cancelUserOrder(String orderNo, String remark) {
        logger.info("取消订单 [{}],[{}]",orderNo,remark);
        if (ToolUtil.isEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        if (ToolUtil.isEmpty(remark)){
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
        logger.info("工人取消订单 [{}],[{}]",orderNo,remark);
        if (ToolUtil.isEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号不能为空");
        }
        if (ToolUtil.isEmpty(remark)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "原因不能为空");
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
        logger.info("开始处理订单 [{}]",orderNo);
        if (ToolUtil.isEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号不能为空");
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
        logger.info("完成订单 [{}]",orderNo);
        if (ToolUtil.isEmpty(orderNo)){
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
    public void grabOrder(String orderNo, String uuid) {
        logger.info("抢单 orderNo[{}] uuid[{}]",orderNo,uuid);
        if (ToolUtil.isEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号 不能为空");
        }
        if (ToolUtil.isEmpty(uuid)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "抢单人uuid 不能为空");
        }
        TmUser tmUser = userService.queryUserInfoByUuid(uuid);
        if(ToolUtil.isEmpty(tmUser)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED,"用户信息不存在！");
        }
        if (tmOrderMapper.selectCountByworkId(uuid)>2){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "只允许同时进行两单");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)&&(tmOrder.getOrderStatus().equals(EnumOrderStatus.toDealWith.getStatusCode()))){
            tmOrder.setOrderStatus(EnumOrderStatus.toFinish.getStatusCode());
            tmOrder.setWorkUuid(uuid);
            tmOrder.setWorkName(tmUser.getUserName());
            tmOrder.setWorkId(tmUser.getId());
            tmOrder.setStartTime(new Date());
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
            logger.info("订单抢单成功！ 用户uuid[{}] 订单[{}]",uuid,orderNo);
            // 抢单生成能量
            energyGenerateService.createGrabOrderEnergy(uuid);
        }else {
            logger.info("抢单失败 订单信息[{}]",ToolUtil.isEmpty(tmOrder)?null:tmOrder.toString());
        }
    }

    @Override
    @Transactional
    public void payRollback(String orderNo, String payNo) {
        logger.info("支付成功回调 orderNo[{}] payNo[{}]",orderNo,payNo);
        if (ToolUtil.isEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号不能为空");
        }
        TmOrder tmOrder = tmOrderMapper.selectByOrderNo(orderNo);
        if (ToolUtil.isNotEmpty(tmOrder)){
            tmOrder.setOrderStatus(EnumOrderStatus.toDealWith.getStatusCode());
            tmOrder.setPayOrder(payNo);
            tmOrder.setPayTime(new Date());
            tmOrderMapper.updateByPrimaryKeySelective(tmOrder);
            // 生成绿色能量
            energyGenerateService.createOrderEnergy(tmOrder.getUserUuid());
        }
    }

    @Override
    public OrderVo getOrderInfo(String orderNo) {
        logger.info("根据订单号获取详情 orderNo[{}]",orderNo);
        if (ToolUtil.isEmpty(orderNo)){
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "订单号不能为空");
        }
        return tmOrderMapper.selectVoInfoByOrderNo(orderNo);
    }

    @Override
    public List<OrderVo> grabOrderList(String longitude,String latitude) {
        logger.info("获取抢单列表 longitude[{}],latitude[{}]",longitude,latitude);
        return tmOrderMapper.grabOrderList(longitude, latitude);
    }

    @Override
    public PageBean<OrderVo> myOrderList(OrderListDto orderListDto) {
        logger.info("我的订单列表 [{}]",orderListDto);
        PageBean<OrderVo> pageBean = new PageBean<>();
        orderListDto.validateAndInit();
        int count = tmOrderMapper.getCountMyOrder(orderListDto);
        PageHelper.startPage(orderListDto.getStart(), orderListDto.getLength());
        List<OrderVo> myOrder = tmOrderMapper.getMyOrder(orderListDto);
        pageBean.setData(myOrder);
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
