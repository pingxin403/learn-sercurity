package com.hyp.learn.dsm.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ds.uaa.config
 * hyp create at 19-12-20
 **/
@Configuration
public class TokenConfig {
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }


    private String SIGNING_KEY = "uaa123";

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称秘钥,资源服务器使用该秘钥来验证
        return converter;
    }
}
