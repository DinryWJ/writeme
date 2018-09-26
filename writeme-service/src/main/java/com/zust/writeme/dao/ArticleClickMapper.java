package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.Article;
import com.zust.writeme.model.ArticleClick;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleClickMapper extends MyMapper<ArticleClick> {

    @Select("select * from article_click where user_id = #{userId} and is_read='0'")
    @Results({
            @Result(property = "clickTableId", column = "click_table_id"),
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "isRead",column = "is_read"),
            @Result(property = "article", column = "articleId", javaType = Article.class,
                    one = @One(select = "com.zust.writeme.dao.ArticlerMapper.selectByPrimaryKey"))
    })
    List<ArticleClick> getNoReadConcernLists(@Param("userId") int userId);


    @Select("select * from article_click where user_id = #{userId}")
    @Results({
            @Result(property = "clickTableId", column = "click_table_id"),
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "isRead",column = "is_read"),
            @Result(property = "article", column = "article_id", javaType = Article.class,
                    one = @One(select = "com.zust.writeme.dao.ArticleMapper.selectByPrimaryKey")),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<ArticleClick> getFavourArticleIdList(@Param("userId") int userId);
}