package com.garbage.classify.model.enums;

import java.util.Objects;

/**
 * @author Mike
 */
public enum EnumAddressSignType {

    home(new Byte("0"), "home", "家"),
    company(new Byte("1"), "company", "公司"),


    ;

    private Byte statusCode;
    private String nameEN;
    private String nameCN;

    EnumAddressSignType(Byte statusCode, String nameEN, String nameCN) {
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
        EnumAddressSignType[] values = EnumAddressSignType.values();
        for (EnumAddressSignType value : values) {
            if (Objects.equals(value.statusCode, typeCode)) {
                return value.nameEN;
            }
        }
        return null;
    }

    public static String getNameCN(Byte typeCode) {
        EnumAddressSignType[] values = EnumAddressSignType.values();
        for (EnumAddressSignType value : values) {
            if (Objects.equals(value.statusCode, typeCode)) {
                return value.nameCN;
            }
        }
        return null;
    }


}
