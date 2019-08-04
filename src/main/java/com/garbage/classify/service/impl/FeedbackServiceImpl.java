package com.garbage.classify.service.impl;

import com.garbage.classify.constant.Constant;
import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TmUserMapper;
import com.garbage.classify.dao.TtUserFeedbackMapper;
import com.garbage.classify.model.dto.UserFeedBack.FreeBackDto;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmUser;
import com.garbage.classify.model.po.TtUserFeedback;
import com.garbage.classify.service.inf.FeedbackService;
import com.garbage.classify.utils.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private TtUserFeedbackMapper ttUserFeedbackMapper;

    @Autowired
    private TmUserMapper tmUserMapper;

    /**
     * 新增反馈意见
     * @param freeBackDto
     * @return
     */
    @Override
    public Object addFeedback(FreeBackDto freeBackDto) {
        log.info("新增用户意见反馈 详情[{}]",freeBackDto.toString());
        String openId = freeBackDto.getOpenId();
        String content = freeBackDto.getContent();
        // 校验用户信息
        TmUser tmUser = tmUserMapper.queryUserInfoByOpenId(openId);
        if(ToolUtil.isEmpty(tmUser)){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"未查询到用户信息！");
        }
        TtUserFeedback feedback=new TtUserFeedback();
        feedback.setContent(content);
        feedback.setIsDel(0);
        feedback.setUserName(tmUser.getUserName());
        feedback.setUuid(tmUser.getUuid());
        ttUserFeedbackMapper.insertSelective(feedback);
        log.info("新增用户意见成功!");
        return null;
    }
}
