package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Video;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IVideoService extends IService<Video> {

    List<Video> list();

    PageInfo<Video> page(BaseRequest baseRequest);

    boolean save(Video vidio);

    Video getById(Integer id);

    void update(Video vidio);

    void deleteById(Integer id);

    void deleteByName(String name);

    Video getByPath(String path);
}


