package com.zust.writeme.api;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.common.util.TokenUtils;
import com.zust.writeme.model.Message;
import com.zust.writeme.service.messageService.MessageService;
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
 * @Date: 2018/9/25 9:32
 */
@Api(value = "消息管理", description = "消息管理")
@RequestMapping(value = "/message")
@RestController
public class MessageApi {
    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "发送信息", notes = "发送信息")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> sendMessage(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "用户id", name = "toUserId", required = true) @RequestParam(value = "toUserId", required = true) int toUserId,
            @ApiParam(value = "消息", name = "message", required = true) @RequestParam(value = "message", required = true) String message
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = messageService.sendMessage(userId, toUserId, message);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @RequestMapping(value = "/getMessageById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getMessageById(
            @ApiParam(value = "id", name = "id", required = true) @RequestParam(value = "id", required = true) int id
    ) {
        Message message = messageService.getMessageById(id);
        return ApiResponse.successResponse(message);
    }

    @ApiOperation(value = "获取用户所有消息", notes = "获取用户所有消息")
    @RequestMapping(value = "/getUserMessageList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getUserMessageList(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "status", name = "status", required = true) @RequestParam(value = "status", required = true) String status
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            Pagination<Message> pagination = messageService.getUserMessageList(userId, status);
            return ApiResponse.successResponse(pagination);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "设置已读", notes = "设置已读")
    @RequestMapping(value = "/haveRead", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> haveRead(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "messageId", name = "messageId", required = true) @RequestParam(value = "messageId", required = true) int messageId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = messageService.haveRead(messageId, userId);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "批量设置已读", notes = "批量设置已读")
    @RequestMapping(value = "/haveReadBatch", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> haveReadBatch(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "messageIds", name = "messageIds", required = true) @RequestParam(value = "messageIds", required = true) int[] messageIds
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = messageService.haveReadBatch(messageIds, userId);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "删除消息", notes = "删除消息")
    @RequestMapping(value = "/deleteMessageById", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> deleteMessageById(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "messageId", name = "messageId", required = true) @RequestParam(value = "messageId", required = true) int messageId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            int eff = messageService.deleteMessageById(messageId, userId);
            return ApiResponse.successResponse(eff);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }

    @ApiOperation(value = "获取聊天记录", notes = "获取聊天记录")
    @RequestMapping(value = "/getMessageRecord", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> getMessageRecord(
            @ApiParam(value = "token", name = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "guestId", name = "guestId", required = true) @RequestParam(value = "guestId", required = true) int guestId
    ) {
        Map<String, Object> map = TokenUtils.validToken(token);
        boolean flag = (boolean) map.get("success");
        if (flag) {
            int userId = Integer.parseInt((String) map.get("uid"));
            String account = (String) map.get("account");
            Pagination<Message> pagination = messageService.getMessageRecord(userId, guestId);
            return ApiResponse.successResponse(pagination);
        } else {
            return ApiResponse.errorResponse("登陆过期，请重新登陆");
        }
    }
}
