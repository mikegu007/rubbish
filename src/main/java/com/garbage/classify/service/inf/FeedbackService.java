package com.garbage.classify.service.inf;

import com.garbage.classify.model.dto.UserFeedBack.FreeBackDto;

public interface FeedbackService {
    /**
     * 新增反馈意见
     * @param freeBackDto
     * @return
     */
    Object addFeedback(FreeBackDto freeBackDto);
}
