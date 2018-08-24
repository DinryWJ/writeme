package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.Article;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.*;

import javax.persistence.Id;
import java.util.List;

public interface ArticleMapper extends MyMapper<Article> {
    @Select("select * from article where user_id = #{userId} and status = #{status}")
    @Results({
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "title", property = "title"),
            @Result(column = "article_content", property = "articleContent"),
            @Result(column = "article_preview", property = "articlePreview"),
            @Result(column = "cover_img", property = "coverImg"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "corpus_id", property = "corpusId"),
            @Result(column = "status", property = "status"),
            @Result(column = "create_time", property = "createTime"),
            @Result(property = "author", column = "user_id", javaType = User.class,
                    one = @One(select = "com.zust.writeme.dao.UserMapper.selectByPrimaryKey"))
    })
    List<Article> getArticleListByUserId(@Param("userId") int userId, @Param("status") int status);
}