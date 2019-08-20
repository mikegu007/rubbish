package com.garbage.classify.model.Base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
@ApiModel(value = "分页信息", description = "分页信息")
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -1L;
    @ApiModelProperty(name = "code", value = "返回code", required = true)
    private String code;
    @ApiModelProperty(name = "msg", value = "返回信息", required = true)
    private String msg;
    @ApiModelProperty(name = "count", value = "返回数量", required = true)
    private Integer count;
    @ApiModelProperty(name = "data", value = "返回数据", required = true)
    private List<T> data;

     public PageBean() {
         super();
     }
    public PageBean(Integer count) {
         super();
        this.count = count;
     }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
