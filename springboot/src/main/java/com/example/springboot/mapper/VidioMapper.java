package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Vidio;
import com.example.springboot.entity.Vidio;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface VidioMapper extends BaseMapper<Vidio> {
    
    List<Vidio> list();

    List<Vidio> listByCondition(BaseRequest baseRequest);

    void save(Vidio vidio);

    Vidio getById(Integer id);

    int updateById(Vidio vidio);

    void deleteById(Integer id);

    Vidio getByName(String name);
}

