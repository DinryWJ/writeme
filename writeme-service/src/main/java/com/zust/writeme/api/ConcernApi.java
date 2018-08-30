package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.Concern;
import com.zust.writeme.model.User;
import com.zust.writeme.service.articleService.ArticleService;
import com.zust.writeme.service.concernService.ConcernService;
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

@Api(value = "关注管理", description = "关注管理")
@RequestMapping(value = "/concern")
@RestController
public class ConcernApi {

    private static final Logger log = LoggerFactory.getLogger(ConcernApi.class);

    @Autowired
    private ConcernService concernService;
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增关注", notes = "新增关注")
    @RequestMapping(value = "/addConcern", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addConcern(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "被关注的用户id", name = "concernedUserId", required = true) @RequestParam(value = "concernedUserId", required = true) int concernedUserId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if (userId != concernedUserId) {
                int eff = concernService.addConcern(userId, concernedUserId);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("不能关注自己！");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "取消关注", notes = "取消关注")
    @RequestMapping(value = "/deleteConcern", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> deleteConcern(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "被取消关注的用户id", name = "concernedUserId", required = true) @RequestParam(value = "concernedUserId", required = true) int concernedUserId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = concernService.deleteConcern(userId, concernedUserId);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "是否关注状态", notes = "取消关注")
    @RequestMapping(value = "/getConcernStatus", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getConcernStatus(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "用户id", name = "concernedUserId", required = true) @RequestParam(value = "concernedUserId", required = true) int concernedUserId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = concernService.getValidConcern(userId, concernedUserId);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "获取用户粉丝列表", notes = "获取用户粉丝列表")
    @RequestMapping(value = "/getUserFansList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserFansList(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Concern> pagination = concernService.getUserFansList(userId, pageNum, pageSize);
        for (Concern concern : pagination.getList()) {
            concern.setUserArticleCount(articleService.getUserArticleCount(concern.getConcernerUser().getUserId()));
            concern.setUserConcernedCount(concernService.getUserConcernCount(concern.getConcernerUser().getUserId()));
            User user= concern.getConcernerUser();
            user.setConcernStatus(concernService.getValidConcern(userId,user.getUserId()));
            concern.setConcernerUser(user);
        }
        return ApiResponse.successResponse(pagination);
    }

    @ApiOperation(value = "获取用户粉丝数", notes = "获取用户粉丝数")
    @RequestMapping(value = "/getUserFansCount", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserFansCount(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {
        int count = concernService.getUserFansCount(userId);
        return ApiResponse.successResponse(count);
    }

    @ApiOperation(value = "获取用户关注列表", notes = "获取用户关注列表")
    @RequestMapping(value = "/getUserConcernList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserConcernList(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Concern> pagination = concernService.getUserConcernList(userId, pageNum, pageSize);
        for (Concern concern : pagination.getList()) {
            concern.setUserArticleCount(articleService.getUserArticleCount(concern.getConcernedUser().getUserId()));
            concern.setUserConcernedCount(concernService.getUserConcernCount(concern.getConcernedUser().getUserId()));
            User user= concern.getConcernedUser();
            user.setConcernStatus(concernService.getValidConcern(userId,user.getUserId()));
            concern.setConcernedUser(user);
        }
        return ApiResponse.successResponse(pagination);
    }

    @ApiOperation(value = "获取用户关注数", notes = "获取用户关注数")
    @RequestMapping(value = "/getUserConcernCount", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserConcernCount(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {
        int count = concernService.getUserConcernCount(userId);
        return ApiResponse.successResponse(count);
    }
}
