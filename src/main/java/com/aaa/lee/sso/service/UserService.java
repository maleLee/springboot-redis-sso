package com.aaa.lee.sso.service;

import com.aaa.lee.sso.mapper.UserMapper;
import com.aaa.lee.sso.model.User;
import com.aaa.lee.sso.utils.CookieUtil;
import com.aaa.lee.sso.utils.JSONUtil;
import com.aaa.lee.sso.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/26 23:22
 * @Description
 **/
@Service
public class UserService {

    @Value("${expireTimeout}")
    private String expireTimeout;
    @Value("${cookie_key}")
    private String cookieKey;

    @Autowired
    private UserMapper userMapper;

    /**
     * @author Seven Lee
     * @description
     *      检测登录状态
     * @param [cookieValue, redisService]
     * @date 2019/8/26
     * @return java.lang.String
     * @throws
    **/
    public String checkLogin(String cookieValue, RedisService redisService) {
        return redisService.get(cookieValue);
    }

    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response, RedisService redisService) {
        User u = userMapper.selectByUsernameAndPassword(user);
        if(null != u) {
            u.setPassword(null);
            String token = u.getId() + UUIDUtil.getUUID();
            String setResult = redisService.set(token, JSONUtil.toJsonString(u));
            if("OK".equals(setResult.toUpperCase())) {
                CookieUtil.setCookie(request, response, cookieKey, token, Integer.parseInt(expireTimeout));
                return "success";
            }
        }
        return "failed";
    }

}
