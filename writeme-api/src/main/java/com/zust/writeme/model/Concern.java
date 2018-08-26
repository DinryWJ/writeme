package com.zust.writeme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

public class Concern {
    /**
     * 关注表id
     */
    @Id
    @Column(name = "concern_id")
    private Integer concernId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 被关注的用户的id
     */
    @Column(name = "concerned_id")
    private Integer concernedId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private User concernerUser;
    @Transient
    private User concernedUser;
    @Transient
    private int userArticleCount;
    @Transient
    private int userConcernedCount;

    public Integer getConcernId() {
        return concernId;
    }

    public void setConcernId(Integer concernId) {
        this.concernId = concernId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getConcernedId() {
        return concernedId;
    }

    public void setConcernedId(Integer concernedId) {
        this.concernedId = concernedId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getConcernerUser() {
        return concernerUser;
    }

    public void setConcernerUser(User concernerUser) {
        this.concernerUser = concernerUser;
    }

    public User getConcernedUser() {
        return concernedUser;
    }

    public void setConcernedUser(User concernedUser) {
        this.concernedUser = concernedUser;
    }

    public int getUserArticleCount() {
        return userArticleCount;
    }

    public void setUserArticleCount(int userArticleCount) {
        this.userArticleCount = userArticleCount;
    }

    public int getUserConcernedCount() {
        return userConcernedCount;
    }

    public void setUserConcernedCount(int userConcernedCount) {
        this.userConcernedCount = userConcernedCount;
    }
}