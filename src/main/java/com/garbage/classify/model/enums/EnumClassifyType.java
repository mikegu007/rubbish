package com.garbage.classify.model.enums;

import java.util.Objects;

/**
 * @author Mike
 */
public enum EnumClassifyType {

    big(new Byte("0"), "big", "大"),
    middle(new Byte("1"), "middle", "中"),
    smail(new Byte("2"), "smail", "小")


    ;

    private Byte statusCode;
    private String nameEN;
    private String nameCN;

    EnumClassifyType(Byte statusCode, String nameEN, String nameCN) {
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
        EnumClassifyType[] values = EnumClassifyType.values();
        for (EnumClassifyType value : values) {
            if (Objects.equals(value.statusCode, typeCode)) {
                return value.nameEN;
            }
        }
        return null;
    }

    public static String getNameCN(Byte typeCode) {
        EnumClassifyType[] values = EnumClassifyType.values();
        for (EnumClassifyType value : values) {
            if (Objects.equals(value.statusCode, typeCode)) {
                return value.nameCN;
            }
        }
        return null;
    }


}
