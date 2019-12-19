package com.hyp.learn.sss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.config
 * hyp create at 19-12-17
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.hyp.learn.sss"}, useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})}
)
public class WebConfig implements WebMvcConfigurer {
    //定制

    //视图解析器方案一
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从 /WEB-INF/ xxx .jsp
        //registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }


    // 默认Url根路径跳转到/login,此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
        registry.addViewController("/").setViewName("redirect:/login");
    }


}
