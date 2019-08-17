package com.garbage.classify.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo  implements Serializable {
    private Integer id;
    @ApiModelProperty(name = "orderNo", value = "订单号", required = true)
    private String orderNo;
    @ApiModelProperty(name = "classifyType", value = "垃圾类型", required = true)
    private Byte classifyType;
    @ApiModelProperty(name = "classifyCount", value = "垃圾数量", required = true)
    private Integer classifyCount;
    @ApiModelProperty(name = "classifyName", value = "垃圾名称", required = true)
    private String classifyName;


}