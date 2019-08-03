package com.garbage.classify.model.dto.UserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户信息维护")
public class UserInfoDto {

    @ApiModelProperty(name = "uuid",value = "uuid")
    private String uuid;

    @ApiModelProperty(name = "userName",value = "用户名称")
    private String userName;

    @ApiModelProperty(name = "userSex",value = "用户性别")
    private Integer userSex;

    @ApiModelProperty(name = "userImage",value = "用户头像")
    private String userImage;

    @ApiModelProperty(name = "userMobile",value = "用户手机号码")
    private String userMobile;

    @ApiModelProperty(name = "defaultAddressId",value = "默认地址ID")
    private Long defaultAddressId;

    @NotNull(message = "微信OpenId信息不能为空！")
    @ApiModelProperty(name = "openId",value = "openId")
    private String openId;
}
