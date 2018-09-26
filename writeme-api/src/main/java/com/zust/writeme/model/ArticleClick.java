package com.zust.writeme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "article_click")
public class ArticleClick {
    /**
     * 点赞表id
     */
    @Id
    @Column(name = "click_table_id")
    private Integer clickTableId;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 点赞用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_read")
    private String isRead;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private Article article;

    @Transient
    private User user;
    /**
     * 获取点赞表id
     *
     * @return click_table_id - 点赞表id
     */
    public Integer getClickTableId() {
        return clickTableId;
    }

    /**
     * 设置点赞表id
     *
     * @param clickTableId 点赞表id
     */
    public void setClickTableId(Integer clickTableId) {
        this.clickTableId = clickTableId;
    }

    /**
     * 获取文章id
     *
     * @return article_id - 文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id
     *
     * @param articleId 文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取点赞用户id
     *
     * @return user_id - 点赞用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置点赞用户id
     *
     * @param userId 点赞用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}