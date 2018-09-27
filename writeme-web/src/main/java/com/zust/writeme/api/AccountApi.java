package com.zust.writeme.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/27 9:45
 * @Description:
 */
@FeignClient(value = "writeme-service-provider")
@RequestMapping(value = "/account")
public interface AccountApi {

    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> modifyPwd(@RequestParam(value = "token", required = true) String token,
                                                 @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                                 @RequestParam(value = "newPwd", required = true) String newPwd,
                                                 @RequestParam(value = "rePwd", required = true) String rePwd);

    @RequestMapping(value = "/userSetting", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> userSetting(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "sex", required = true) String sex,
            @RequestParam(value = "desc", required = true) String desc
    );

    @RequestMapping(value = "/modifyPic", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> modifyPic(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "imageUrl", required = true) String imageUrl
    );
}
