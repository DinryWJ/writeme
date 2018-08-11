package com.zust.writeme.service.commentService;

import com.zust.writeme.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComment();

    int update(int CommentId,Comment comment);

    int add(Comment comment);

    int delete(int CommentId);

    Comment selectCommentById(int CommentId);
}
