package com.hyp.learn.sss.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.config
 * hyp create at 19-12-17
 **/
@Configuration
@ComponentScan(basePackages = {"com.hyp.learn.sss"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})
        })
public class ApplicationConfig {
}
