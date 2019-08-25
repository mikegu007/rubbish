package com.garbage.classify.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 用户领取绿色能量Dto
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户领取绿色能量")
public class DrawEnergyDto {

    @NotNull(message = "用户UUID信息不能为空!")
    @ApiModelProperty(name = "uuid",value = "用户uuid")
    private String uuid;

    @NotNull(message = "领取能量ID信息不能为空!")
    @ApiModelProperty(name = "energyId",value = "用户能量ID")
    private Long energyId;
}
