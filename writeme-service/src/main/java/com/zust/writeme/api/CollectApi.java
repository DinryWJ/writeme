package com.zust.writeme.api;

import com.zust.writeme.service.collectService.CollectService;
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

@Api(value = "收藏管理", description = "收藏管理")
@RequestMapping(value = "/collect")
@Controller
public class CollectApi {
    private static final Logger log = LoggerFactory.getLogger(CollectApi.class);
    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "收藏")
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> like(
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {
        int eff = collectService.collect(articleId, userId);
        return ApiResponse.successResponse(eff);
    }

    @ApiOperation(value = "取消收藏")
    @RequestMapping(value = "/nocollect", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> nolike(
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {
        int eff = collectService.nocollect(articleId, userId);
        return ApiResponse.successResponse(eff);
    }

    @ApiOperation(value = "判断是否已经收藏")
    @RequestMapping(value = "/judge", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> isExist(
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {
        boolean eff = collectService.isExist(articleId, userId);
        return ApiResponse.successResponse(eff);
    }
}
