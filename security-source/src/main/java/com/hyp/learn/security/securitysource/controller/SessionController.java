package com.hyp.learn.security.securitysource.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.security.securitysource.controller
 * hyp create at 19-12-31
 **/
@RestController
public class SessionController {

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String sessionInvalid() {
        return "session失效";
    }
}

