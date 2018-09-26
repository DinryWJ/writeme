package com.zust.writeme.service.articleClickService;


import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.ArticleClick;

import java.util.List;

public interface ArticleClickService {
    int like(int articleId, int userId);

    int nolike(int articleId, int userId);

    boolean isExist(int articleId, int userId);

    int getStarsCount(int articleId);

    List<ArticleClick> getNoReadArticleClickList(int userId);

    Pagination<ArticleClick> getFavourArticleIdList(int userId, int pageNum, int pageSize);
}
