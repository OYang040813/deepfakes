package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Vidio;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IVidioService extends IService<Vidio> {

    List<Vidio> list();

    PageInfo<Vidio> page(BaseRequest baseRequest);

    boolean save(Vidio vidio);

    Vidio getById(Integer id);

    void update(Vidio vidio);

    void deleteById(Integer id);

    void deleteByName(String name);
}


