package com.wayne.sparrow.app.pojo;

import lombok.Data;

import java.util.HashMap;

@Data
public class SentMsg {
    private String uuid;
    private String msgType;
    private HashMap<String, Object> msg;
}
