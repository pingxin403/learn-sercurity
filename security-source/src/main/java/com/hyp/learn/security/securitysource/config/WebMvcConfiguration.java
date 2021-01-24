package com.hyp.learn.security.securitysource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hyp
 * Project name is security-source
 * Include in com.hyp.learn.security.securitysource.config
 * hyp create at 19-12-30
 **/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signOut").setViewName("signOut");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/getUserList").setViewName("userList");
        registry.addViewController("/getOrderList").setViewName("orderList");
    }
}
