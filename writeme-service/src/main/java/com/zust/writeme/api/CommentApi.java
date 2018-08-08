package com.zust.writeme.api;

import com.zust.writeme.model.Comment;
import com.zust.writeme.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="评论管理",description = "评论管理")
@RequestMapping(value = "comment")
@RestController
public class CommentApi {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取所有评论详细信息")
    @RequestMapping(value = "/getAllComment", method = RequestMethod.POST)
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
    }

    @ApiOperation(value = "评论详细信息", notes = "根据url的id来获取评论详细信息")
    @RequestMapping(value = "/selectComment", method = RequestMethod.POST)
    public Comment getComment(
            @ApiParam(name = "CommentId", value = "评论ID", required = true)
            @RequestParam(value = "CommentId", required = true) int id) {
        Comment comment = commentService.selectCommentById(id);
        System.out.println(comment.getCommentContent());
        return comment;
    }

    @ApiOperation(value = "删除评论", notes = "根据url的id来获取评论详细信息")
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public boolean delete(
            @ApiParam(name = "CommentId", value = "评论ID", required = true)
            @RequestParam(value = "CommentId", required = true) int id) {
        boolean d = commentService.delete(id);
        if (d)
            return true;
        else
            return false;
    }


    @ApiOperation(value = "更新评论")
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public boolean updateComment(
            @ApiParam(name = "CommentId", value = "评论ID", required = true)
            @RequestParam(value = "CommentId") int commentId,
            @ApiParam(name = "Comment", value = "评论更新内容json格式", required = true)
            @RequestBody Comment comment) {
        if (comment != null) {
            commentService.update(commentId, comment);
            return true;
        }
        return false;
}
    @ApiOperation(value="添加评论", notes="添加评论")
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    public boolean add(
            @ApiParam(name = "Comment", value = "添加评论内容json格式", required = true)
            @RequestBody Comment comment){
        if (comment!=null) {
            commentService.add(comment);
            return true;
        }
        return false;
    }
}
