package com.zust.writeme.service.commentservice;

import com.zust.writeme.dao.CommentMapper;
import com.zust.writeme.model.Comment;
import com.zust.writeme.service.commentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getAllComment() {

        return commentMapper.selectAll();
    }

    @Override
    public int update(int CommentId ,Comment comment) {

        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public int add(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public int delete(int CommentId) {
        return commentMapper.deleteByPrimaryKey(CommentId);
    }


    @Override
    public Comment selectCommentById(int CommentId) {

        return commentMapper.selectByPrimaryKey(CommentId);

    }
}
