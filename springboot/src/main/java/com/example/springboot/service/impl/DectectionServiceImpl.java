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

    }

    @Override
    public void startDetectionForVideo(Integer fileId, Integer pid) {

    }
}


