package com.zust.writeme.service.commentServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.CommentMapper;
import com.zust.writeme.model.Comment;
import com.zust.writeme.service.commentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public Pagination<Comment> getAllComment(int pageNum,int pageSize) {

        Pagination<Comment> pagination = new Pagination<>();
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNotNull("commentId");
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> commentList = commentMapper.selectByExample(example);

        pagination.setList(commentList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page)commentList).getTotal());
        return pagination;
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
