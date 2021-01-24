package com.hyp.learn.so.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so.model
 * hyp create at 19-12-31
 **/
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class OAuth2Client {
    private String clientId;
    private String clientSecret;
    private int accessTokenValiditySeconds;

}

