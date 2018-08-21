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
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
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
    public Pagination<User> getTotalUser(int pageNum, int pageSize) {
        Pagination<User> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll();

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

    @Override
    public Pagination<User> selectUserListByName(String name, int pageNum, int pageSize) {
        Pagination<User> pagination = new Pagination<>();

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userName", "%" + name + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectByExample(example);

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

}
