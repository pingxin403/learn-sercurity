package com.hyp.learn.security.securitysource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

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
public class SysUser {
    private Long id;
    private String username;
    private String password;

    private List<SysPermission> sysPermissions;
}
