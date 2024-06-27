package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Video;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.VideoMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IVideoService;
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
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {
    
    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Video> list() {
        return videoMapper.list();
    }


    @Override
    public PageInfo<Video> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Video> vidios = videoMapper.listByCondition(baseRequest);
        return new PageInfo<>(vidios);
    }

    @Override
    public boolean save(Video video) {
        video.setUpdatetime(new Date());
        videoMapper.save(video);

        return false;
    }

    @Override
    public Video getById(Integer id) {
        return videoMapper.getById(id);
    }

    @Override
    public void update(Video video) {
        video.setUpdatetime(new Date());
        videoMapper.updateById(video);
    }

    @Override
    public void deleteById(Integer id) {
        videoMapper.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        videoMapper.deleteById(name);
    }

    @Override
    public Video getByPath(String path) {
        return videoMapper.getByPath(path);
    }

}


