package com.garbage.classify.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PayOrderVo", description = "PayOrderVo")
public class PayOrderVo implements Serializable {

    @ApiModelProperty(name = "orderNo", value = "订单号", required = true)
    private String orderNo;


    @ApiModelProperty(name = "preOrderNo", value = "预订单号", required = true)
    private String preOrderNo;



}