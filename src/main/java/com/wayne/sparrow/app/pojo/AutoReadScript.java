package com.wayne.sparrow.app.pojo;

import lombok.Data;

@Data
public class AutoReadScript {
    private Long autoreadScriptId;
    private Long apkInfoId;
    private int widthPixeis;
    private int heightPixeis;
    private String script;
}
