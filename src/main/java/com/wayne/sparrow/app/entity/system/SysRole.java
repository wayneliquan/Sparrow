package com.wayne.sparrow.app.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private Long id;

    private String name;

    private String code;

    private String weight;

    private Integer type;

    private Date dateModify;

    private Date dateCreated;

}