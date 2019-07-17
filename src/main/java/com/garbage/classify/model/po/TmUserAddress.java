package com.garbage.classify.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class TmUserAddress {
    private Long id;

    private String userUuid;

    private String name;

    private String mobile;

    private String address;

    private String addressDetail;

    private Byte sign;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean isDel;

    private String longitude;

    private String latitude;
}