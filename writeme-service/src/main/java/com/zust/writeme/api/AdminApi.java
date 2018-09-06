package com.zust.writeme.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 吴佳杰
 * @date: 2018/9/4 22:33
 * @description:
 */
@Api(value = "管理端-管理", description = "管理端-管理")
@RequestMapping(value = "/manage")
@RestController
public class AdminApi {

}
