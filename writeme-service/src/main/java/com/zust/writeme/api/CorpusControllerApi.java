package com.zust.writeme.api;

import com.zust.writeme.model.Corpus;
import com.zust.writeme.service.CorpusService.CorpusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value="文集管理",description = "文集管理")
@RequestMapping(value = "/corpus")
@Controller
public class CorpusControllerApi {
    private static final Logger log = LoggerFactory.getLogger(CorpusControllerApi.class);

    @Autowired
    private CorpusService corpusService;

    @ApiOperation(value="添加文集", notes="添加文集")
    @RequestMapping(value = "/addCorpus",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> add(
            @ApiParam(name = "Corpus", value = "添加文集json格式", required = true)
            @RequestBody Corpus corpus){
        int eff = corpusService.add(corpus);
        ApiResponse res = new ApiResponse(200,"ok",eff);
        return ApiResponse.successResponse(res);
    }

    @ApiOperation(value = "删除文集", notes = "根据文集id来删除文集")
    @RequestMapping(value = "/deleteCorpusById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> delete(
            @ApiParam(name = "corpusId", value = "文集ID", required = true)
            @RequestParam(value = "corpusId", required = true) int id) {
        int eff = corpusService.delete(id);
        ApiResponse res = new ApiResponse(200,"ok",eff);
        return ApiResponse.successResponse(res);
    }

}
