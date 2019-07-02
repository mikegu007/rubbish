package com.garbage.classify.model.enums;

import java.util.Objects;

/**
 * @author Mike
 */
public enum EnumOrderStatus {
    cancel(new Byte("0"), "cancel", "取消"),
    toPay(new Byte("1"), "toPay", "待付款"),
    toDealWith(new Byte("2"), "toDealWith", "待抢单"),
    toFinish(new Byte("3"), "toFinish", "待完成"),
    finish(new Byte("4"), "finish", "完成"),
    ;

    private Byte statusCode;
    private String nameEN;
    private String nameCN;

    EnumOrderStatus(Byte statusCode, String nameEN, String nameCN) {
        this.statusCode = statusCode;
        this.nameEN = nameEN;
        this.nameCN = nameCN;
    }

    public Byte getStatusCode() {
        return statusCode;
    }

    public String getNameEN() {
        return nameEN;
    }

    public String getNameCN() {
        return nameCN;
    }

    public static String getNameEN(Byte typeCode) {
        EnumOrderStatus[] values = EnumOrderStatus.values();
        for (EnumOrderStatus value : values) {
            if (Objects.equals(value.statusCode, typeCode)) {
                return value.nameEN;
            }
        }
        return null;
    }

    public static String getNameCN(Byte typeCode) {
        EnumOrderStatus[] values = EnumOrderStatus.values();
        for (EnumOrderStatus value : values) {
            if (Objects.equals(value.statusCode, typeCode)) {
                return value.nameCN;
            }
        }
        return null;
    }


}
