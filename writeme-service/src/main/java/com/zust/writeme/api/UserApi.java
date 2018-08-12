/* *
 * @author Administrator
 * @create 2018/8/9
 */
package com.zust.writeme.api;

import com.zust.writeme.model.User;
import com.zust.writeme.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="评论管理",description = "评论管理")
@RequestMapping(value = "user")
@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void insertUser(@RequestBody User user){
            userService.insertUser(user);
    };

    @ApiOperation(value = "陈列所有用户")
    @RequestMapping(value = "/listUser", method = RequestMethod.GET)
     public List<User> selectAll(){
        return  userService.selectAll();
    };
    @ApiOperation(value = "查询账号")
    @RequestMapping(value = "/selectAccount",method = RequestMethod.POST)
        public User selectByAccount(
            @ApiParam(name = "UserAccount", value = "用户账号", required = true)
            @RequestParam(value = "account", required = true)
                String account){

            return  userService.selectByAccount(account);
    }
    @ApiOperation(value = "查询用户名")
    @RequestMapping(value = "/selectName",method = RequestMethod.POST)
    public List<User> selectByUserName(
            @ApiParam(name = "UserName", value = "用户昵称", required = true)
            @RequestParam(value = "name", required = true)
            String name){
        return  userService.selectByUserName(name);
    }
@ApiOperation(value = "删除用户")
@RequestMapping(value = "/delete",method = RequestMethod.POST)
    public  boolean deleteUser(
        @ApiParam(name = "UserId", value = "用户ID", required = true)
        @RequestParam(value = "id", required = true)
                int uid){
            boolean tep=userService.deleteUser(uid);
            if (tep)
                return  true;
            else
                return  false;
    }
    @ApiOperation(value = "更改用户昵称")
    @RequestMapping(value = "/updateName",method = RequestMethod.POST)
    public  void updateName(
            @ApiParam(name = "UserId", value = "用户ID", required = true)
            @RequestParam(value = "id", required = true)
            int uid,
            @ApiParam(name = "UserName", value = "用户昵称", required = true)
            @RequestParam(value = "name", required = true)
            String name ){
        List<User> list=userService.selectByUserName(name);
        if (list.size()==0){
            userService.updateName(uid,name);
        }else
            System.out.println(name+"    用户名已经存在");
    }
    @ApiOperation(value = "更改密码")
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public  void  updatePassword(
            @ApiParam(name = "UserAccount", value = "用户账号", required = true)
            @RequestParam(value = "account", required = true)
            String account,
            @ApiParam(name = "UserPassword", value = "用户旧密码", required = true)
            @RequestParam(value = "password", required = true)
            String password,
            @ApiParam(name = "UserNewPassword", value = "用户新密码", required = true)
            @RequestParam(value = "newpassword", required = true)
            String newpassword ){
            User user =userService.selectByAccount(account);
            if (user.getUserPassword().equals(password))
                userService.updatePassword(account,password,newpassword);
    }

}
