package com.zust.writeme.service.messageServiceImpl;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.MessageMapper;
import com.zust.writeme.model.Message;
import com.zust.writeme.service.messageService.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/20 10:15
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int sendMessage(int fromUserId, int toUserId, String message) {
        Message model = new Message();
        model.setFromUserId(fromUserId);
        model.setToUserId(toUserId);
        model.setCreateTime(new Date());
        model.setMessage(message);
        model.setStatus("0");
        return messageMapper.insert(model);
    }

    @Override
    public Message getMessageById(int messageId) {
        return messageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public Pagination<Message> getUserMessageList(int toUserId, String status) {
        Pagination<Message> pagination = new Pagination<>();
        List<Integer> integers = new ArrayList<>();
        List<Message> list = messageMapper.getFromUserMessageList(toUserId, status);
        List<Message> list2 = messageMapper.getToUserMessageList(toUserId, status);
        for (Message m : list) {
            integers.add(m.getToUserId());
        }
        list2 = list2.stream().filter(t -> !integers.contains(t.getFromUserId())).collect(Collectors.toList());
        list.addAll(list2);
        pagination.setList(list);
        pagination.setTotal((long) list.size());

        return pagination;
    }

    @Override
    public int haveRead(int messageId, int userId) {
        Message message = new Message();
        message.setId(messageId);
        message.setToUserId(userId);
        message.setStatus("1");
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int haveReadBatch(int[] messageIds, int userId) {
        int eff = 0;
        for (int i = 0; i < messageIds.length; i++) {
            Message message = new Message();
            message.setId(messageIds[i]);
            message.setToUserId(userId);
            message.setStatus("1");
            eff += messageMapper.updateByPrimaryKeySelective(message);
        }
        return eff;
    }

    @Override
    public int deleteMessageById(int messageId, int userId) {
        Message message = new Message();
        message.setId(messageId);
        message.setToUserId(userId);
        message.setStatus("2");
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public Pagination<Message> getMessageRecord(int userId, int guestId) {
        Pagination<Message> pagination = new Pagination<>();
        List<Message> messageList = messageMapper.getMessageRecord(userId, guestId);

        pagination.setList(messageList);
        return pagination;
    }

    @Override
    public int getNewMessageNumber(int userId) {
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUserId", userId);
        criteria.andEqualTo("status", "0");
        return messageMapper.selectCountByExample(example);
    }


}
