package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Image;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IImageService extends IService<Image> {
    
    List<Image> list(Integer id);

    PageInfo<Image> page(BaseRequest baseRequest);

    boolean save(Image image);

    Image getById(Integer id);

    void update(Image image);

    void deleteById(Integer id);

    Image getByName(String name);

    Image getByPath(String path);

    void owner(Integer pid, Integer id);
}


