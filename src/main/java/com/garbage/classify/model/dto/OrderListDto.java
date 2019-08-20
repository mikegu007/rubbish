package com.garbage.classify.model.dto;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.model.Base.DataTableCommonParameter;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.utils.ToolUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "订单列表dto", description = "订单列表dto")
public class OrderListDto extends DataTableCommonParameter implements Serializable {


    @ApiModelProperty(name = "uuid", value = "uuid", required = true)
    private String uuid;


    public void validateAndInit() {
        init();
        if (ToolUtil.isEmpty(uuid)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "uuid 不能为空");
        }
    }

}