package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Audio;
import com.example.springboot.entity.Audio;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface AudioMapper extends BaseMapper<Audio> {
    List<Audio> list(Integer id);

    List<Audio> listByCondition(BaseRequest baseRequest);

    void save(Audio audio);

    Audio getById(Integer id);

    int updateById(Audio audio);

    void deleteById(Integer id);

    Audio getByName(String name);

    Audio getByPath(String path);
}

