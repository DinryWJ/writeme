package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
<<<<<<< HEAD

=======
    @Select("SELECT s.fromUserId, u.* " +
            "FROM " +
            "( SELECT c2.user_id AS fromUserId, c2.concerned_id AS toUserId " +
            "FROM concern AS c2 " +
            "WHERE c2.concerned_id != #{userId} " +
            "AND c2.user_id IN ( SELECT c.concerned_id FROM concern AS c WHERE c.user_id = #{userId}) " +
            "AND c2.concerned_id NOT IN ( SELECT c.concerned_id FROM concern AS c WHERE c.user_id = #{userId})" +
            ") AS s LEFT JOIN `user` AS u " +
            "ON s.toUserId = u.user_id")
    @Results({
            @Result(property = "userId",column = "user_id"),
            @Result(property = "userAccount",column = "user_account"),
            @Result(property = "userPassword",column = "user_password"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "userPermission",column = "user_permission"),
            @Result(property = "userAbstract",column = "user_abstract"),
            @Result(property = "status",column = "status"),
            @Result(property = "userImage",column = "user_image"),
            @Result(property = "fromUserId",column = "fromUserId"),
    })
    List<User> getMyRecommentUserList(@Param("userId") int userId);
>>>>>>> 80797ee3b7309e797b7650d85b5cea3c934c8664
}