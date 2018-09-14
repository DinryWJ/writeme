package com.zust.writeme.model;

import javax.persistence.*;

public class Collect {
    /**
     * 收集表id
     */
    @Id
    @Column(name = "collect_id")
    private Integer collectId;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 值val
     */
    @Column(name = "val")
    private String val;
    /**
     * 获取收集表id
     *
     * @return collect_id - 收集表id
     */
    public Integer getCollectId() {
        return collectId;
    }

    /**
     * 设置收集表id
     *
     * @param collectId 收集表id
     */
    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}