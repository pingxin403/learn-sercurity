package com.hyp.learn.ssb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ssb.config
 * hyp create at 19-12-18
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //配置用户信息服务
    //在 userDetailsService()方法中,我们返回了一个UserDetailsService给spring容器,Spring Security会使用它来
    //获取用户信息。我们暂时使用InMemoryUserDetailsManager实现类,并在其中分别创建了zhangsan、lisi两个用
    //户,并设置密码和权限。
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
//                .antMatchers("/r/r1").hasAuthority("p1")
//                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/r3").access("hasAuthority('p1') and hasAuthority('p2')")
                .antMatchers("/r/**").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                //允许表单登录
                .formLogin()
                //指定我们自己的登录页,spring security以重定向方式跳转到/login-view
                .loginPage("/login-view")
                //指定登录处理的URL,也就是用户名、密码表单提交的目的路径
                .loginProcessingUrl("/login")
                //指定登录成功后的跳转URL
                .successForwardUrl("/login‐success")
                //我们必须允许所有用户访问我们的登录页(例如为验证的用户),这个 formLogin().permitAll() 方法允许任意用户访问基于表单登录的所有的URL。
                .permitAll()


                .and()
                //提供系统退出支持,使用 WebSecurityConfigurerAdapter 会自动被应用
                .logout()
                //设置触发退出操作的URL (默认是 /logout ).
                .logoutUrl("/logout")
                //(3)退出之后跳转的URL。默认是 /login?logout
                .logoutSuccessUrl("/login-view?logout")
                //定制的 LogoutSuccessHandler ,用于实现用户退出成功时的处理。如果指定了这个选项那么logoutSuccessUrl() 的设置会被忽略
//                .logoutSuccessHandler(logoutSuccessHandler)
                //添加一个 LogoutHandler ,用于实现用户退出时的清理工作.默认 SecurityContextLogoutHandler 会被添加为最后一个 LogoutHandler
//                .addLogoutHandler(logoutHandler)
                //指定是否在退出时让 HttpSession 无效。 默认设置为 true。
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));

        //会话控制
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .expiredUrl("/login‐view?error=EXPIRED_SESSION")
                .invalidSessionUrl("/login-view?error=INVALID_SESSION");


    }


    /**
     * 配置CSRF过滤器
     *
     * @return {@link org.springframework.boot.web.servlet.FilterRegistrationBean}
     */
    @Bean
    public FilterRegistrationBean<CsrfFilter> csrfFilter() {
        FilterRegistrationBean<CsrfFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CsrfFilter(new HttpSessionCsrfTokenRepository()));
        registration.addUrlPatterns("/*");
        registration.setName("csrfFilter");
        return registration;
    }

    //spring security为防止CSRF(Cross-site request forgery跨站请求伪造)的发生,限制了除了get以外的大多数方法。
    //方法一：
    //        http.csrf().disable();
//屏蔽CSRF控制,即spring security不再限制CSRF

    //方法二
    //在login.jsp页面添加一个token,spring security会验证token,如果token合法则可以继续请求。
    //修改login.jsp
//<form action="login" method="post">
//<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
//...
//</form>
}
