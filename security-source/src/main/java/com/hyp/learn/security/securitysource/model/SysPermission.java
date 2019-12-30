package com.hyp.learn.security.securitysource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

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
public class SysPermission implements GrantedAuthority {
    private Long id;
    private String name;
    private String code;
    private String url;
    private String method;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.code + ":" + this.method.toUpperCase();
    }
}
