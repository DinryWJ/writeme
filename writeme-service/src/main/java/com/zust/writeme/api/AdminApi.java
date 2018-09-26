package com.zust.writeme.api;

import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.service.articleClickService.ArticleClickService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 吴佳杰
 * @date: 2018/9/4 22:33
 * @description:
 */
@Api(value = "管理端-管理", description = "管理端-管理")
@RequestMapping(value = "/manage")
@RestController
public class AdminApi {
    private static final Logger log = LoggerFactory.getLogger(ArticleApi.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleClickService articleClickService;

    @ApiOperation(value = "用户封禁管理", notes = "用户封禁管理")
    @RequestMapping(value = "/userManage", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> userManage(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "用户id", name = "currentId", required = true) @RequestParam(value = "currentId", required = true) int currentId,
            @ApiParam(value = "状态", name = "status", required = true) @RequestParam(value = "status", required = true) String status
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if ("1".equals(userService.getUserById(userId).getUserPermission())) {
                if ("1".equals(userService.getUserById(currentId).getUserPermission())) {
                    return ApiResponse.errorResponse("无法封禁管理员!");
                }
                int eff = userService.userManage(currentId, status);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("无权限!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "文章封禁管理", notes = "文章封禁管理")
    @RequestMapping(value = "/articleManage", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> articleManage(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(value = "状态", name = "status", required = true) @RequestParam(value = "status", required = true) int status
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if ("1".equals(userService.getUserById(userId).getUserPermission())) {
                int eff = articleService.articleManage(articleId, status);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("你无权删除此文章!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "文章审核管理", notes = "文章审核管理")
    @RequestMapping(value = "/articlePassInfo", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> articlePassInfo(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(value = "状态", name = "status", required = true) @RequestParam(value = "status", required = true) int status,
            @ApiParam(value = "信息", name = "msg", required = true) @RequestParam(value = "msg", required = true) String msg
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if ("1".equals(userService.getUserById(userId).getUserPermission())) {
                int eff = articleService.articleManage(articleId, status);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("无权限!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }
}
