package com.garbage.classify.model.dto;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.utils.ToolUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class OrderDto implements Serializable {

    @ApiModelProperty(name = "addressId", value = "地址id", required = true)
    private Long addressId;
    @ApiModelProperty(name = "addressName", value = "地址", required = true)
    private String addressName;
    @ApiModelProperty(name = "userUuid", value = "下单人uuid", required = true)
    private String userUuid;
    @ApiModelProperty(name = "userName", value = "下单人", required = true)
    private String userName;
    @ApiModelProperty(name = "mobile", value = "手机号", required = true)
    private String mobile;
    @ApiModelProperty(name = "remark", value = "备注", required = false)
    private String remark;

    private List<OrderDetailDto> orderDetailDtos;


    public void validateAndInit() {
        if (ToolUtil.isEmpty(addressId)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "地址id 不能为空");
        }
        if (ToolUtil.isEmpty(addressName)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "地址 不能为空");
        }
        if (ToolUtil.isEmpty(userUuid)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "下单人uuid 不能为空");
        }
        if (ToolUtil.isEmpty(userName)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "下单人 不能为空");
        }
        if (ToolUtil.isEmpty(mobile)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "手机号 不能为空");
        }
        orderDetailDtos.forEach(that ->{
            that.validateAndInit();
        });
    }

}