package com.garbage.classify.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private Long id;

    private String orderNo;

    private Long addressId;

    private String addressName;

    private Long userId;

    private String userUuid;

    private String userName;

    private Long workId;

    private String workUuid;

    private String workName;

    private Byte orderStatus;

    private String mobile;

    private Date payTime;

    private String payOrder;

    private Long payPrice;

    private Date startTime;

    private Date finishTime;

    private String remark;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean isDel;

}