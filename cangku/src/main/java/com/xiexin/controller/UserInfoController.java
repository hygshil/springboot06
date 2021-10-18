package com.xiexin.controller;

import com.xiexin.respcode.Result;
import com.xiexin.service.UserinfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserinfoService userinfoService;

    @RequestMapping("/login")
    public Result loginByShiro(@RequestBody Map map) {

        //登录交给shiro的securityManger管理
        Subject subject = SecurityUtils.getSubject();  //subject是根据过滤器拿到的
        UsernamePasswordToken token = new UsernamePasswordToken((String) map.get("username"), (String) map.get("password"));
        try {
            subject.login(token);
            return new Result(0, "登录成功");
        } catch (UnknownAccountException e) { //账号错误
            e.printStackTrace();
            return new Result(40001, "账号不对");
        } catch (IncorrectCredentialsException e) { //密码错误
            e.printStackTrace();
            return new Result(40002, "密码不对");
        }
    }

}
