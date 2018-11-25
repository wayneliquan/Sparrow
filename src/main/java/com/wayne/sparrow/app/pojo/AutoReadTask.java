package com.wayne.sparrow.app.pojo;

import com.alibaba.fastjson.JSON;
import com.wayne.sparrow.app.mina.MinaSessionManager;
import lombok.Data;
import org.apache.mina.core.session.IoSession;

import java.util.HashMap;
import java.util.Map;

@Data
public class AutoReadTask implements Runnable {

    private DeviceInfo deviceInfo;
    private ApkInfo apkInfo;
    private String script;

    public AutoReadTask(DeviceInfo deviceInfo, ApkInfo apkInfo, String script) {
        this.deviceInfo = deviceInfo;
        this.apkInfo = apkInfo;
        this.script = script;
    }

    @Override
    public void run() {
        IoSession session = MinaSessionManager.getSession(deviceInfo.getUuid());
        if (session != null && session.isActive()) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msgType", "task");
            msg.put("uuid", deviceInfo.getUuid());
            msg.put("apkInfo", JSON.toJSONString(apkInfo));
            msg.put("script", script);
            System.out.println(msg);
            session.write(JSON.toJSONString(msg));
        }
    }
}
