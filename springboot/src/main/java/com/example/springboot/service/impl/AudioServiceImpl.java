package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Audio;
import com.example.springboot.entity.Video;
import com.example.springboot.mapper.AudioMapper;
import com.example.springboot.mapper.VideoMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IAudioService;
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
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements IAudioService {

    @Autowired
    AudioMapper audioMapper;
    
    @Override
    public List<Audio> list(Integer id) {
        return audioMapper.list(id);
    }

    @Override
    public PageInfo<Audio> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Audio> audios = audioMapper.listByCondition(baseRequest);
        return new PageInfo<>(audios);
    }

    @Override
    public boolean save(Audio audio) {
        audio.setUpdatetime(new Date());
        audioMapper.save(audio);

        return false;
    }

    @Override
    public Audio getById(Integer id) {
        return audioMapper.getById(id);
    }

    @Override   
    public void update(Audio audio) {
        audio.setUpdatetime(new Date());
        audioMapper.updateById(audio);
    }

    @Override
    public void deleteById(Integer id) {
        audioMapper.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        //to do
        audioMapper.deleteById(name);
    }

    @Override
    public Audio getByPath(String path) {
        return audioMapper.getByPath(path);
    }

    @Override
    public void owner(Integer pid, Integer id) {
        Audio byId = audioMapper.getById(id);
        byId.setPid(pid);
        audioMapper.updateById(byId);
    }
}


