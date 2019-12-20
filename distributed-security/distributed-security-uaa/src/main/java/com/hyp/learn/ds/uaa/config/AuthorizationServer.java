package com.hyp.learn.ds.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务配置总结:授权服务配置分成三大块,可以关联记忆。
 * <p>
 * 1. 既然要完成认证,它首先得知道客户端信息从哪儿读取,因此要进行客户端详情配置。
 * 2. 既然要颁发token,那必须得定义token的相关endpoint,以及token如何存取,以及客户端支持哪些类型的token。
 * 3. 既然暴露除了一些endpoint,那对这些endpoint可以定义一些安全上的约束等。
 *
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ds.uaa.config
 * hyp create at 19-12-20
 **/
//@EnableAuthorizationServer
//@Configuration
public class AuthorizationServer implements AuthorizationServerConfigurer {


    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;


    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    /**
     * AuthorizationServerTokenServices 接口定义了一些操作使得你可以对令牌进行一些必要的管理,令牌可以被用来
     * 加载身份信息,里面包含了这个令牌的相关权限
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();

        service.setClientDetailsService(clientDetailsService);
        //是否支持刷新token
        service.setSupportRefreshToken(true);
        //token存储
        service.setTokenStore(tokenStore);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }


    /**
     * ClientDetailsServiceConfigurer 能够使用内存或者JDBC来实现客户端详情服务(ClientDetailsService),
     * ClientDetailsService负责查找ClientDetails,而ClientDetails有几个重要的属性如下列表:
     * <p>
     * 1. clientId :(必须的)用来标识客户的Id。
     * 2. secret :(需要值得信任的客户端)客户端安全码,如果有的话。
     * 3. scope :用来限制客户端的访问范围,如果为空(默认)的话,那么客户端拥有全部的访问范围。
     * 4. authorizedGrantTypes :此客户端可以使用的授权类型,默认为空。
     * 5. authorities :此客户端可以使用的权限(基于Spring Security authorities)。
     * <p>
     * 客户端详情(Client Details)能够在应用程序运行的时候进行更新,可以通过访问底层的存储服务(例如将客户端详情存储在一个关系数据库的表中,就可以使用 JdbcClientDetailsService)或者通过自己实现ClientRegistrationService接口(同时你也可以实现 ClientDetailsService 接口)来进行
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService)
        //使用in-memory存储
        clients.inMemory()
                //client_id：(必须的)用来标识客户的Id。
                .withClient("c1")
                //secret :(需要值得信任的客户端)客户端安全码,如果有的话。
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("rest1")
                //authorizedGrantTypes :此客户端可以使用的授权类型,默认为空。
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                //scope :用来限制客户端的访问范围,如果为空(默认)的话,那么客户端拥有全部的访问范围。
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");
    }

    /**
     * 这个属性是用来设置授权码服务的(即 AuthorizationCodeServices 的实例对
     * 象),主要用于 "authorization_code" 授权码类型模式。
     */
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    /**
     * 认证管理器,当你选择了资源所有者密码(password)授权类型的时候,请设置
     * 这个属性注入一个 AuthenticationManager 对象。
     */
    @Autowired
    private AuthenticationManager authenticationManager;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //将客户端信息存储到数据库
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder());
        return clientDetailsService;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //设置授权码模式的授权码如何存取,暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * AuthorizationServerEndpointsConfigurer 这个配置对象有一个叫做 pathMapping() 的方法用来配置端点URL链接,它有两个参数:
     * 第一个参数: String 类型的,这个端点URL的默认链接。
     * 第二个参数: String 类型的,你要进行替代的URL链接。
     * <p>
     * 以上的参数都将以 "/" 字符为开始的字符串,框架的默认URL链接如下列表,可以作为这个 pathMapping() 方法的
     * 第一个参数:
     * /oauth/authorize :授权端点。
     * /oauth/token :令牌端点。
     * /oauth/confirm_access :用户确认授权提交端点。
     * /oauth/error :授权服务错误信息端点。
     * /oauth/check_token :用于资源服务访问的令牌解析端点。
     * /oauth/token_key :提供公有密匙的端点,如果你使用JWT令牌的话。
     * <p>
     * 需要注意的是授权端点这个URL应该被Spring Security保护起来只供授权用户访问.
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);

    }


    /**
     * AuthorizationServerSecurityConfigurer :用来配置令牌端点(Token Endpoint)的安全约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //tokenkey这个endpoint当使用JwtToken且使用非对称加密时,资源服务用于获取公钥而开放的,这里指这个endpoint完全公开
        security.tokenKeyAccess("permitAll()")
                //checkToken这个endpoint完全公开
                .checkTokenAccess("permitAll()")
                //允许表单认证
                .allowFormAuthenticationForClients()
        ;
    }

}
