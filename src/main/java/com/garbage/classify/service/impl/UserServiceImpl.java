package com.garbage.classify.service.impl;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TmUserMapper;
import com.garbage.classify.model.dto.UserInfo.UserInfoDto;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmUser;
import com.garbage.classify.service.inf.UserService;
import com.garbage.classify.utils.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.xa.XAException;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private TmUserMapper tmUserMapper;

    /**
     * 用户信息新增和维护
     * @param userInfoDto
     * @return
     */
    @Override
    public String userInfoMainTain(UserInfoDto userInfoDto) {
        log.info("新增或编辑用户信息 详情和[{}]",userInfoDto.toString());
        TmUser tmUser=new TmUser();
        if(!ToolUtil.isEmpty(userInfoDto)){
            BeanUtils.copyProperties(userInfoDto,tmUser);
            // 获取用户信息
            String openId = userInfoDto.getOpenId();
            TmUser user = tmUserMapper.queryUserInfoByOpenId(openId);
            if(!ToolUtil.isEmpty(user)){
                // 更新用户信息
                tmUser.setId(user.getId());
                tmUser.setUuid(user.getUuid());
                tmUserMapper.updateByPrimaryKeySelective(tmUser);
            }else {
                // 新增用户信息
                String uuid = UUID.randomUUID().toString();
                tmUser.setUuid(uuid);
                tmUserMapper.insertSelective(tmUser);
            }
        }
        log.info("新增或编辑用户信息成功！ UUID[{}]",tmUser.getUuid());
        return tmUser.getUuid();
    }

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    @Override
    public UserInfoDto queryUserInfoByOpenId(String openId) {
        log.info("根据OpenId获取用户信息 OpenId[{}]",openId);
        TmUser tmUser = tmUserMapper.queryUserInfoByOpenId(openId);
        if(ToolUtil.isEmpty(tmUser)){
            throw new ZyTechException(ErrConstant.BUSI_RETURN_ERR,"未查询到用户信息！");
        }
        UserInfoDto userInfoDto=new UserInfoDto();
        BeanUtils.copyProperties(tmUser,userInfoDto);
        return userInfoDto;
    }
}
