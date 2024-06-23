package com.example.springboot.gen.service;

import com.example.springboot.gen.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
* @author oy
*/
public interface IUserService extends IService<User> {

    List<User> list();

    PageInfo<User> page(BaseRequest baseRequest);

    boolean save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);
}


