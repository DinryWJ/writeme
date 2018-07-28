package com.zust.writeme.model;

import javax.persistence.*;

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

    /**
     * 获取关注表id
     *
     * @return concern_id - 关注表id
     */
    public Integer getConcernId() {
        return concernId;
    }

    /**
     * 设置关注表id
     *
     * @param concernId 关注表id
     */
    public void setConcernId(Integer concernId) {
        this.concernId = concernId;
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

    /**
     * 获取被关注的用户的id
     *
     * @return concerned_id - 被关注的用户的id
     */
    public Integer getConcernedId() {
        return concernedId;
    }

    /**
     * 设置被关注的用户的id
     *
     * @param concernedId 被关注的用户的id
     */
    public void setConcernedId(Integer concernedId) {
        this.concernedId = concernedId;
    }
}