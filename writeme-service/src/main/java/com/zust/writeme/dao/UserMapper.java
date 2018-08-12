package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.User;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    User selectByAccount(String account);
    List<User> selectByUserName(String name);
    boolean deleteUser(int uid);
    void updatePassword(int uid ,String password);
    void updateUserName(int uid, String name);

}