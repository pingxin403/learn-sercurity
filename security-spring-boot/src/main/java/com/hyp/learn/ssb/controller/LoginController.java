package com.hyp.learn.ssb.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAnyAuthority('p1')")
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf8"})
    public String r1() {
        return " 访问资源1";
    }

    /**
     * 测试资源2
     *
     * @return
     */
    @PreAuthorize("hasAnyAuthority('p2')")
    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf8"})
    public String r2() {
        return " 访问资源2";
    }

//    @RequestMapping(value = "/login‐success", produces = {"text/plain;charset=utf8"})
//    public String loginSuccess() {
//        return " 登录成功";
//    }

    @RequestMapping(value = "/login‐success", produces = {"text/plain;charset=utf8"})
    public String loginSuccess() {
        String username = getUsername();
        return username + " 登录成功";
    }

    /**
     * 获取当前登录用户名
     *
     * @return
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username =
                    ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
