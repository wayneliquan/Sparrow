package com.wayne.sparrow.core.common;

import lombok.Data;

/**
 * Created by zhanliquan on 17-6-22.
 * Description:
 */
@Data
public class OperationMessage {
    private String message = "操作成功!"; //返回字符串

    private boolean success = true; //默认操作成功

    private Integer type = 1;  //类别，没有特别意义，可当做是扩展字段

    public OperationMessage(){}

    public OperationMessage(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public OperationMessage(String message) {
        super();
        this.message = message;
    }
}
