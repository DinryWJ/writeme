package com.zust.writeme.service.CollectService;

public interface CollectService {
    int collect(int articleId,int userId);

    int nocollect(int articleId,int userId);

    boolean isExist(int articleId,int userId);
}
