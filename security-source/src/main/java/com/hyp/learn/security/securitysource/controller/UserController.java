package com.hyp.learn.security.securitysource.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.security.securitysource.controller
 * hyp create at 19-12-31
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/me")
    public String me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return JSON.toJSONString(auth);
    }
}
