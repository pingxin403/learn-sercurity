package com.hyp.learn.security.securitysource.config;

import com.hyp.learn.security.securitysource.filter.ImageValidateCodeFilter;
import com.hyp.learn.security.securitysource.filter.SmsValidateCodeFilter;
import com.hyp.learn.security.securitysource.handler.MyAuthenticationFailureHandler;
import com.hyp.learn.security.securitysource.handler.MyAuthenticationSuccessHandler;
import com.hyp.learn.security.securitysource.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author hyp
 * Project name is security-source
 * Include in com.hyp.learn.security.securitysource.config
 * hyp create at 19-12-30
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        图片
        ImageValidateCodeFilter imageValidateCodeFilter = new ImageValidateCodeFilter();
//        短信，从命令行查看
        SmsValidateCodeFilter smsValidateCodeFilter = new SmsValidateCodeFilter();


        imageValidateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        smsValidateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        http.csrf().disable()
                // 配置需要认证的请求
                .authorizeRequests()
                .antMatchers("/login", "/code/image", "/code/sms").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 登录表单相关配置
                .addFilterBefore(imageValidateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                //记住我
                .rememberMe()
                .userDetailsService(myUserDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 60 * 30)
                .and()
                // 登出相关配置
                .logout()
                .permitAll();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

}


