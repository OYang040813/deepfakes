package com.example.springboot.gen.service.impl;

import com.example.springboot.gen.entity.User;
import com.example.springboot.gen.mapper.UserMapper;
import com.example.springboot.gen.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author oy
*/
@Service
    public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    }


