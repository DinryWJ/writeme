/* *
 * @author Administrator
 * @create 2018/8/8
 */
package com.zust.writeme.service.userservice;

import com.zust.writeme.dao.UserMapper;
import com.zust.writeme.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        User u=user;
        userMapper.insert(u);
        return 0;
    }

    @Override
    public List<User> selectAll() {

        return userMapper.selectAll();
    }

    @Override
    public User selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public List<User> selectByUserName(String name) {
        return userMapper.selectByUserName(name);
    }

    @Override
    public int deleteUser(int uid) {
        return userMapper.deleteUser(uid);

    }

    @Override
    public void updateName(int uid, String name) {
        //User user=userMapper.selectByPrimaryKey(uid);
        List<User> listUser=userMapper.selectByUserName(name);
        if (listUser.size()>0)
            System.out.println("请修改用户名");
        else
            userMapper.updateUserName(uid,name);
    }

    @Override
    public void updatePassword(String account, String password, String newpassword) {
        int uid;
            User user=userMapper.selectByAccount(account);
            if (user.getUserPassword().equals(password)) {
                uid = user.getUserId();
                userMapper.updatePassword(uid,newpassword);
            }
            else
                System.out.println("账户密码错误");
    }
}
