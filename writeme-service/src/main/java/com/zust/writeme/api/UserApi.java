/* *
 * @author Administrator
 * @create 2018/8/9
 */
package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.User;
import com.zust.writeme.service.userService.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user")
@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> register(
            @ApiParam(value = "账号", name = "account", required = true) @RequestParam(value = "account", required = true) String account,
            @ApiParam(value = "密码", name = "password", required = true) @RequestParam(value = "password", required = true) String password,
            @ApiParam(value = "重复密码", name = "repassword", required = true) @RequestParam(value = "repassword", required = true) String repassword
    ) {
        if (!password.equals(repassword)) {
            return ApiResponse.errorResponse("两次密码不能相同");
        } else {
            int count = userService.validAccount(account);
            if (count > 0) {
                return ApiResponse.errorResponse("账号已存在");
            }
            User user = new User();
            user.setUserAccount(account);
            user.setUserPassword(password);
            user.setSex("M");
            user.setUserName("新用户" + LocalDate.now().toString());
            user.setUserPermission("0");
            user.setStatus("0");
            int eff = userService.insertUser(user);
            return ApiResponse.successResponse(eff);
        }
    }

    @ApiOperation(value = "通过id搜索用户")
    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserInfoById(
            @ApiParam(name = "userId", value = "userId", required = true) @RequestParam(value = "userId", required = true) int userId
    ) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ApiResponse.successResponse(user);
        } else {
            return ApiResponse.errorResponse("未找到该用户");
        }
    }

    @ApiOperation(value = "通过token搜索用户id")
    @RequestMapping(value = "/getUserIdByToken", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserIdByToken(
            @ApiParam(name = "token", value = "token", required = true) @RequestParam(value = "token", required = true) String token
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            return ApiResponse.successResponse(userId);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

//    @ApiOperation(value = "陈列所有用户")
//    @RequestMapping(value = "/listUser", method = RequestMethod.POST)
//    public ResponseEntity<ApiResponse> selectAllUser(
//            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
//            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
//    ) {
//        Pagination<User> pagination = userService.getTotalUser(pageNum, pageSize);
//        return ApiResponse.successResponse(pagination);
//    }

    @ApiOperation(value = "用户模糊搜索")
    @RequestMapping(value = "/selectUserListByName", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> selectUserListByName(
            @ApiParam(name = "name", value = "账号/用户名", required = false) @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "flag", value = "账号 1/用户名 2", required = false) @RequestParam(value = "flag", required = false) String flag,
            @ApiParam(name = "status", value = "账号状态", required = false) @RequestParam(value = "status", required = false) String status,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<User> pagination = null;
        if (name == null) {
            pagination = userService.getTotalUser(status, pageNum, pageSize);
        } else {
            if ("1".equals(flag)) {
                pagination = userService.getUserListByUserAccount(name, status, pageNum, pageSize);
            }
            if ("2".equals(flag)) {
                pagination = userService.selectUserListByName(name, status, pageNum, pageSize);
            }

        }
        return ApiResponse.successResponse(pagination);
    }

    @ApiOperation(value = "获取我的推荐用户")
    @RequestMapping(value = "/getMyRecommentUserList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getMyRecommentUserList(
            @ApiParam(name = "token", value = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            Pagination<User> pagination = userService.getMyRecommentUserList(userId, pageNum, pageSize);

            for (User user : pagination.getList()) {
                user.setFromUserName(userService.getUserById(user.getFromUserId()).getUserName());
                user.setConcernStatus(0);
            }
            return ApiResponse.successResponse(pagination);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }

    }
}
