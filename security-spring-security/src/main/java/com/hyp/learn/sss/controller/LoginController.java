package com.hyp.learn.sss.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.controller
 * hyp create at 19-12-18
 **/
@RestController
public class LoginController {

    /**
     * 测试资源1
     *
     * @return
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf8"})
    public String r1() {
        return " 访问资源1";
    }

    /**
     * 测试资源2
     *
     * @return
     */
    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf8"})
    public String r2() {
        return " 访问资源2";
    }

    @RequestMapping(value = "/login‐success", produces = {"text/plain;charset=utf8"})
    public String loginSuccess() {
        return " 登录成功";
    }
}
