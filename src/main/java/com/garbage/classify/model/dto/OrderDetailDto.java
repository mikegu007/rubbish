package com.garbage.classify.model.dto;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.utils.ToolUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto implements Serializable {
    @ApiModelProperty(name = "classifyType", value = "垃圾类型", required = true)
    private Byte classifyType;
    @ApiModelProperty(name = "classifyCount", value = "垃圾数量", required = true)
    private Integer classifyCount;
    @ApiModelProperty(name = "classifyName", value = "垃圾类型名称", required = true)
    private String classifyName;

    public void validateAndInit() {
        if (ToolUtil.isEmpty(classifyType)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "垃圾类型 不能为空");
        }
        if (ToolUtil.isEmpty(classifyCount)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "垃圾数量 不能为空");
        }
        if (ToolUtil.isEmpty(classifyName)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "垃圾类型名称 不能为空");
        }
    }

}