package com.zust.writeme.service.userService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.User;

public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 登陆判断
     *
     * @param account,password
     * @return
     */
    int validAccount(String account, String password);

    /**
     * 判断该账号是否存在
     *
     * @param account
     * @return
     */
    int validAccount(String account);

    /**
     * 通过Id搜索用户
     *
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 登录后获取用户
     *
     * @param account
     * @param password
     * @return
     */
    User getLoginUser(String account, String password);

    /**
     * 分页获取所有用户
     *
     * @param status
     * @return
     */
    Pagination<User> getTotalUser(String status, int pageNum, int pageSize);

    /**
     * 根据用户名搜索用户
     *
     * @param name,status,pageNum,pageSize
     * @return
     */
    Pagination<User> selectUserListByName(String name, String status, int pageNum, int pageSize);


    /**
     * 获取我的推荐用户
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<User> getMyRecommentUserList(int userId, int pageNum, int pageSize);

    /**
     * 根据账号搜索用户
     *
     * @param account
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<User> getUserListByUserAccount(String account, String status, int pageNum, int pageSize);

    /**
     * 用户封禁管理
     *
     * @param userId
     * @param status
     * @return
     */
    int userManage(int userId, String status);
}
