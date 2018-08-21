package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Comment;
import com.zust.writeme.service.commentService.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "评论管理", description = "评论管理")
@RequestMapping(value = "/comment")
@Controller
public class CommentApi {

    private static final Logger log = LoggerFactory.getLogger(CommentApi.class);
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取所有评论详细信息")
    @RequestMapping(value = "/getAllComment", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getAllComment(
            @ApiParam(name = "pageNum", value = "评论开始位置", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(name = "pageSize", value = "评论条数", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Comment> commentList = commentService.getAllComment(pageNum, pageSize);
        return ApiResponse.successResponse(commentList);
    }

    @ApiOperation(value = "评论详细信息", notes = "根据url的id来获取评论详细信息")
    @RequestMapping(value = "/selectComment", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getComment(
            @ApiParam(name = "CommentId", value = "评论ID", required = true) @RequestParam(value = "CommentId", required = true) int id
    ) {
        Comment comment = commentService.selectCommentById(id);
        return ApiResponse.successResponse(comment);
    }

    @ApiOperation(value = "删除评论", notes = "根据评论id来删除评论")
    @RequestMapping(value = "/deleteCommentById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> delete(
            @ApiParam(name = "CommentId", value = "评论ID", required = true) @RequestParam(value = "CommentId", required = true) int id
    ) {
        int eff = commentService.delete(id);
        return ApiResponse.successResponse(eff);
    }


    @ApiOperation(value = "更新评论", notes = "更新评论")
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateComment(
            @ApiParam(name = "CommentId", value = "评论ID", required = true) @RequestParam(value = "CommentId") int commentId,
            @ApiParam(name = "Comment", value = "评论更新内容json格式", required = true) @RequestBody Comment comment
    ) {
        int eff = commentService.update(commentId, comment);
        return ApiResponse.successResponse(eff);
    }

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> add(
            @ApiParam(name = "Comment", value = "添加评论内容json格式", required = true) @RequestBody Comment comment
    ) {
        int eff = commentService.add(comment);
        return ApiResponse.successResponse(eff);
    }
}
