package com.zust.writeme.api;

import com.zust.writeme.common.util.FtpFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Api(value = "图片上传", description = "图片上传")
@CrossOrigin
@RequestMapping(value = "/upload")
@RestController
public class UploadApi {
    private static final Logger log = LoggerFactory.getLogger(UploadApi.class);

    @ApiOperation(value = "模拟图片上传", notes = "模拟图片上传")
    @RequestMapping(value = "/virtualUpload", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> virtualUpload(@RequestParam(value = "file") MultipartFile file) {
        JSONObject obj = new JSONObject();
        obj.put("url", "http://www.dinry.top/a.jpg");
        ApiResponse res = new ApiResponse(200, "ok", obj);
        return ApiResponse.successResponse(res);
    }

    @ApiOperation(value = "图片上传", notes = "图片上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> upload(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String filePath = null;
        //上传文件名+UUID
        String currentPath = UUID.randomUUID()+fileName;

        Boolean flag = FtpFileUtil.uploadFile(currentPath, inputStream);
        if (flag == true) {
            System.out.println("ftp上传成功！");
            filePath = currentPath;
        }
        JSONObject obj = new JSONObject();
        obj.put("url", "http://www.dinry.top/"+filePath );
        return ApiResponse.successResponse(obj);
    }
}
