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
    //List getNoReadCommentList(int userId);

<<<<<<< HEAD
    Pagination getNoReadCommentList(int userId, int pageNum, int pageSize);
=======
    int getCommentNumByArticleId(int articleId);

>>>>>>> 80797ee3b7309e797b7650d85b5cea3c934c8664
}
