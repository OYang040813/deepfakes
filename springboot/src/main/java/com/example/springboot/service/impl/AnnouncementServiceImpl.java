package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Announcement;
import com.example.springboot.mapper.AnnouncementMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IAnnouncementService;
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
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
    
    @Autowired
    AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> list() {
        return announcementMapper.list();
    }


    @Override
    public PageInfo<Announcement> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Announcement> messages = announcementMapper.listByCondition(baseRequest);
        return new PageInfo<>(messages);
    }

    @Override
    public boolean save(Announcement announcement) {
        announcement.setCreatetime(new Date());
        announcement.setUpdatetime(new Date());
        announcementMapper.save(announcement);
        return true;
    }

    @Override
    public Announcement getById(Integer id) {
        return announcementMapper.getById(id);
    }

    @Override
    public void update(Announcement announcement) {
        announcement.setUpdatetime(new Date());
        announcementMapper.updateById(announcement);
    }

    @Override
    public void deleteById(Integer id) {
        announcementMapper.deleteById(id);
    }

}


