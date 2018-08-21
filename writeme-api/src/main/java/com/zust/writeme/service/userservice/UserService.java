package com.zust.writeme.service.userservice;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.User;

import java.util.List;

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
     * @return
     */
    Pagination<User> getTotalUser(int pageNum, int pageSize);

    /**
     * 根据用户名搜索用户
     * @param name,pageNum,pageSize
     * @return
     */
    Pagination<User> selectUserListByName(String name,int pageNum, int pageSize);

//    int deleteUser(int uid);
//
//    void updateName(int uid, String name);
//
//    void updatePassword(String account, String password, String newpassword);
//

}
