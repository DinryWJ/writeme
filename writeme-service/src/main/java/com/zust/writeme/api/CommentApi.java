package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.Comment;
import com.zust.writeme.service.articleService.ArticleService;
import com.zust.writeme.service.commentService.CommentService;
import com.zust.writeme.service.userService.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "评论管理", description = "评论管理")
@RequestMapping(value = "/comment")
@Controller
public class CommentApi {

    private static final Logger log = LoggerFactory.getLogger(CommentApi.class);
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取文章所有评论详细信息")
    @RequestMapping(value = "/getArticleComment", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleComment(
            @ApiParam(name = "articleId", value = "文章id", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "pageNum", value = "评论开始位置", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(name = "pageSize", value = "评论条数", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Comment> commentList = commentService.getArticleComment(articleId, pageNum, pageSize);
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
    public ResponseEntity<ApiResponse> addComment(
            @ApiParam(name = "token", value = "token", required = true) @RequestParam(value = "token") String token,
            @ApiParam(name = "Comment", value = "添加评论内容json格式", required = true) @RequestBody Comment comment
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            comment.setCommentTime(new Date());
            comment.setUserId(userId);
            int eff = commentService.add(comment);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }

    }

    @ApiOperation(value = "未读评论列表", notes = "查看未读评论")
    @RequestMapping(value = "/getCommentList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getCommentList(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
           Pagination pagination = commentService.getNoReadCommentList(userId,pageNum,pageSize);

            return ApiResponse.successResponse(pagination);

    }


}
