package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.Utils.TokenUtils;
import com.example.springboot.entity.*;
import com.example.springboot.entity.Dectection;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.*;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IDectectionService;
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
public class DectectionServiceImpl extends ServiceImpl<DectectionMapper, Dectection> implements IDectectionService {

    @Autowired
    DectectionMapper dectectionMapper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    AudioMapper audioMapper;

    @Override
    public void startDetectionForImage(Integer fileId, Integer pid) {
        //创建实例
        Image image = imageMapper.getById(fileId);
        Dectection dectection = new Dectection();
        dectection.setName(image.getName());
        dectection.setStyle("图像检测");
        dectection.setStatus("等待检测");
        dectection.setPid(pid);
        dectectionMapper.save(dectection);

        imageMapper.deleteById(fileId);

        log.error("图片检测开始");
    }

    @Override
    public void startDetectionForVideo(Integer fileId, Integer pid) {
        //创建实例
        Video video = videoMapper.getById(fileId);
        Dectection dectection = new Dectection();
        dectection.setName(video.getName());
        dectection.setStyle("视频检测");
        dectection.setStatus("等待检测");
        dectection.setPid(pid);
        dectectionMapper.save(dectection);

        videoMapper.deleteById(fileId);

        log.error("视频检测开始");
    }

    @Override
    public void startDetectionForAudioSingle(Integer fileId, Integer pid) {
        //创建实例
        Audio audio = audioMapper.getById(fileId);
        Dectection dectection = new Dectection();
        dectection.setName(audio.getName());
        dectection.setStyle("音频检测");
        dectection.setStatus("等待检测");
        dectection.setPid(pid);
        dectectionMapper.save(dectection);

        audioMapper.deleteById(fileId);

        log.error("真人音频检测开始");
    }

    @Override
    public void startDetectionForAudioDouble(Integer fileId, Integer pid) {
        // to do

        log.error("匹配音频检测开始");
    }

    @Override
    public List<Dectection> list() {
        return dectectionMapper.list();
    }

    @Override
    public boolean save(Dectection dectection) {

        dectection.setUpdatetime(new Date());
        dectection.setCreatetime(new Date());

        dectectionMapper.save(dectection);

        return true;
    }

    @Override
    public Dectection getById(Integer id) {
        return dectectionMapper.getById(id);
    }

    @Override
    public void update(Dectection dectection) {
        dectection.setUpdatetime(new Date());
        dectectionMapper.updateById(dectection);
    }

    @Override
    public void deleteById(Integer id) {
        dectectionMapper.deleteById(id);
    }

    @Override
    public PageInfo<Dectection> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Dectection> users = dectectionMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }
}


