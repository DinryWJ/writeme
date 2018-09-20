package com.zust.writeme.service.messageServiceImpl;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Message;
import com.zust.writeme.service.messageService.MessageService;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/20 10:15
 */
public class MessageServiceImpl implements MessageService {
    @Override
    public int sendMessage(int fromUserId, int toUserId, String message) {
        return 0;
    }

    @Override
    public Message getMessageById(int messageId) {
        return null;
    }

    @Override
    public Pagination<Message> getUserMessageList(int toUserId, String status) {
        return null;
    }
}
