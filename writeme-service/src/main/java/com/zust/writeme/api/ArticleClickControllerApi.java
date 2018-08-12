package com.zust.writeme.api;

import com.zust.writeme.service.ArticleClickService.ArticleClickService;
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

@Api(value="点赞管理",description = "点赞管理")
@RequestMapping(value = "/article_click")
@Controller
public class ArticleClickControllerApi {
    private static final Logger log = LoggerFactory.getLogger(ArticleClickControllerApi.class);
    @Autowired
    private ArticleClickService articleClickService;

    @ApiOperation(value = "点赞")
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> like(
            @ApiParam(name = "articleId", value = "文章ID", required = true)
            @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam(value = "userId", required = true) int userId) {
        int eff = articleClickService.like(articleId,userId);
        ApiResponse res = new ApiResponse(200,"ok",eff);
        return ApiResponse.successResponse(res);
    }

    @ApiOperation(value = "取消点赞")
    @RequestMapping(value = "/nolike", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> nolike(
            @ApiParam(name = "articleId", value = "文章ID", required = true)
            @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam(value = "userId", required = true) int userId) {
        int eff = articleClickService.nolike(articleId,userId);
        ApiResponse res = new ApiResponse(200,"ok",eff);
        return ApiResponse.successResponse(res);
    }

    @ApiOperation(value = "判断是否已经点赞")
    @RequestMapping(value = "/judge", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> isExist(
            @ApiParam(name = "articleId", value = "文章ID", required = true)
            @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam(value = "userId", required = true) int userId) {
        boolean eff = articleClickService.isExist(articleId,userId);
        ApiResponse res = new ApiResponse(200,"ok",eff);
        return ApiResponse.successResponse(res);
    }
}
