package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Video;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {
    
    List<Video> list();

    List<Video> listByCondition(BaseRequest baseRequest);

    void save(Video video);

    Video getById(Integer id);

    int updateById(Video video);

    void deleteById(Integer id);

    Video getByName(String name);

    Video getByPath(String path);
}

