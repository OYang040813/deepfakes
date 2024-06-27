package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Message;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.MessageMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author oy
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    
    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> list() {
        return messageMapper.list();
    }


    @Override
    public PageInfo<Message> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Message> messages = messageMapper.listByCondition(baseRequest);
        return new PageInfo<>(messages);
    }

    @Override
    public boolean save(Message message) {
        //用创建时间作为卡号并更新时间
        message.setUpdatetime(new Date());
        messageMapper.save(message);
        return true;
    }

    @Override
    public Message getById(Integer id) {
        return messageMapper.getById(id);
    }

    @Override
    public void update(Message message) {
        message.setUpdatetime(new Date());
        messageMapper.updateById(message);
    }

    @Override
    public void deleteById(Integer id) {
        messageMapper.deleteById(id);
    }

}


