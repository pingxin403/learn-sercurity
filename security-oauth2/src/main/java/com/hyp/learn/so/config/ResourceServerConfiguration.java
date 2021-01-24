package com.hyp.learn.so.config;

import com.hyp.learn.so.handler.MyAuthenticationFailureHandler;
import com.hyp.learn.so.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器@EnableResourceServer用于保护接口，验证token，如果token有效就可以访问api
 * 认证服务器@EnableAuthorizationServer用于获取token
 * 资源服务器可以单独配置在要保护的项目上，也可以与认证服务器配置在同一个项目中
 *
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so.config
 * hyp create at 19-12-31
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .formLogin()
                .loginPage("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        http.authorizeRequests()
                .antMatchers("/login", "/authentication/form", "/authentication/mobile", "/code/sms").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}

