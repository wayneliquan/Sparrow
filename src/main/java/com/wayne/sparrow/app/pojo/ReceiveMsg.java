package com.wayne.sparrow.app.pojo;

import lombok.Data;

import java.util.HashMap;

@Data
public class ReceiveMsg {

    private DeviceInfo deviceInfo;
    private HashMap<String, Object> msg;
}
