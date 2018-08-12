package com.zust.writeme.service.ArticleClickService;


public interface ArticleClickService  {
    int like(int articleId,int userId);

    int nolike(int articleId,int userId);

    boolean isExist(int articleId,int userId);
}
