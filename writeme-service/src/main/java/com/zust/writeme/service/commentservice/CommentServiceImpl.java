package com.zust.writeme.service.commentservice;

import com.zust.writeme.dao.CommentMapper;
import com.zust.writeme.model.Comment;
import com.zust.writeme.service.CommentService;
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
    public Integer update(int CommentId ,Comment comment) {

        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public void add(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public boolean delete(int CommentId) {
        commentMapper.deleteByPrimaryKey(CommentId);
        return true;
    }


    @Override
    public Comment selectCommentById(int CommentId) {

        return commentMapper.selectByPrimaryKey(CommentId);

    }
}
