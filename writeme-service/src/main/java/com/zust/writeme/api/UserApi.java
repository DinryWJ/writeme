/* *
 * @author Administrator
 * @create 2018/8/9
 */
package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.User;
import com.zust.writeme.service.userservice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user")
@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> insertUser(
            @ApiParam(value = "账号", name = "account", required = true) @RequestParam(value = "account", required = true) String account,
            @ApiParam(value = "密码", name = "password", required = true) @RequestParam(value = "password", required = true) String password,
            @ApiParam(value = "重复密码", name = "repassword", required = true) @RequestParam(value = "repassword", required = true) String repassword
    ) {

        if (!password.equals(repassword)) {
            return ApiResponse.errorResponse("两次密码不能相同");
        } else {
            int count = userService.validAccount(account);
            if (count>0){
                return ApiResponse.errorResponse("账号已存在");
            }
            User user = new User();
            user.setUserAccount(account);
            user.setUserPassword(password);
            user.setSex("M");
            user.setUserName("新用户" + LocalDate.now().toString());
            user.setUserPermission(0);
            user.setStatus(true);
            int eff = userService.insertUser(user);
            return ApiResponse.successResponse(eff);
        }
    }

    @ApiOperation(value = "陈列所有用户")
    @RequestMapping(value = "/listUser", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> selectAllUser(
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<User> pagination = userService.getTotalUser(pageNum,pageSize);
        return ApiResponse.successResponse(pagination);
    }
//
//
//
//    @ApiOperation(value = "查询用户名")
//    @RequestMapping(value = "/selectName", method = RequestMethod.POST)
//    public List<User> selectByUserName(
//            @ApiParam(name = "UserName", value = "用户昵称", required = true) @RequestParam(value = "name", required = true) String name
//    ) {
//        return userService.selectByUserName(name);
//
//    }
//
//    @ApiOperation(value = "删除用户")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public ResponseEntity<ApiResponse> deleteUser(
//            @ApiParam(name = "UserId", value = "用户ID", required = true) @RequestParam(value = "id", required = true) int uid
//    ) {
//        int eff = userService.deleteUser(uid);
//        return ApiResponse.successResponse(eff);
//    }
//
//    @ApiOperation(value = "更改用户昵称")
//    @RequestMapping(value = "/updateName", method = RequestMethod.POST)
//    public void updateName(
//            @ApiParam(name = "UserId", value = "用户ID", required = true)
//            @RequestParam(value = "id", required = true)
//                    int uid,
//            @ApiParam(name = "UserName", value = "用户昵称", required = true)
//            @RequestParam(value = "name", required = true)
//                    String name) {
//        List<User> list = userService.selectByUserName(name);
//        if (list.size() == 0) {
//            userService.updateName(uid, name);
//        } else
//            System.out.println(name + "    用户名已经存在");
//    }

//    @ApiOperation(value = "更改密码")
//    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
//    public void updatePassword(
//            @ApiParam(name = "UserAccount", value = "用户账号", required = true)
//            @RequestParam(value = "account", required = true)
//                    String account,
//            @ApiParam(name = "UserPassword", value = "用户旧密码", required = true)
//            @RequestParam(value = "password", required = true)
//                    String password,
//            @ApiParam(name = "UserNewPassword", value = "用户新密码", required = true)
//            @RequestParam(value = "newpassword", required = true)
//                    String newpassword) {
//        User user = userService.selectByAccount(account);
//        if (user.getUserPassword().equals(password))
//            userService.updatePassword(account, password, newpassword);
//    }

}
