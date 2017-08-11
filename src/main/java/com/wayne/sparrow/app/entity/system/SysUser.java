package com.wayne.sparrow.app.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private Integer status;

    private Date dateCreated;

}