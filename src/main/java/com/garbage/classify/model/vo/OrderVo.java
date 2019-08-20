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
@ApiModel(value = "订单Vo", description = "订单Vo")
public class OrderVo  implements Serializable {
    private Long id;
    @ApiModelProperty(name = "orderNo", value = "订单号", required = true)
    private String orderNo;
    @ApiModelProperty(name = "addressId", value = "地址id", required = true)
    private Long addressId;
    @ApiModelProperty(name = "addressName", value = "地址名称", required = true)
    private String addressName;
    @ApiModelProperty(name = "userId", value = "下单人ID", required = true)
    private Long userId;
    @ApiModelProperty(name = "userUuid", value = "下单人uuid", required = true)
    private String userUuid;
    @ApiModelProperty(name = "userName", value = "下单人昵称", required = true)
    private String userName;
    @ApiModelProperty(name = "workId", value = "工作人id", required = true)
    private Long workId;
    @ApiModelProperty(name = "workUuid", value = "工作人uuid", required = true)
    private String workUuid;
    @ApiModelProperty(name = "workName", value = "工作人名称", required = true)
    private String workName;
    @ApiModelProperty(name = "orderStatus", value = "订单状态", required = true)
    private Byte orderStatus;
    @ApiModelProperty(name = "mobile", value = "下单人手机号", required = true)
    private String mobile;
    @ApiModelProperty(name = "payTime", value = "支付时间", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;
    @ApiModelProperty(name = "payOrder", value = "支付订单号", required = true)
    private String payOrder;
    @ApiModelProperty(name = "payPrice", value = "支付钱", required = true)
    private BigDecimal payPrice;
    @ApiModelProperty(name = "startTime", value = "开始时间", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(name = "finishTime", value = "结束时间", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;
    @ApiModelProperty(name = "remark", value = "备注", required = true)
    private String remark;

    @ApiModelProperty(name = "longitude",value = "经度")
    private String longitude;
    @ApiModelProperty(name = "latitude",value = "纬度")
    private String latitude;

    private List<OrderDetailVo> orderDetailVos;


}