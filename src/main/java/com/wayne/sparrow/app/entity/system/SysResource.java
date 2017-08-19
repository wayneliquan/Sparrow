package com.wayne.sparrow.app.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysResource {
    public static final Long TopPid = 0L;//顶级菜单
    private Long id;
    private Long pid; // 上级资源，
    private String url;
    private String icon;
    private Integer weight; // 排序

    private String code; // 资源编码
    private Integer type; // 资源的类型
    private Integer level; // 资源等级
    private String name; // 资源名称
    private String pname; // 上级资源名称

    private Integer status; // 0:启用(默认)， 1：禁用（删除）
    private String uniqueCode;//唯一编码
    private Date dateModify;
    private Date dateCreated;

}