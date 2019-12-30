package com.hyp.learn.security.hb.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.security.hb.security
 * hyp create at 19-12-30
 **/
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //ObjectMapper: jackson框架的工具类，用于转换对象为json字符串
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param request
     * @param response
     * @param authentication ：代表认证成功后的信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //返回json字符串给前端
        Map result = new HashMap();
        result.put("succcess",true);

        String json = objectMapper.writeValueAsString(result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }


}
