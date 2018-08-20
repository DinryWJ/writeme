package com.zust.writeme.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Api(value = "图片上传", description = "图片上传")
@RequestMapping(value = "/upload")
@Controller
public class UploadApi {
    private static final Logger log = LoggerFactory.getLogger(UploadApi.class);

    @ApiOperation(value = "图片上传", notes = "图片上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> upload(){
        JSONObject obj = new JSONObject();
        obj.put("url","static/1.jpg");
        ApiResponse res = new ApiResponse(200, "ok", obj);
        return ApiResponse.successResponse(res);
    }
}
