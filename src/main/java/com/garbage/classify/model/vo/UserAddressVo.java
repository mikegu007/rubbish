package com.garbage.classify.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
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

    private Byte sign;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean isDel;

}