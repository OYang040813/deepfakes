package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Announcement;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IAnnouncementService extends IService<Announcement> {

    List<Announcement> list();

    PageInfo<Announcement> page(BaseRequest baseRequest);

    boolean save(Announcement announcement);

    Announcement getById(Integer id);

    void update(Announcement announcement);

    void deleteById(Integer id);
}


