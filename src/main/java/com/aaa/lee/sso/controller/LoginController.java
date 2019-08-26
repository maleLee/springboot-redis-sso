package com.aaa.lee.sso.controller;

import com.aaa.lee.sso.model.User;
import com.aaa.lee.sso.service.RedisService;
import com.aaa.lee.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/26 23:22
 * @Description
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    /**
     * @author Seven Lee
     * @description
     *      跳转到登录页面
     * @param [request, response]
     * @date 2019/8/26
     * @return java.lang.String
     * @throws 
    **/
    @RequestMapping("/turnLoginPage")
    public String turnLoginPage(HttpServletRequest request, ModelMap modelMap) {
        // 获取从portal项目传递过来的参数
        String currentPage = request.getParameter("currentPage");
        modelMap.addAttribute("currentPage", currentPage);
        return "login";
    }

    /**
     * @author Seven Lee
     * @description
     *      检测登录状态
     * @param [cookieValue, callback]
     * @date 2019/8/26
     * @return java.lang.Object
     * @throws
    **/
    @RequestMapping("/token/{cookieValue}")
    @ResponseBody
    public Object checkLogin(@PathVariable("cookieValue") String cookieValue, String callback) {
        String checkResult = userService.checkLogin(cookieValue, redisService);
        // callback参数必须要和jsonp一起使用，如果在ajax的dataType使用jsonp也就是意味着需要做跨域操作，那么在controller中必须要接收一个固定的参数callback
        // callback参数的意义就是告诉controller我是一个跨域请求，跨域的唯一标识！
        // 如果接收到的callback为null，则该请求不是跨域请求，如果callback不为null，则该请求为跨域请求
        if("".equals(callback) || null == callback) {
            // 说明请求不是跨域请求
            return checkResult;
        } else {
            // 请求为跨域请求
            MappingJacksonValue mjv = new MappingJacksonValue(checkResult);
            mjv.setJsonpFunction(callback);
            return mjv;
        }
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        return userService.doLogin(user, request, response, redisService);
    }

}
