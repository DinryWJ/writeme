package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.Concern;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ConcernMapper extends MyMapper<Concern> {
    @Select("select * from concern where user_id = #{userId}")
    @Results({
            @Result(property = "concernId", column = "concern_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "concernedId", column = "concerned_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "isRead",column = "is_read"),
            @Result(property = "concernedUser", column = "concerned_id", javaType = User.class, one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<Concern> getUserConcernList(@Param("userId") int userId);

    @Select("select * from concern where concerned_id = #{userId}")
    @Results({
            @Result(property = "concernId", column = "concern_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "concernedId", column = "concerned_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "isRead",column = "is_read"),
            @Result(property = "concernerUser", column = "user_id", javaType = User.class, one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey")),
    })
    List<Concern> getUserFansList(@Param("userId") int userId);

    @Select("select * from concern where concerned_id = #{concernedId} and is_read='0'")
    @Results({
            @Result(property = "concernId", column = "concern_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "concernedId", column = "concerned_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "isRead",column = "is_read"),
            @Result(property = "concernerUser", column = "user_id", javaType = User.class, one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey")),
    })
    List<Concern> getNoReadConcernList(@Param("concernedId") int userId);
}