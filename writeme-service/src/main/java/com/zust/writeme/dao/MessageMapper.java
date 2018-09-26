package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.Message;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/20 9:57
 */
public interface MessageMapper extends MyMapper<Message> {
    @Select("SELECT * FROM message AS m WHERE m.`status` = '0' AND ( to_user_id = #{userId} AND from_user_id = #{guestId}) OR ( to_user_id = #{guestId} AND from_user_id = #{userId}) ORDER BY create_time ASC")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "fromUserId",column = "from_user_id"),
            @Result(property = "toUserId",column = "to_user_id"),
            @Result(property = "message",column = "message"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "user", column = "from_user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<Message> getMessageRecord(@Param("userId") int userId, @Param("guestId") int guestId);

    @Select("SELECT * FROM ( SELECT * FROM message AS m WHERE m.`status` = #{status} AND from_user_id = #{fromUserId} ORDER BY create_time DESC) s GROUP BY s.to_user_id")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "fromUserId",column = "from_user_id"),
            @Result(property = "toUserId",column = "to_user_id"),
            @Result(property = "message",column = "message"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "user", column = "to_user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<Message> getFromUserMessageList(@Param("fromUserId") int fromUserId, @Param("status") String status);

    @Select("SELECT * FROM ( SELECT * FROM message AS m WHERE m.`status` = #{status} AND to_user_id = #{toUserId} ORDER BY create_time DESC) s GROUP BY s.from_user_id")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "fromUserId",column = "from_user_id"),
            @Result(property = "toUserId",column = "to_user_id"),
            @Result(property = "message",column = "message"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "user", column = "from_user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<Message> getToUserMessageList(@Param("toUserId")int toUserId, @Param("status") String status);
}
