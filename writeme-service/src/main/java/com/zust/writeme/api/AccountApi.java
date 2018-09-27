package com.zust.writeme.api;

import com.zust.writeme.common.util.TokenUtils;
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

import java.util.Map;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/27 9:45
 * @Description:
 */
@Api(value = "账号管理", description = "账号管理")
@RequestMapping(value = "/account")
@RestController
public class AccountApi {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> modifyPwd(
            @ApiParam(name = "token", value = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(name = "oldPwd", value = "oldPwd", required = true) @RequestParam(value = "oldPwd", required = true) String oldPwd,
            @ApiParam(name = "newPwd", value = "newPwd", required = true) @RequestParam(value = "newPwd", required = true) String newPwd,
            @ApiParam(name = "rePwd", value = "rePwd", required = true) @RequestParam(value = "rePwd", required = true) String rePwd
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if (newPwd.equals(rePwd)) {
                if (userService.getUserById(userId).getUserPassword().equals(oldPwd)) {
                    int eff = userService.modifyPwd(userId, newPwd);
                    return ApiResponse.successResponse(eff);
                } else {
                    return ApiResponse.errorResponse("密码错误!");
                }
            } else {
                return ApiResponse.errorResponse("两次密码不一致!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "个人设置", notes = "个人设置")
    @RequestMapping(value = "/userSetting", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> userSetting(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "username", name = "username", required = true) @RequestParam(value = "username", required = true) String username,
            @ApiParam(value = "sex", name = "sex", required = true) @RequestParam(value = "sex", required = true) String sex,
            @ApiParam(value = "desc", name = "desc", required = true) @RequestParam(value = "desc", required = true) String desc
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if (username == null || username.length() == 0 || (!sex.equals("M") && !sex.equals("F"))) {
                return ApiResponse.errorResponse("数据有错误");
            }
            int eff = userService.userSetting(userId, username, sex, desc);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "个人设置", notes = "个人设置")
    @RequestMapping(value = "/modifyPic", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> modifyPic(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "imageUrl", name = "imageUrl", required = true) @RequestParam(value = "imageUrl", required = true) String imageUrl
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = userService.modifyPic(userId, imageUrl);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }
}
