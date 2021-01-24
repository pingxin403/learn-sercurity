package com.hyp.learn.so.config;

import com.hyp.learn.so.model.OAuth2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对于clients.secret设置，网上大部分都是直接赋值一个明文，究竟设置为明文还是密文取决与SecurityConfiguration类中配置的PasswordEncoder是什么，如果PasswordEncoder为BCryptPasswordEncoder，此时clients.secret也必须设置为BCryptPasswordEncoder加密后的密文，如果PasswordEncoder为上文注释的自定义的密码加密器(该实现任何情况下都会返回true)，此时clients.secret可以设置为明文。
 *
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so
 * hyp create at 19-12-31
 **/
@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        List<OAuth2Client> clientList = Arrays.asList(
                new OAuth2Client("clientId", "clientSecret", 7200),
                new OAuth2Client("clientId2", "clientSecret2", 0));

        InMemoryClientDetailsServiceBuilder inMemory = clients.inMemory();
        // 可以通过多次调用withClient来配置多对clientId和secret
        clientList.forEach(client -> {
            inMemory // 使用in-memory存储
                    .withClient(client.getClientId())
                    .secret(new BCryptPasswordEncoder().encode(client.getClientSecret()))
                    // token的生效时间2小时, 0 表示永久生效不过期
                    .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                    .authorizedGrantTypes("authorization_code", "refresh_token", "password", "implicit")
                    .scopes("all", "read", "write")
                    .redirectUris("http://www.baidu.com");
        });


//        clients.inMemory() // 使用in-memory存储
//                .withClient("clientId")
//                .secret(new BCryptPasswordEncoder().encode("clientSecret"))
//                .authorizedGrantTypes("authorization_code")
//                .scopes("all")
//                .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Autowired
    @Qualifier("redisTokenStore")
    private TokenStore redisTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("jwtTokenEnhancer")
    private TokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(redisTokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

        // jwt增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(tokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);

        endpoints
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);

    }


}

