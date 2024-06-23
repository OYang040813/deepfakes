package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Image;
import com.example.springboot.mapper.ImageMapper;
import com.example.springboot.service.IImageService;
import org.springframework.stereotype.Service;

/**
 * @author oy
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

}


