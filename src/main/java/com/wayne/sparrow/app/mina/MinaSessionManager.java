package com.wayne.sparrow.app.mina;

import com.alibaba.fastjson.JSON;
import com.wayne.sparrow.app.pojo.DeviceInfo;
import com.wayne.sparrow.app.pojo.ReceiveMsg;
import groovy.lang.Singleton;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.*;

@Component
@Singleton
public class MinaSessionManager {
    public static final String MSG_TYPE_REGISTER = "register";
    private static Map<String, IoSession> sessionMap;
    private static Map<Long, DeviceInfo> deviceMap;
    public boolean startService() {
        sessionMap = new HashMap<String, IoSession>();
        deviceMap = new HashMap<Long, DeviceInfo>();
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setHandler(new ServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE.BOTH_IDLE, 10);

        try {
            acceptor.bind(new InetSocketAddress(9123));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void putSession(DeviceInfo deviceInfo, IoSession session) {
        sessionMap.put(deviceInfo.getUuid(), session);
        deviceMap.put(session.getId(), deviceInfo);
    }

    public void removeSession(IoSession session) {
        DeviceInfo deviceInfo = deviceMap.get(session.getId());
        deviceMap.remove(session.getId());
        sessionMap.remove(deviceInfo.getUuid());
    }

    public static IoSession getSession(String key) {
        return sessionMap.get(key);
    }

    public List<DeviceInfo> getCurrDeviceInfoList() {
        return new ArrayList<DeviceInfo>(deviceMap.values());
    }

    public DeviceInfo getDeviceInfoByUuid(String uuid) {
        IoSession session = sessionMap.get(uuid);
        return deviceMap.get(session.getId());
    }

    class ServerHandler extends IoHandlerAdapter {

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            System.out.println("sessionCreated" + session.getId());
            super.sessionCreated(session);
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            HashMap msg = JSON.parseObject(message.toString(), HashMap.class);
            System.out.println(msg);
            System.out.println("接收的数据：" + msg.toString());
            Object msgType = msg.get("msgType");
            if (MSG_TYPE_REGISTER.equals(msgType)) {
                Object deviceInfoStr = msg.get("deviceInfo");
                if (deviceInfoStr != null) {
                    DeviceInfo deviceInfo = JSON.parseObject(deviceInfoStr.toString(), DeviceInfo.class);
                    putSession(deviceInfo, session);
                }
            }
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            removeSession(session);
            super.sessionClosed(session);
        }
    }
}
