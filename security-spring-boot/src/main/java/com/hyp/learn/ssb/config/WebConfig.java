package com.hyp.learn.ssb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ssb.config
 * hyp create at 19-12-18
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //默认Url根路径跳转到/login,此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("redirect:/login");

        registry.addViewController("/").setViewName("redirect:/login-view");
        registry.addViewController("/login-view").setViewName("login");
    }

}
