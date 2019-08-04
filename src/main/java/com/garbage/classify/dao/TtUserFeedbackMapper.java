package com.garbage.classify.dao;

import com.garbage.classify.model.po.TtUserFeedback;

public interface TtUserFeedbackMapper {
    int insert(TtUserFeedback record);

    int insertSelective(TtUserFeedback record);
}