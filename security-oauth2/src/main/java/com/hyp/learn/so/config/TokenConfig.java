package com.hyp.learn.so.config;

import com.hyp.learn.so.model.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so.config
 * hyp create at 19-12-31
 **/
@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("myJwtKey");
        return accessTokenConverter;
    }

    /**
     * token增强器
     *
     * @return
     */
    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }


    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory());
    }

    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    public TokenStore inMemoryTokenStore() {
        return new InMemoryTokenStore();
    }
//
//    @Autowired
//    private DataSource dataSource;
//
//    public TokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
}
