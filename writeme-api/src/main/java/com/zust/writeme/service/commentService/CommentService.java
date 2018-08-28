package com.zust.writeme.service.commentService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Comment;

public interface CommentService {
    Pagination<Comment> getArticleComment(int articleId, int pageNum, int pageSize);

    int update(int CommentId, Comment comment);

    int add(Comment comment);

    int delete(int CommentId);

    Comment selectCommentById(int CommentId);

    int getCommentNumByArticleId(int articleId);

}
