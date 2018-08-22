package com.zust.writeme.model;

import java.util.Date;
import javax.persistence.*;

public class Article {
    /**
     * 文章id
     */
    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 文章标题
     */
    private String title;

    @Column(name = "article_preview")
    private String articlePreview;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 文集id
     */
    @Column(name = "corpus_id")
    private Integer corpusId;

    /**
     * 0未发布 1发布
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;

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
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return article_preview
     */
    public String getArticlePreview() {
        return articlePreview;
    }

    /**
     * @param articlePreview
     */
    public void setArticlePreview(String articlePreview) {
        this.articlePreview = articlePreview;
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
     * 获取文集id
     *
     * @return corpus_id - 文集id
     */
    public Integer getCorpusId() {
        return corpusId;
    }

    /**
     * 设置文集id
     *
     * @param corpusId 文集id
     */
    public void setCorpusId(Integer corpusId) {
        this.corpusId = corpusId;
    }

    /**
     * 获取0未发布 1发布
     *
     * @return status - 0未发布 1发布
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0未发布 1发布
     *
     * @param status 0未发布 1发布
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取文章内容
     *
     * @return article_content - 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置文章内容
     *
     * @param articleContent 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}