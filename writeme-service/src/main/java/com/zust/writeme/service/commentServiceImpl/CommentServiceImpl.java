package com.zust.writeme.service.commentServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.ArticleMapper;
import com.zust.writeme.dao.CommentMapper;
import com.zust.writeme.model.Article;
import com.zust.writeme.model.Comment;
import com.zust.writeme.model.User;
import com.zust.writeme.service.commentService.CommentService;
import com.zust.writeme.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserService userMapper;

    @Override
    public Pagination<Comment> getArticleComment(int articleId, int pageNum, int pageSize) {
        Pagination<Comment> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.getArticleComment(articleId);

        pagination.setList(commentList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) commentList).getTotal());
        return pagination;
    }

    @Override
    public int update(int CommentId, Comment comment) {

        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public int add(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public int delete(int CommentId) {
        return commentMapper.deleteByPrimaryKey(CommentId);
    }


    @Override
    public Comment selectCommentById(int CommentId) {

        return commentMapper.selectByPrimaryKey(CommentId);

    }

    @Override
<<<<<<< HEAD
    public Pagination getNoReadCommentList(int userId, int pageNum, int pageSize) {
        Pagination<Map<String,Object>> pagination = new Pagination<>();
        List<Comment> comment1=commentMapper.getNoreadCommentList(userId);
        PageHelper.startPage(pageNum, pageSize);

        List<Map<String,Object>> list=new ArrayList<>();

        List<Comment> comment=commentMapper.getNoreadCommentList(userId);

        for (int i=0;i<comment.size();i++){
            Map map = new HashMap(5);
            Article article=articleMapper.selectByPrimaryKey(comment.get(i).getArticleId());
            User user=userMapper.getUserById(comment.get(i).getUserId());
            map.put("content",comment.get(i).getCommentContent());
            map.put("time",comment.get(i).getCommentTime());
            map.put("userId",user.getUserId());
            map.put("name",user.getUserName());
            map.put("img",article.getCoverImg());
            map.put("articleName",article.getTitle());
            map.put("articleId",article.getArticleId());
            list.add(map);
        }
        pagination.setList(list);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);

        pagination.setTotal((long)comment1.size());
        return pagination;
=======
    public int getCommentNumByArticleId(int articleId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        return commentMapper.selectCountByExample(example);
>>>>>>> 80797ee3b7309e797b7650d85b5cea3c934c8664
    }
}
