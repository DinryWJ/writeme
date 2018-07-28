package com.zust.writeme.model;

import javax.persistence.*;

public class Corpus {
    /**
     * 文集表id
     */
    @Id
    @Column(name = "corpus_id")
    private Integer corpusId;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 文集名称
     */
    @Column(name = "corpus_name")
    private String corpusName;

    /**
     * 获取文集表id
     *
     * @return corpus_id - 文集表id
     */
    public Integer getCorpusId() {
        return corpusId;
    }

    /**
     * 设置文集表id
     *
     * @param corpusId 文集表id
     */
    public void setCorpusId(Integer corpusId) {
        this.corpusId = corpusId;
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

    /**
     * 获取文集名称
     *
     * @return corpus_name - 文集名称
     */
    public String getCorpusName() {
        return corpusName;
    }

    /**
     * 设置文集名称
     *
     * @param corpusName 文集名称
     */
    public void setCorpusName(String corpusName) {
        this.corpusName = corpusName;
    }
}