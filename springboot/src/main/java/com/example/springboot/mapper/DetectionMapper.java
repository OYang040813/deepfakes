package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Detection;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface DetectionMapper extends BaseMapper<Detection> {

    void save(Detection detection);

    Detection getById(Integer id);

    List<Detection> listByCondition(BaseRequest baseRequest);

    List<Detection> list();

    int updateById(Detection detection);

    void deleteById(Integer id);

    Detection getByName(String name);
}

