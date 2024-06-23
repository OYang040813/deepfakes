package com.example.springboot.gen.mapper;

import com.example.springboot.gen.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* @author oy
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> list();

    List<User> listByCondition(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    int updateById(User user);

    void deleteById(Integer id);

    User getByName(String name);
}

