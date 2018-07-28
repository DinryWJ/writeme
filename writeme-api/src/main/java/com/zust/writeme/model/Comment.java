package com.zust.writeme.model;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    /**
     * 评论表id
     */
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 评论时间戳
     */
    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 评论用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 获取评论表id
     *
     * @return comment_id - 评论表id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置评论表id
     *
     * @param commentId 评论表id
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取评论内容
     *
     * @return comment_content - 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置评论内容
     *
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 获取评论时间戳
     *
     * @return comment_time - 评论时间戳
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * 设置评论时间戳
     *
     * @param commentTime 评论时间戳
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
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
     * 获取评论用户id
     *
     * @return user_id - 评论用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置评论用户id
     *
     * @param userId 评论用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}