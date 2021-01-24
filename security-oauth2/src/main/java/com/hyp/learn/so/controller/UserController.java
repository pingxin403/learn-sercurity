package com.hyp.learn.so.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so.controller
 * hyp create at 19-12-31
 **/
@RestController
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/user1/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public String me01() throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        return objectMapper.writeValueAsString(principal);
    }

    @GetMapping(value = "/user2/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public String me02(Authentication auth, HttpServletRequest request) throws JsonProcessingException, UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = header.split("bearer ")[1];
        Claims claims = Jwts.parser().setSigningKey("myJwtKey".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String foo = (String) claims.get("foo");

        System.out.println(foo);

        return objectMapper.writeValueAsString(auth);
    }
}

