package com.garbage.classify.model.dto.UserFeedBack;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户意见反馈
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户意见反馈内容")
public class FreeBackDto {

    @ApiModelProperty(name = "openId",value = "openId")
    private String openId;

    @ApiModelProperty(name = "content",value = "用户内容")
    private String content;
}
