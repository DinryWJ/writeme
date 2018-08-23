package com.zust.writeme.service.articleService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Article;

/**
 * @author 82712
 */
public interface ArticleService {

    int addArticle(String title, String content, String preview, String coverImg, int corpusId, int userId);

    Article getArticleById(int articleId);

    Pagination<Article> getArticleListByTitleName(String title, int pageNum, int pageSize);

    int deleteArticleById(int articleId);

    int updateArticle(int articleId, String title, String content, int corpusId);

    Pagination<Article> getArticleListByUserId(int userId, int status, int pageNum, int pageSize);
}
