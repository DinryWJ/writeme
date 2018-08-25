package com.zust.writeme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "cover_img")
    private String coverImg;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;

    @Transient
    private User author;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticlePreview() {
        return articlePreview;
    }

    public void setArticlePreview(String articlePreview) {
        this.articlePreview = articlePreview;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCorpusId() {
        return corpusId;
    }

    public void setCorpusId(Integer corpusId) {
        this.corpusId = corpusId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}