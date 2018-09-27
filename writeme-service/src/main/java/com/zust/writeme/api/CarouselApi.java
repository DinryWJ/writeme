package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.Carousel;
import com.zust.writeme.service.carouselService.CarouselService;
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
 * @Date: 2018/9/27 11:11
 * @Description: 轮播图管理
 */
@Api(value = "轮播图管理", description = "轮播图管理")
@RequestMapping(value = "/carousel")
@RestController
public class CarouselApi {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增轮播图", notes = "新增轮播图")
    @RequestMapping(value = "/addCarousel", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addCarousel(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "tag", name = "tag", required = true) @RequestParam(value = "tag", required = true) String tag,
            @ApiParam(value = "address", name = "address", required = true) @RequestParam(value = "address", required = true) String address,
            @ApiParam(value = "pic", name = "pic", required = true) @RequestParam(value = "pic", required = true) String pic

    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if ("1".equals(userService.getUserById(userId).getUserPermission())) {
                int eff = carouselService.addCarousel(tag, address, pic);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("无权限!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "删除轮播图", notes = "删除轮播图")
    @RequestMapping(value = "/delCarousel", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> delCarousel(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "id", name = "id", required = true) @RequestParam(value = "id", required = true) int id

    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if ("1".equals(userService.getUserById(userId).getUserPermission())) {
                int eff = carouselService.delCarousel(id);
                return ApiResponse.successResponse(eff);
            } else {
                return ApiResponse.errorResponse("无权限!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "获取轮播图列表", notes = "获取轮播图列表")
    @RequestMapping(value = "/getCarouselList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getCarouselList(
            @ApiParam(value = "pageNum", name = "pageNum", required = true) @RequestParam(value = "pageNum", required = true) int pageNum,
            @ApiParam(value = "pageSize", name = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        Pagination<Carousel> pagination = carouselService.getCarouselList(pageNum, pageSize);
        return ApiResponse.successResponse(pagination);
    }

    @ApiOperation(value = "通过id获取轮播图", notes = "通过id获取轮播图")
    @RequestMapping(value = "/getCarouselById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getCarouselById(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "id", name = "id", required = true) @RequestParam(value = "id", required = true) int id

    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            if ("1".equals(userService.getUserById(userId).getUserPermission())) {
                Carousel carousel = carouselService.getCarouselById(id);
                return ApiResponse.successResponse(carousel);
            } else {
                return ApiResponse.errorResponse("无权限!");
            }
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }
}
