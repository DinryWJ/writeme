package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.Article;
import com.zust.writeme.model.User;
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

@Api(value = "文章管理", description = "文章管理")
@RequestMapping(value = "/article")
@RestController
public class ArticleApi {

    private static final Logger log = LoggerFactory.getLogger(ArticleApi.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleClickService articleClickService;

    @ApiOperation(value = "发布文章", notes = "发布文章")
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> publish(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "文章内容", name = "content", required = true) @RequestParam(value = "content", required = true) String content,
            @ApiParam(value = "文章预览", name = "preview", required = true) @RequestParam(value = "preview", required = true) String preview,
            @ApiParam(value = "文章封面", name = "coverImg", required = true) @RequestParam(value = "coverImg", required = true) String coverImg,
            @ApiParam(value = "文集id", name = "corpusId", required = true) @RequestParam(value = "corpusId", required = true) int corpusId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int status = 0;
            int eff = articleService.addArticle(title, content, preview, coverImg, corpusId, userId, status);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "新增保存文章", notes = "新增保存文章")
    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveArticle(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "文章内容", name = "content", required = true) @RequestParam(value = "content", required = true) String content,
            @ApiParam(value = "文集id", name = "corpusId", required = true) @RequestParam(value = "corpusId", required = true) int corpusId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int status = 2;
            int eff = articleService.addArticle(title, content, null, null, corpusId, userId, status);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "重新发布文章", notes = "重新发布文章")
    @RequestMapping(value = "/republish", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> republish(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "articleId", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "文章内容", name = "content", required = true) @RequestParam(value = "content", required = true) String content,
            @ApiParam(value = "文章预览", name = "preview", required = true) @RequestParam(value = "preview", required = true) String preview,
            @ApiParam(value = "文章封面", name = "coverImg", required = true) @RequestParam(value = "coverImg", required = true) String coverImg,
            @ApiParam(value = "文集id", name = "corpusId", required = true) @RequestParam(value = "corpusId", required = true) int corpusId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");

            Article article = articleService.getArticleById(articleId);
            if (article.getUserId() == userId) {
                article.setTitle(title);
                article.setArticleContent(content);
                article.setArticlePreview(preview);
                article.setCoverImg(coverImg);
                article.setCorpusId(corpusId);
                article.setStatus(0);
                int eff = articleService.updateArticle(article);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("无权发布这篇文章");
            }

        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "通过status获取用户文章列表", notes = "通过status获取用户文章列表")
    @RequestMapping(value = "/getArticleListByUserId", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleListByUserId(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId,
            @ApiParam(value = "status", name = "status", required = true) @RequestParam(value = "status", required = true) int status,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Article> pagination = articleService.getArticleListByUserId(userId, status, pageNum, pageSize);
        for (Article article : pagination.getList()) {
            article.setCommentNum(commentService.getCommentNumByArticleId(article.getArticleId()));
            article.setStarNum(articleClickService.getStarsCount(article.getArticleId()));
        }
        return ApiResponse.successResponse(pagination);
    }

    @ApiOperation(value = "通过id获取文章信息", notes = "通过id获取文章信息")
    @RequestMapping(value = "/getArticleById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleById(
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        Article article = articleService.getArticleById(articleId);
        if (article != null) {
            User author = userService.getUserById(article.getUserId());
            article.setStarNum(articleClickService.getStarsCount(articleId));
            article.setCommentNum(commentService.getCommentNumByArticleId(articleId));
            article.setAuthor(author);
            return ApiResponse.successResponse(article);
        } else {
            return ApiResponse.errorResponse("文章不存在");
        }
    }

    @ApiOperation(value = "文章条件查询", notes = "文章条件查询")
    @RequestMapping(value = "/getArticleListByCondition", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getArticleListByTitleName(
            @ApiParam(value = "value", name = "value", required = false) @RequestParam(value = "value", required = false) String value,
            @ApiParam(value = "1文章名，2用户id", name = "flag", required = false) @RequestParam(value = "flag", required = false) int flag,
            @ApiParam(value = "status", name = "status", required = false) @RequestParam(value = "status", required = false) int status,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Article> articleList = null;
        if (value != null) {
            if (flag == 1) {
                articleList = articleService.getArticleListByTitleName(value, status, pageNum, pageSize);
            }
            if (flag == 2) {
                articleList = articleService.getArticleListByUserId(Integer.parseInt(value), status, pageNum, pageSize);
            }
        } else {
            articleList = articleService.getArticleList(pageNum, pageSize);
        }

        return ApiResponse.successResponse(articleList);
    }

    @ApiOperation(value = "删除文章", notes = "删除文章")
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> deleteArticle(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            Article article = articleService.getArticleById(articleId);
            if (article.getUserId() == userId) {
                int eff = articleService.deleteArticleById(articleId);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("你无权删除此文章!");
            }

        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "更新保存文章", notes = "更新保存文章")
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateArticle(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "文章id", name = "articleId", required = true) @RequestParam(value = "articleId", required = true) int articleId,
            @ApiParam(value = "文章标题", name = "title", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "文章内容", name = "content", required = true) @RequestParam(value = "content", required = true) String content,
            @ApiParam(value = "文集id", name = "corpusId", required = true) @RequestParam(value = "corpusId", required = true) int corpusId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            Article article = articleService.getArticleById(articleId);
            if (article.getUserId() == userId) {
                int eff = articleService.updateArticle(articleId, title, content, corpusId);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("无权修改此文章");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }

    }

    @ApiOperation(value = "获取推荐文章", notes = "个性推荐")
    @RequestMapping(value = "/getMyRecommentArticleList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getLikeArticle(
            @ApiParam(value = "userId", name = "userId", required = true) @RequestParam(value = "userId", required = true) int userId,
            @ApiParam(value = "status", name = "status", required = true) @RequestParam(value = "status", required = true)int status ,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Article> pagination = articleService.getArticleListByCF(userId,status,pageNum,pageSize);

         return ApiResponse.successResponse(pagination);
    }
}
