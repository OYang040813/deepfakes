package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Announcement;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    List<Announcement> list();

    List<Announcement> listByCondition(BaseRequest baseRequest);

    void save(Announcement announcement);

    Announcement getById(Integer id);

    int updateById(Announcement announcement);

    void deleteById(Integer id);
}

