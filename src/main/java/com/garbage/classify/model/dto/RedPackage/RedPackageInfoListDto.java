package com.garbage.classify.model.dto.RedPackage;

import com.garbage.classify.model.Base.DataTableCommonParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "红包列表请求参数数据模型")
public class RedPackageInfoListDto extends DataTableCommonParameter {

    @ApiModelProperty(name = "userUuid",value = "用户UUID")
    private String userUuid;

    public void init(){
        int start = this.getStart();
        if(start<1){
            this.setStart(1);
        }
        int length = this.getLength();
        if(length<1){
            this.setLength(10);
        }
    }
}
