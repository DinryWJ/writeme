package com.zust.writeme.service.userservice;

import com.zust.writeme.model.User;

import java.util.List;

public interface UserService   {


    public void insertUser(User user);

    public List<User> selectAll();

    public User selectByAccount(String account);

    public List<User> selectByUserName(String name);

    public  boolean deleteUser(int uid);
    public  void updateName(int uid, String name );
    public  void  updatePassword(String account,String password,String newpassword );


}
