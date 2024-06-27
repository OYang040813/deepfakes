package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Image;
import com.example.springboot.mapper.ImageMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author oy
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

    @Autowired
    ImageMapper imageMapper;

    @Override
    public List<Image> list(Integer id) {
        return imageMapper.list(id);
    }
    
    @Override
    public PageInfo<Image> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Image> images = imageMapper.listByCondition(baseRequest);
        return new PageInfo<>(images);
    }

    @Override
    public boolean save(Image image) {
        
        image.setUpdatetime(new Date());
        imageMapper.save(image);

        return false;
    }

    @Override
    public Image getById(Integer id) {
        return imageMapper.getById(id);
    }

    @Override
    public void update(Image image) {
        image.setUpdatetime(new Date());
        imageMapper.updateById(image);
    }

    @Override
    public void deleteById(Integer id) {
        imageMapper.deleteById(id);
    }

    @Override
    public Image getByName(String name) {
        return imageMapper.getByName(name);
    }

    @Override
    public Image getByPath(String path) {
        return imageMapper.getByPath(path);
    }

    @Override
    public void owner(Integer pid, Integer id) {
        Image byId = imageMapper.getById(id);
        byId.setPid(pid);
        imageMapper.updateById(byId);
    }
}


