package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.Image;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface ImageMapper extends BaseMapper<Image> {
    List<Image> list();

    List<Image> listByCondition(BaseRequest baseRequest);

    void save(Image image);

    Image getById(Integer id);

    int updateById(Image image);

    void deleteById(Integer id);

    Image getByName(String name);
}

