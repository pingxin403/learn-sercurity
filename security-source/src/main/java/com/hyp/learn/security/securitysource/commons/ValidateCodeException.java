package com.hyp.learn.security.securitysource.commons;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.ServletRequestBindingException;

/**
 * @author hyp
 * Project name is security-source
 * Include in com.hyp.learn.security.securitysource.commons
 * hyp create at 19-12-30
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String message) {
        super(message);
    }
}
