package com.zust.writeme.api;

import com.zust.writeme.model.UserInfo;
import com.zust.writeme.service.SimpleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleControllerApi {

    @Autowired
    private SimpleService simpleService;


    @RequestMapping(value = "/key",method = RequestMethod.POST)
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "query")
    public UserInfo getMS(int id){
        UserInfo userInfo = simpleService.selectUserById(id);
        int eff = simpleService.update(userInfo);
        if (eff>0){
            return simpleService.selectUserById(id);
        }else {
            return new UserInfo();
        }
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    public void set(@RequestBody UserInfo userInfo){
        System.out.println(userInfo.toString());
    }
}
