package com.garbage.classify.model.dto;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.utils.ToolUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDto implements Serializable{
    private Long id;

    private String userUuid;

    private String name;

    private String mobile;

    private String address;

    private String addressDetail;

    private Byte sign;

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