package com.wayne.sparrow.app.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ApkInfo {
    private Long apkInfoId;
    private String apkName;
    private String packageName;
    private String versionName;
    private String downloadUrl;
    private Long fileId;
    private Date dateModify;
    private Date dateCreated;
}
