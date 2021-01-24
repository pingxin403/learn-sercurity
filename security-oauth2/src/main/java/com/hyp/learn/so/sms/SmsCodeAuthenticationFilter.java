package com.hyp.learn.so.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so.filter
 * hyp create at 19-12-31
 **/
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public SmsCodeAuthenticationFilter(String loginProcessUrlMobile) {
        super(new AntPathRequestMatcher(loginProcessUrlMobile, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);

        // 封装令牌
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        // 开始认证
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainMobile(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        if (mobile == null) {
            mobile = "";
        }
        return mobile.trim();
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
