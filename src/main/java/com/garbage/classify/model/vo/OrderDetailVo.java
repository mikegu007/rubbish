package com.garbage.classify.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo  implements Serializable {
    private Integer id;

    private String orderNo;

    private Byte classifyType;

    private Integer classifyCount;

    private String classifyName;


}