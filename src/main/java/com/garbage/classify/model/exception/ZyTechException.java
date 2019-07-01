package com.garbage.classify.model.exception;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ZyTechException extends RuntimeException{

    private String errCode;

    private String errMsg;

    private static String ERR_PREFIX = "ZY_TECH_AUTH_";

    public ZyTechException(String errCode, String errMsg){
        this.errCode = ERR_PREFIX + errCode;
        this.errMsg = errMsg;
    }

    @Override
    public String getMessage() {
        return this.errMsg;
    }
}
