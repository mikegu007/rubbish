package com.garbage.classify.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto implements Serializable {
    private Integer id;

    private String orderNo;

    private Byte classifyType;

    private Integer classifyCount;

    private String classifyName;


}