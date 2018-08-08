package com.zust.writeme.service;

import com.zust.writeme.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComment();

    Integer update(int CommentId,Comment comment);

    void add(Comment comment);

    boolean delete(int CommentId);

    Comment selectCommentById(int CommentId);
}
