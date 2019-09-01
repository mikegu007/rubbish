package com.garbage.classify.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmOrder {
    private Long id;

    private String orderNo;

    private Long addressId;

    private String addressName;

    private String longitude;
    private String latitude;

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

    private BigDecimal payPrice;

    private Date startTime;

    private Date finishTime;

    private String remark;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean isDel;

    private String tradeNo;
}