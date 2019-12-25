package com.hyp.learn.dsm.order.model;

import lombok.Data;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ssb.model
 * hyp create at 19-12-19
 **/
@Data
public class PermissionDto {
    private Integer id;
    private String code;
    private String description;
    private String url;

}
