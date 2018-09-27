/* *
 * @author Administrator
 * @create 2018/8/8
 */
package com.zust.writeme.service.userServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.UserMapper;
import com.zust.writeme.model.User;
import com.zust.writeme.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int validAccount(String account, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userAccount", account);
        if (password != null && password.length() != 0) {
            criteria.andEqualTo("userPassword", password);
        }
        return userMapper.selectCountByExample(example);
    }

    @Override
    public int validAccount(String account) {
        return validAccount(account, null);
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getLoginUser(String account, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userAccount", account);
        criteria.andEqualTo("userPassword", password);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public Pagination<User> getTotalUser(String status, int pageNum, int pageSize) {
        Pagination<User> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", status);
        List<User> userList = userMapper.selectByExample(example);

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

    @Override
    public Pagination<User> selectUserListByName(String name, String status, int pageNum, int pageSize) {
        Pagination<User> pagination = new Pagination<>();

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userName", "%" + name + "%");
        criteria.andEqualTo("status", status);
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectByExample(example);

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

    @Override
    public Pagination<User> getMyRecommentUserList(int userId, int pageNum, int pageSize) {
        Pagination<User> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getMyRecommentUserList(userId);

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

    @Override
    public Pagination<User> getUserListByUserAccount(String account, String status, int pageNum, int pageSize) {
        Pagination<User> pagination = new Pagination<>();

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userAccount", "%" + account + "%");
        criteria.andEqualTo("status", status);
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectByExample(example);

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

    @Override
    public int userManage(int userId, String status) {
        User user = new User();
        user.setUserId(userId);
        user.setStatus(status);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int modifyPwd(int userId, String newPwd) {
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword(newPwd);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int userSetting(int userId, String userName, String sex, String userAbstract) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setSex(sex);
        user.setUserAbstract(userAbstract);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int modifyPic(int userId, String imageUrl) {
        User user = new User();
        user.setUserId(userId);
        user.setUserImage(imageUrl);
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
