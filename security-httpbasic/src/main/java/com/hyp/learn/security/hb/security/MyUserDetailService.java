package com.hyp.learn.security.hb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.security.hb.security
 * hyp create at 19-12-30
 **/
public class MyUserDetailService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    /**
     * loadUserByUsername: 读取用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserDetails: 封装用户数据的接口
        User user = new User("hyp", passwordEncoder.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));


        return user;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
