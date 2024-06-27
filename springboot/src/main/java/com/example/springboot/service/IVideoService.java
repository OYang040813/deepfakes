package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Video;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IVideoService extends IService<Video> {

    List<Video> list(Integer id);

    PageInfo<Video> page(BaseRequest baseRequest);

    boolean save(Video vidio);

    Video getById(Integer id);

    void update(Video vidio);

    void deleteById(Integer id);

    void deleteByName(String name);

    Video getByPath(String path);

    void owner(Integer pid, Integer id);
}


