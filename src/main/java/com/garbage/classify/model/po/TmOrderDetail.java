package com.garbage.classify.model.po;

public class TmOrderDetail {
    private Integer id;

    private String orderNo;

    private Byte classifyType;

    private Integer classifyCount;

    private String classifyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Byte getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(Byte classifyType) {
        this.classifyType = classifyType;
    }

    public Integer getClassifyCount() {
        return classifyCount;
    }

    public void setClassifyCount(Integer classifyCount) {
        this.classifyCount = classifyCount;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }
}