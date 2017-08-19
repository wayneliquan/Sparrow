package com.wayne.sparrow.core.common.controller;

import com.wayne.sparrow.core.common.OperationMessage;

/**
 * Created by zhanliquan on 17-8-11.
 * Description:
 */
public class BaseController {
    public OperationMessage initOpMsg() {
        return new OperationMessage();
    }

    public String getModulePath(){
        return "";
    }

    public String getModuleName() {
        return "";
    }
}
