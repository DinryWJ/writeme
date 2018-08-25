package com.zust.writeme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
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
     * 评论时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    @Column(name = "commented_user_id")
    private Integer commentedUserId;

    @Column(name = "parent_comment_id")
    private Integer parentCommentId;

    @Column(name = "is_read")
    private String isRead;

    @Transient
    private User user;

    @Transient
    private List<Comment> commentList;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentedUserId() {
        return commentedUserId;
    }

    public void setCommentedUserId(Integer commentedUserId) {
        this.commentedUserId = commentedUserId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}