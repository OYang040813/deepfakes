package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Audio;
import com.example.springboot.mapper.AudioMapper;
import com.example.springboot.service.IAudioService;
import org.springframework.stereotype.Service;

/**
 * @author oy
 */
@Service
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements IAudioService {

}


