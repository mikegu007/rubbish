package com.garbage.classify.model.dto.RedPackage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "红包列表VO")
public class RedPackageInfoListVo {

    @ApiModelProperty(name = "id",value = "红包ID")
    private Long id;

    @ApiModelProperty(name = "packageName",value = "红包名称")
    private String packageName;

    @ApiModelProperty(name = "userUuid",value = "用户UUID")
    private String userUuid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "expireTime",value = "过期时间")
    private Date expireTime;

    @ApiModelProperty(name = "packagePrice",value = "红包金额")
    private Long packagePrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createDate",value = "创建时间")
    private Date createDate;
}
