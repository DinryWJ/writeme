package com.zust.writeme.api;

import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.User;
import com.zust.writeme.service.userservice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "登陆管理", description = "登陆管理")
@RequestMapping(value = "/login")
@RestController
public class LoginApi {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "判断账号是否存在")
    @RequestMapping(value = "/validAccount", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> validAccount(
            @ApiParam(name = "account", value = "用户账号", required = true) @RequestParam(value = "account", required = true) String account
    ) {
        int count = userService.validAccount(account);
        return ApiResponse.successResponse(count);
    }

    @ApiOperation(value = "登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> login(
            @ApiParam(name = "account", value = "用户账号", required = true) @RequestParam(value = "account", required = true) String account,
            @ApiParam(value = "密码", name = "password", required = true) @RequestParam(value = "password", required = true) String password
    ) {
        int count = userService.validAccount(account,password);
        if (count>0){
            User user = userService.getLoginUser(account,password);
            if (user.isStatus()){
                String token = TokenUtils.createToken(user.getUserId(),user.getUserAccount());
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("token",token);
                return ApiResponse.successResponse(map);
            }else {
                return ApiResponse.errorResponse("该账号被禁用，请联系管理员");
            }
        }else{
            return ApiResponse.errorResponse("用户名或密码错误");
        }
    }
}
