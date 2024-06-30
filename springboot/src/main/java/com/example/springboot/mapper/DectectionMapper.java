package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Dectection;
import com.example.springboot.entity.User;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface DectectionMapper extends BaseMapper<Dectection> {

    void save(Dectection dectection);

    Dectection getById(Integer id);

    List<Dectection> listByCondition(BaseRequest baseRequest);

    List<Dectection> list();

    int updateById(Dectection dectection);

    void deleteById(Integer id);

    Dectection getByName(String name);
}

