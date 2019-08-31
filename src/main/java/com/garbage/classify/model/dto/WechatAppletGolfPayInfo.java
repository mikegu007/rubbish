package com.garbage.classify.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WechatAppletGolfPayInfo {

    private String payId;

    private String openId;

    private Long amount;

    private String payStatus;
}
