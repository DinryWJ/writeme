package com.zust.writeme.api;

import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.service.articleClickService.ArticleClickService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Api(value = "点赞管理", description = "点赞管理")
@RequestMapping(value = "/articleClick")
@Controller
public class ArticleClickApi {
    private static final Logger log = LoggerFactory.getLogger(ArticleClickApi.class);
    @Autowired
    private ArticleClickService articleClickService;

    @ApiOperation(value = "点赞")
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> like(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if (!articleClickService.isExist(articleId,userId)){
                int eff = articleClickService.like(articleId, userId);
                return ApiResponse.successResponse(eff);
            }else {
                return ApiResponse.errorResponse("你已点过赞了！");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }

    }

    @ApiOperation(value = "取消点赞")
    @RequestMapping(value = "/nolike", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> nolike(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if (articleClickService.isExist(articleId,userId)){
                int eff = articleClickService.nolike(articleId, userId);
                return ApiResponse.successResponse(eff);
            }else {
                return ApiResponse.errorResponse("你已取消点赞！");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "获取文章赞数")
    @RequestMapping(value = "/getStarsCount", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getStarsCount(
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        int count = articleClickService.getStarsCount(articleId);
        return ApiResponse.successResponse(count);
    }

    @ApiOperation(value = "判断是否已经点赞")
    @RequestMapping(value = "/judge", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> isExist(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            boolean eff = articleClickService.isExist(articleId, userId);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }
}
