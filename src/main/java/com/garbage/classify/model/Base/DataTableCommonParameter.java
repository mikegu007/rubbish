package com.garbage.classify.model.Base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 前台datatable传参公共类<br>
 * @Author: shenjian
 * @Desription:
 * @Date: Created in 下午5:14 2017/12/22
 * @Modified By:
 */
@ApiModel(value = "分页公共类", description = "分页公共类")
public class DataTableCommonParameter {

    /** 请求标识 */
    @ApiModelProperty(name = "draw", value = "请求标识（如果没有可不填）", required = false)
    private int draw;
    /** 当前请求开始下标 */
    @ApiModelProperty(name = "start", value = "当前页", required = true)
    private int start;
    /** 每页显示条数 */
    @ApiModelProperty(name = "length", value = "每页显示条数", required = true)
    private int length;

    /**
     * 初始化分页参数
     */
    public void init(){
        if(start<=0){
            start=1;
        }
        if(length<=0||length>10){
            length=10;
        }
    }

    public DataTableCommonParameter(){
        super();
    }

    public DataTableCommonParameter(int start,int length){
        this.start = start;
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCurrentPage() {
        return  start;
    }
}
