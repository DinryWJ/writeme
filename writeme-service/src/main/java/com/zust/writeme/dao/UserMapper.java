package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    @Select("select * from user as u where u.user_account=#{account}")
    User selectByAccount(String account);

    @Select("select * from user as u where u.user_name=#{name}")
    List<User> selectByUserName(String name);

    @Delete("delete from user  where user.user_id=#{uid}")
    int deleteUser(int uid);

    @Update("update user  set user_password=#{password} where user_id=#{uid}")
    void updatePassword(int uid, String password);

    @Update("update user  set user_name=#{name} where user_id=#{uid}")
    void updateUserName(int uid, String name);

}