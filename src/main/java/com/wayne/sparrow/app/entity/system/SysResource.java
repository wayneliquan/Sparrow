package com.wayne.sparrow.app.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysResource {
    private Long id;

    private Long pid;

    private String url;

    private String icon;

    private String weight;

    private String uniqueCode;

    private Integer status;

    private Date dateModify;

    private Date dateCreated;

}