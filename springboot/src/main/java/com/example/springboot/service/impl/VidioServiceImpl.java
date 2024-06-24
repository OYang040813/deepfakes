package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.entity.Vidio;
import com.example.springboot.entity.Vidio;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.VidioMapper;
import com.example.springboot.mapper.VidioMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.service.IVidioService;
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
public class VidioServiceImpl extends ServiceImpl<VidioMapper, Vidio> implements IVidioService {
    
    @Autowired
    VidioMapper vidioMapper;

    @Override
    public List<Vidio> list() {
        return vidioMapper.list();
    }


    @Override
    public PageInfo<Vidio> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Vidio> vidios = vidioMapper.listByCondition(baseRequest);
        return new PageInfo<>(vidios);
    }

    @Override
    public boolean save(Vidio vidio) {
        vidio.setUpdatetime(new Date());
        Vidio vidio1 = vidioMapper.getByName(vidio.getName());
        if (vidio1 == null) {
            vidioMapper.save(vidio);
        } else {
            throw new ServiceException("该用户名已被占用");
        }
        return false;
    }

    @Override
    public Vidio getById(Integer id) {
        return vidioMapper.getById(id);
    }

    @Override
    public void update(Vidio vidio) {
        vidio.setUpdatetime(new Date());
        vidioMapper.updateById(vidio);
    }

    @Override
    public void deleteById(Integer id) {
        vidioMapper.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        vidioMapper.deleteById(name);
    }

}


