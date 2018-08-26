package com.zust.writeme.service.concernService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Concern;

public interface ConcernService {
    int addConcern(int userId, int concernedUserId);

    int deleteConcern(int userId, int concernedUserId);

    int getValidConcern(int userId, int concernedUserId);

    Pagination<Concern> getUserFansList(int userId, int pageNum, int pageSize);

    int getUserFansCount(int userId);

    Pagination<Concern> getUserConcernList(int userId, int pageNum, int pageSize);

    int getUserConcernCount(int userId);
}
