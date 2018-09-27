package com.zust.writeme.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "writeme-service-provider")
@RequestMapping(value = "/article")
public interface ArticleApi {
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> publish(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "preview", required = true) String preview,
            @RequestParam(value = "coverImg", required = true) String coverImg,
            @RequestParam(value = "corpusId", required = true) int corpusId
    );

    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveArticle(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "corpusId", required = true) int corpusId
    );

    @RequestMapping(value = "/republish", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> republish(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "articleId", required = true) int articleId,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "preview", required = true) String preview,
            @RequestParam(value = "coverImg", required = true) String coverImg,
            @RequestParam(value = "corpusId", required = true) int corpusId
    );

    @RequestMapping(value = "/getArticleListByUserId", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleListByUserId(
            @RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "status", required = true) int status,
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize
    );

    @RequestMapping(value = "/getArticleById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleById(
            @RequestParam(value = "articleId", required = true) int articleId
    );

    @RequestMapping(value = "/getArticleListByCondition", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleListByTitleName(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "flag", required = false) int flag,
            @RequestParam(value = "status", required = false) int status,
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize
    );

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> deleteArticle(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "articleId", required = true) int articleId
    );

    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateArticle(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "articleId", required = true) int articleId,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "corpusId", required = true) int corpusId
    );

    
    @RequestMapping(value = "/getMyRecommentArticleList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getLikeArticle(
            @RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize
    );
}
