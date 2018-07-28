package com.zust.writeme.model;

import java.util.Date;
import javax.persistence.*;

public class Reply {
    /**
     * 回复表id
     */
    @Id
    @Column(name = "comment_table_id")
    private Integer commentTableId;

    /**
     * 回复内容
     */
    @Column(name = "reply_content")
    private String replyContent;

    /**
     * 回复时间
     */
    @Column(name = "reply_time")
    private Date replyTime;

    /**
     * 被评论的id
     */
    @Column(name = "replied_id")
    private Integer repliedId;

    /**
     * 评论用户id
     */
    @Column(name = "replier_id")
    private Integer replierId;

    /**
     * 获取回复表id
     *
     * @return comment_table_id - 回复表id
     */
    public Integer getCommentTableId() {
        return commentTableId;
    }

    /**
     * 设置回复表id
     *
     * @param commentTableId 回复表id
     */
    public void setCommentTableId(Integer commentTableId) {
        this.commentTableId = commentTableId;
    }

    /**
     * 获取回复内容
     *
     * @return reply_content - 回复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 设置回复内容
     *
     * @param replyContent 回复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 获取回复时间
     *
     * @return reply_time - 回复时间
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * 设置回复时间
     *
     * @param replyTime 回复时间
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 获取被评论的id
     *
     * @return replied_id - 被评论的id
     */
    public Integer getRepliedId() {
        return repliedId;
    }

    /**
     * 设置被评论的id
     *
     * @param repliedId 被评论的id
     */
    public void setRepliedId(Integer repliedId) {
        this.repliedId = repliedId;
    }

    /**
     * 获取评论用户id
     *
     * @return replier_id - 评论用户id
     */
    public Integer getReplierId() {
        return replierId;
    }

    /**
     * 设置评论用户id
     *
     * @param replierId 评论用户id
     */
    public void setReplierId(Integer replierId) {
        this.replierId = replierId;
    }
}