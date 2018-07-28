package com.zust.writeme.model;

import javax.persistence.*;

@Table(name = "recommend_user")
public class RecommendUser {
    /**
     * 用户id
     */
    @Id
    @Column(name = "recommend_id")
    private Integer recommendId;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 获取用户id
     *
     * @return recommend_id - 用户id
     */
    public Integer getRecommendId() {
        return recommendId;
    }

    /**
     * 设置用户id
     *
     * @param recommendId 用户id
     */
    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}