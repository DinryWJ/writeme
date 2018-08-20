package com.zust.writeme.service.userservice;

import com.zust.writeme.model.User;

import java.util.List;

public interface UserService {

    int insertUser(User user);

    List<User> selectAll();

    User selectByAccount(String account);

    List<User> selectByUserName(String name);

    int deleteUser(int uid);

    void updateName(int uid, String name);

    void updatePassword(String account, String password, String newpassword);


}
