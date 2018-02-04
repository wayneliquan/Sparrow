package com.wayne.sparrow.app.po.wechat;

import lombok.Data;

@Data
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;
}
