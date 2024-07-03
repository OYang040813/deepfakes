package com.example.springboot.Utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Slf4j
public class TokenUtils {

    private static IUserService staticUserService;

    @Resource
    private IUserService userService;

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String userid, String sign) {
        return JWT.create().withAudience(userid) //将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 200)) // 200小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     * /user?token=xxxx
     */
    public static User getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            String userid = JWT.decode(token).getAudience().get(0);
            return staticUserService.getById(Integer.valueOf(userid));
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token, e);
            return null;
        }
    }

    @PostConstruct
    public void setUserService() {

        staticUserService = userService;
    }
}

