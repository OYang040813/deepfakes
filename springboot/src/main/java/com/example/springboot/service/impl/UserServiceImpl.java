package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author oy
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final String PASS_SALT = "ouyang";
    @Autowired
    UserMapper userMapper;

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
        user.setCardnum(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        user.setUpdatetime(new Date());
//        //mt5+加盐并加密密码
//        user.setKeynum(secureKeynum(user.getKeynum()));

        User user1 = userMapper.getByName(user.getName());
        if (user1 == null) {
            userMapper.save(user);
        } else {
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

    @Override
    public LoginDTO login(LoginRequest request) {
//        //输入密码加盐加密后再进入数据库进行对比
//        request.setKeynum(secureKeynum(request.getKeynum()));

        User user = null;
        try {
            user = userMapper.getByName(request.getName());
        }catch (Exception e){
            log.error("根据用户名查询出错");
            throw new ServiceException("用户名错误");
        }

        if (user == null){
            throw new ServiceException("用户名或密码错误");
        }

        //判断密码是否合法
        if (!request.getKeynum().equals(user.getKeynum())){
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(user,loginDTO);

        return loginDTO;
    }

    private String secureKeynum(String keynum) {
        return SecureUtil.md5(keynum + PASS_SALT);
    }
}


