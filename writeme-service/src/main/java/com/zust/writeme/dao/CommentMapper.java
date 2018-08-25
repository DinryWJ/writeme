package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.Comment;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {
    @Select("select * from comment where article_id = #{articleId}  and parent_comment_id = 0")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "commentContent", column = "comment_content"),
            @Result(property = "commentTime", column = "comment_time"),
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "commentedUserId", column = "commented_user_id"),
            @Result(property = "parentCommentId", column = "parent_comment_id"),
            @Result(property = "isRead", column = "is_read"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey")),
            @Result(property = "commentList", column = "comment_id", javaType = List.class,
                    many = @Many(select = "com.zust.writeme.dao.CommentMapper.getSecondComment"))
    })
    List<Comment> getArticleComment(@Param("articleId") int articleId);

    @Select("select * from comment where parent_comment_id = #{commentId}")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "commentContent", column = "comment_content"),
            @Result(property = "commentTime", column = "comment_time"),
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "commentedUserId", column = "commented_user_id"),
            @Result(property = "parentCommentId", column = "parent_comment_id"),
            @Result(property = "isRead", column = "is_read"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<Comment> getSecondComment(@Param("commentId") int commentId);
}