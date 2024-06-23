package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Vidio;
import com.example.springboot.mapper.VidioMapper;
import com.example.springboot.service.IVidioService;
import org.springframework.stereotype.Service;

/**
 * @author oy
 */
@Service
public class VidioServiceImpl extends ServiceImpl<VidioMapper, Vidio> implements IVidioService {

}


