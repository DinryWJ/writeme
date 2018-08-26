package com.zust.writeme.api;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "关注管理", description = "关注管理")
@RequestMapping(value = "/concern")
@RestController
public class ConcernApi {

    private static final Logger log = LoggerFactory.getLogger(ConcernApi.class);


}
