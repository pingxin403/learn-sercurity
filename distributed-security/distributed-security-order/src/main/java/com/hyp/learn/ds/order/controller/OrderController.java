package com.hyp.learn.ds.order.controller;

import com.hyp.learn.ds.order.model.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ds.order.controller
 * hyp create at 19-12-20
 **/
@RestController
public class OrderController {
    @PreAuthorize("hasAuthority('p1')")
    @GetMapping(value = "/r1")
    public String r1() {
        UserDto user = (UserDto)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源1";
    }

    @PreAuthorize("hasAuthority('p2')")
    @GetMapping(value = "/r2")
    public String r2() {//通过Spring Security API获取当前登录用户
        UserDto user =
                (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源2";
    }
}
