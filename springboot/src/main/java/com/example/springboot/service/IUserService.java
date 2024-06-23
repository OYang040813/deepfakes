package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.entity.User;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
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

    LoginDTO login(LoginRequest request);
}


