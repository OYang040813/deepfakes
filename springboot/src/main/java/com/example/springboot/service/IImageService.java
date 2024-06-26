package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.entity.Image;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IImageService extends IService<Image> {
    
    List<Image> list();

    PageInfo<Image> page(BaseRequest baseRequest);

    boolean save(Image image);

    Image getById(Integer id);

    void update(Image image);

    void deleteById(Integer id);

}


