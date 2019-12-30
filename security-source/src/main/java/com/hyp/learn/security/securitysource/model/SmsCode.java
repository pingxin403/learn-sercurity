package com.hyp.learn.security.securitysource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author hyp
 * Project name is security-source
 * Include in com.hyp.learn.security.securitysource.model
 * hyp create at 19-12-30
 **/
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SmsCode {
    private String code;
    private LocalDateTime expireTime;


    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
