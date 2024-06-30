package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Dectection;
import com.example.springboot.mapper.DectectionMapper;
import com.example.springboot.service.IDectectionService;
import org.springframework.stereotype.Service;

/**
 * @author oy
 */
@Service
public class DectectionServiceImpl extends ServiceImpl<DectectionMapper, Dectection> implements IDectectionService {

    @Override
    public void startDetectionForImage(Integer fileId, Integer pid) {

        log.error("图片检测开始");
    }

    @Override
    public void startDetectionForVideo(Integer fileId, Integer pid) {

        log.error("视频检测开始");
    }

    @Override
    public void startDetectionForAudioSingle(Integer fileId, Integer pid) {

        log.error("真人音频检测开始");
    }

    @Override
    public void startDetectionForAudioDouble(Integer fileId, Integer pid) {

        log.error("匹配音频检测开始");
    }
}


