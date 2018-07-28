package com.zust.writeme.model;

import javax.persistence.*;

@Table(name = "recommend_article")
public class RecommendArticle {
    @Id
    @Column(name = "recommend_id")
    private Integer recommendId;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * @return recommend_id
     */
    public Integer getRecommendId() {
        return recommendId;
    }

    /**
     * @param recommendId
     */
    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
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
}