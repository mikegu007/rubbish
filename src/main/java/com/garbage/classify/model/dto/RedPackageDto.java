package com.garbage.classify.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedPackageDto implements Serializable {
    private Long id;

    private String packageName;

    private String userUuid;

    private Date expireTime;

    private Long packagePrice;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean isDel;

}