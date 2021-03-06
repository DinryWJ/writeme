package com.zust.writeme.service.commentService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Comment;

import java.util.List;

public interface CommentService {
    Pagination<Comment> getArticleComment(int articleId, int pageNum, int pageSize);

    int update(int CommentId, Comment comment);

    int add(Comment comment);

    int delete(int CommentId);

    Comment selectCommentById(int CommentId);

    Pagination getNoReadCommentList(int userId, int pageNum, int pageSize);
    
    int getCommentNumByArticleId(int articleId);

}
