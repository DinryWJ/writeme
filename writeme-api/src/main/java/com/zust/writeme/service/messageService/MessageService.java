package com.zust.writeme.service.messageService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Message;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/20 10:01
 */
public interface MessageService {
    /**
     * 发送信息
     *
     * @param fromUserId
     * @param toUserId
     * @param message
     * @return
     */
    int sendMessage(int fromUserId, int toUserId, String message);

    /**
     * 通过id查询
     *
     * @param messageId
     * @return
     */
    Message getMessageById(int messageId);

    /**
     * 获取用户所有信息
     *
     * @param toUserId
     * @param status
     * @return
     */
    Pagination<Message> getUserMessageList(int toUserId, String status);
}
