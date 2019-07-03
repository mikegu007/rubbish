package com.garbage.classify.model.vo;

import com.garbage.classify.model.enums.EnumAddressSignType;
import com.garbage.classify.model.po.TmUserAddress;
import com.garbage.classify.utils.ToolUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressVo implements Serializable{
    private Long id;

    private String userUuid;

    private String name;

    private String mobile;

    private String address;

    private String addressDetail;

    private String sign;

    public UserAddressVo(TmUserAddress that) {
        if (ToolUtil.isNotEmpty(that)) {
            this.setId(that.getId());
            this.setUserUuid(that.getUserUuid());
            this.setAddress(that.getAddress());
            this.setAddressDetail(that.getAddressDetail());
            this.setName(that.getName());
            this.setMobile(that.getMobile());
            this.setSign(EnumAddressSignType.getNameCN(that.getSign()));
        }
    }


}