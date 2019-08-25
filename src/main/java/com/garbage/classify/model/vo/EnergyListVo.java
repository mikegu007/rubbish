package com.garbage.classify.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 可领取能量列表Vo
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "能量获取列表Vo")
public class EnergyListVo {

    @ApiModelProperty(name = "id",value = "能量ID")
    private Long id;

    @ApiModelProperty(name = "uuid",value = "用户UUID")
    private String uuid;

    @ApiModelProperty(name = "energy",value = "能量值（g）")
    private Long energy;

    @ApiModelProperty(name = "type",value = "能量类型 0 二恶英 1 绿色能量")
    private Integer type;

}
