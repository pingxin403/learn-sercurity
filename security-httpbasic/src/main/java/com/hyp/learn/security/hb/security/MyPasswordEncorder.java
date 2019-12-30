package com.hyp.learn.security.hb.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.security.hb.security
 * hyp create at 19-12-30
 **/
public class MyPasswordEncorder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString() + "@";
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }
}
