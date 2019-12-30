package com.hyp.learn.security.securitysource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author hyp
 * Project name is security-source
 * Include in com.hyp.learn.security.securitysource
 * hyp create at 19-12-30
 **/
@RestController
public class ExampleController {

    @GetMapping("helloworld")
    public List<String> helloworld() {
        return Arrays.asList("Spring Security simple demo");
    }
}
