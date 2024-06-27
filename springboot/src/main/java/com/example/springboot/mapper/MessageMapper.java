package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Message;
import com.example.springboot.request.BaseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @author oy
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> list();

    List<Message> listByCondition(BaseRequest baseRequest);

    void save(Message message);

    Message getById(Integer id);

    int updateById(Message message);

    void deleteById(Integer id);
}

