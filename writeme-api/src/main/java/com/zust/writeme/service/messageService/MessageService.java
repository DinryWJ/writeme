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
     * 获取用户所有消息
     *
     * @param toUserId
     * @param status
     * @return
     */
    Pagination<Message> getUserMessageList(int toUserId, int status);

    /**
     * 设置已读
     *
     * @param messageId
     * @return
     */
    int haveRead(int messageId, int userId);

    /**
     * 设置已读（批量）
     *
     * @param messageIds
     * @return
     */
    int haveReadBatch(int[] messageIds, int userId);

    /**
     * 删除消息
     *
     * @param messageId
     * @return
     */
    int deleteMessageById(int messageId, int userId);

    /**
     * 获取聊天记录
     *
     * @param userId
     * @param guestId
     * @return
     */
    Pagination<Message> getMessageRecord(int userId, int guestId);

    /**
     * 获取新消息数
     *
     * @param userId
     * @return
     */
    int getNewMessageNumber(int userId);
}
