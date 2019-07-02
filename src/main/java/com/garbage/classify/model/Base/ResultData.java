package com.garbage.classify.model.Base;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: guweifeng
 * @Desription:     返回页面的类
 * @Date: Created in 下午1:36 2017/12/1
 * @Modified By:
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private int status;

    private String errCode;

    private String errMsg;

    private T data;

    public static final int SUCCESS = 1;

    public static final int FAIL = 0;

}
