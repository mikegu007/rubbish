package com.garbage.classify.controller.PersonalCenter;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.model.Base.ResultData;
import com.garbage.classify.model.dto.OrderDto;
import com.garbage.classify.model.dto.UserFeedBack.FreeBackDto;
import com.garbage.classify.service.inf.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed/back")
@Api(value = "用户意见反馈", tags = "用户意见反馈")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @ApiOperation(value = "新增反馈意见", notes = "新增反馈意见")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultData addFeedback(
            @ApiParam(name = "",value = "")
            @RequestBody FreeBackDto freeBackDto) {
        return new ResultData(ResultData.SUCCESS, "", "意见反馈成功！", feedbackService.addFeedback(freeBackDto));
    }
}
