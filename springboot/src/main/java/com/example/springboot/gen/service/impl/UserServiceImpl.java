package com.example.springboot.gen.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.gen.entity.User;
import com.example.springboot.gen.mapper.UserMapper;
import com.example.springboot.gen.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author oy
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    private static final String PASS_SALT = "ouyang";

    @Override
    public List<User> list() {
        return userMapper.list();
    }


    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public boolean save(User user) {
        //用创建时间作为卡号并更新时间
        Date date = new Date();
        user.setCardnum(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        user.setUpdatetime(new Date());
        //mt5+加盐并加密密码
//        user.setKeynum(secureKeynum(user.getKeynum()));

        User user1 = userMapper.getByName(user.getName());
        if (user1==null){
            userMapper.save(user);
        }else{
            throw new ServiceException("该用户名已被占用");
        }
        return false;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    private String secureKeynum(String keynum){
        return SecureUtil.md5(keynum + PASS_SALT);
    }
}


