package com.hyp.learn.so.controller;

import com.hyp.learn.so.sms.SmsCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.so.controller
 * hyp create at 19-12-31
 **/
@RestController
public class SmsValidateCodeController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/code/sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SmsCode createCode(@RequestHeader("deviceId") String deviceId, String mobile) {
        SmsCode smsCode = createSmsCode();
        System.out.println("验证码发送成功：" + smsCode);

        String key = "code:sms:"+ deviceId;
        stringRedisTemplate.opsForValue().set(key, smsCode.getCode());

        return smsCode;
    }

    private SmsCode createSmsCode() {
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return new SmsCode(code, 30000);
    }
}

