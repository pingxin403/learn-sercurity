package com.hyp.learn.ds.uaa.model;

import lombok.Data;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ssb.model
 * hyp create at 19-12-19
 **/
@Data
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
