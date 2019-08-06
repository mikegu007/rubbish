package com.garbage.classify.model.dto.UserAddress;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.utils.ToolUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户地址")
public class UserAddressDto {

    @ApiModelProperty(name = "id",value = "用户地址ID")
    private Long id;

    @ApiModelProperty(name = "userUuid",value = "用户UUID")
    private String userUuid;

    @ApiModelProperty(name = "name",value = "用户名称")
    private String name;

    @ApiModelProperty(name = "mobile",value = "用户地址手机号码")
    private String mobile;

    @ApiModelProperty(name = "address",value = "用户地址")
    private String address;

    @ApiModelProperty(name = "addressDetail",value = "用户地址详情")
    private String addressDetail;

    @ApiModelProperty(name = "sign",value = "地址标签")
    private Byte sign;

    @ApiModelProperty(name = "longitude",value = "经度")
    private String longitude;

    @ApiModelProperty(name = "latitude",value = "纬度")
    private String latitude;

    @ApiModelProperty(name = "defaultAddress",value = "是否设置默认为默认地址",example = "true")
    private Boolean defaultAddress;

    public void validateAndInit() {
        if (ToolUtil.isEmpty(address)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "地址 不能为空");
        }
        if (ToolUtil.isEmpty(addressDetail)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "地址详情 不能为空");
        }
        if (ToolUtil.isEmpty(userUuid)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "下单人uuid 不能为空");
        }
        if (ToolUtil.isEmpty(mobile)) {
            throw new ZyTechException(ErrConstant.INVALID_DATAFILED, "手机号 不能为空");
        }
    }

}
