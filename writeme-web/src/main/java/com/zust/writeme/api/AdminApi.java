package com.zust.writeme.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: 吴佳杰
 * @date: 2018/9/4 22:33
 * @description:
 */
@FeignClient(value = "writeme-service-provider")
@RequestMapping(value = "/manage")
public interface AdminApi {

    @RequestMapping(value = "/userManage", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> userManage(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "currentId", required = true) int currentId,
            @RequestParam(value = "status", required = true) String status
    );

    @RequestMapping(value = "/articleManage", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> articleManage(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "articleId", required = true) int articleId,
            @RequestParam(value = "status", required = true) int status
    );


    @RequestMapping(value = "/articlePassInfo", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> articlePassInfo(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "articleId", required = true) int articleId,
            @RequestParam(value = "status", required = true) int status,
            @RequestParam(value = "msg", required = true) String msg
    );
}
