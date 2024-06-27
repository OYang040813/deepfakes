package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Message;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IMessageService extends IService<Message> {

    List<Message> list();

    PageInfo<Message> page(BaseRequest baseRequest);

    boolean save(Message message);

    Message getById(Integer id);

    void update(Message message);

    void deleteById(Integer id);
}


