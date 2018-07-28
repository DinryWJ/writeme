package com.zust.writeme.service;


import com.zust.writeme.dao.UserInfoMapper;
import com.zust.writeme.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author YFZX-WJJ-1778
 */
@Service
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public int update(UserInfo userInfo) {
        userInfo.setCreateTime(LocalDateTime.now());
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }


    @Override
    public UserInfo selectUserById(int userId) {
        UserInfo user = new UserInfo();
        user.setId(userId);
        return userInfoMapper.selectOne(user);
    }
}
