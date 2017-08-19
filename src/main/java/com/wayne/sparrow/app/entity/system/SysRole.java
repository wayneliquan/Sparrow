package com.wayne.sparrow.app.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private Long id;

    private String name; // 角色的名称

    private String code; // 角色的code, 以ROLE_XXX

    private Integer weight; // 角色的权重

    private Integer type; // 角色的类别； 1: admin, 2: Operator, 3:manager, 4: user

    private Date dateModify;

    private Date dateCreated;
}