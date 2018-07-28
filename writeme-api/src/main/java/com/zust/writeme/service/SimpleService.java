package com.zust.writeme.service;

import com.zust.writeme.model.UserInfo;

/**
 * @author YFZX-WJJ-1778
 */
public interface SimpleService {
    int update(UserInfo userInfo);

    UserInfo selectUserById(int userId);
}
