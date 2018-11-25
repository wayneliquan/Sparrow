package com.wayne.sparrow.app.pojo.msg;

import com.wayne.sparrow.app.pojo.DeviceInfo;
import lombok.Data;

@Data
public class TaskMsg extends BaseMsg {
    private String uuid;
    private DeviceInfo apkInfo;
    private String script;
}
