package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Audio;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IAudioService extends IService<Audio> {
    List<Audio> list(Integer id);

    PageInfo<Audio> page(BaseRequest baseRequest);

    boolean save(Audio audio);

    Audio getById(Integer id);

    void update(Audio audio);

    void deleteById(Integer id);

    void deleteByName(String name);

    Audio getByPath(String path);

    void owner(Integer pid, Integer id);
}


