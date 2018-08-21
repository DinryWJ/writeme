/* *
 * @author Administrator
 * @create 2018/8/8
 */
package com.zust.writeme.service.userservice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.UserMapper;
import com.zust.writeme.model.User;
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
        criteria.andLike("userName","%"+name+"%");

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectByExample(example);

        pagination.setList(userList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) userList).getTotal());
        return pagination;
    }

//
//    @Override
//    public int deleteUser(int uid) {
//        return userMapper.deleteUser(uid);
//
//    }
//
//    @Override
//    public void updateName(int uid, String name) {
//        //User user=userMapper.selectByPrimaryKey(uid);
//        List<User> listUser=userMapper.selectByUserName(name);
//        if (listUser.size()>0)
//            System.out.println("请修改用户名");
//        else
//            userMapper.updateUserName(uid,name);
//    }
//
//    @Override
//    public void updatePassword(String account, String password, String newpassword) {
//        int uid;
//            User user=userMapper.selectByAccount(account);
//            if (user.getUserPassword().equals(password)) {
//                uid = user.getUserId();
//                userMapper.updatePassword(uid,newpassword);
//            }
//            else
//                System.out.println("账户密码错误");
//    }


}
