package com.zust.writeme.api;

import com.zust.writeme.model.Article;
import com.zust.writeme.service.articleservice.ArticleService;
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

import java.util.List;

@Api(value = "文章管理", description = "文章管理")
@RequestMapping(value = "/article")
@Controller
public class ArticleControllerApi {

    private static final Logger log = LoggerFactory.getLogger(ArticleControllerApi.class);

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增文章", notes = "新增文章")
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addArticle(
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "文章内容", name = "content", required = true) @RequestParam(value = "content", required = true) String content,
            @ApiParam(value = "文集id", name = "corpusId", required = true) @RequestParam(value = "corpusId", required = true) int corpusId,
            @ApiParam(value = "用户id", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {

        int eff = articleService.addArticle(title, content, corpusId, userId);

        ApiResponse res = new ApiResponse(200, "ok", eff);
        return ApiResponse.successResponse(res);

    }

    @ApiOperation(value = "通过id获取文章信息", notes = "通过id获取文章信息")
    @RequestMapping(value = "/getArticleById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleById(
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        Article article = articleService.getArticleById(articleId);

        ApiResponse res = new ApiResponse(200, "ok", article);
        return ApiResponse.successResponse(res);

    }

    @ApiOperation(value = "文章标题模糊查询", notes = "文章标题模糊查询")
    @RequestMapping(value = "/getArticleListByTitleName", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleListByTitleName(
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        List<Article> articleList = articleService.getArticleListByTitleName(title, pageNum, pageSize);

        ApiResponse res = new ApiResponse(200, "ok", articleList);
        return ApiResponse.successResponse(res);
    }

    @ApiOperation(value = "通过文章id删除文章", notes = "通过文章id删除文章")
    @RequestMapping(value = "/deleteArticleById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> deleteArticleById(
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        int eff = articleService.deleteArticleById(articleId);

        ApiResponse res = new ApiResponse(200, "ok", eff);
        return ApiResponse.successResponse(res);
    }

    @ApiOperation(value = "更新文章信息", notes = "更新文章信息")
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateArticle(
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "文章内容", name = "content", required = true) @RequestParam(value = "content", required = true) String content,
            @ApiParam(value = "文集id", name = "corpusId", required = true) @RequestParam(value = "corpusId", required = true) int corpusId
    ) {
        int eff = articleService.updateArticle(articleId, title, content, corpusId);

        ApiResponse res = new ApiResponse(200, "ok", eff);
        return ApiResponse.successResponse(res);
    }

}
