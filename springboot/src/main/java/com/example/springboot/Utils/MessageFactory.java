package com.example.springboot.Utils;

import com.example.springboot.controller.MessageController;
import com.example.springboot.entity.Detection;
import com.example.springboot.entity.Message;
import com.example.springboot.mapper.DetectionMapper;
import com.example.springboot.mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class MessageFactory extends MessageController {

    @Autowired
    MessageMapper messageMapper;

    public void initForUser(Integer pid){
        createMessage(pid,"系统通知","您的账户已成功激活,可以开始属于你的检测之旅了");
        createMessage(pid,"安全提醒","请定期更改您的密码以确保账户安全");
    }

    public void createMessage(Integer pid, String style, String mes){
        Message message = new Message();
        message.setStyle(style);
        message.setIsread("0");
        message.setMes(mes);
        message.setPid(pid);
        messageMapper.save(message);
    }
}

