package com.hyp.learn.sss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 安全配置的内容包括:用户信息、密码编码器、安全拦截机制。
 *
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.sss.config
 * hyp create at 19-12-18
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //配置用户信息服务
    //在 userDetailsService()方法中,我们返回了一个UserDetailsService给spring容器,Spring Security会使用它来
    //获取用户信息。我们暂时使用InMemoryUserDetailsManager实现类,并在其中分别创建了zhangsan、lisi两个用
    //户,并设置密码和权限。
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //配置安全拦截机制
    //而在configure()中,我们通过HttpSecurity设置了安全拦截规则,其中包含了以下内容:
    //url匹配/r/**的资源,经过认证后才能访问。
    //其他url完全开放。
    //支持form表单认证,认证成功后转向/login-success。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().successForwardUrl("/login‐success");
    }
}
