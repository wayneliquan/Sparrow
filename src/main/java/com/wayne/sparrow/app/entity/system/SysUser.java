package com.wayne.sparrow.app.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    public static final int status_normal = 0;
    public static final int status_deleted = 1;
    public static final int status_froze = 2;
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private Integer status;

    private Date dateCreated;

}