package com.wayne.sparrow.core.constants;

import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.core.util.ContextHolderUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionConstants {
    private  static final String SESSION_CONSTANTS_KEY = "com.wayne.app.core.constants.SessionConstants.SESSION_CONSTANTS_KEY";

    public static HttpSession getSession() {
        return ContextHolderUtils.getSession();
    }

    public static Map<Object, Object> getMap(){
        Map<Object, Object> map = (Map<Object, Object>)getSession().getAttribute(SESSION_CONSTANTS_KEY);
        if(map == null){
            map = new HashMap<>();
            getSession().setAttribute(SESSION_CONSTANTS_KEY,map);
        }
        return map;
    }

    public static void reset(){
        getSession().invalidate();
        getSession().setAttribute(SESSION_CONSTANTS_KEY, new HashMap<>());
    }

    public static void put(Object key, Object value){
        getMap().put(key, value);
    }

    public static Object get(Object key){
        return getMap().get(key);
    }

    public static <T> T get(Object key, T defaltValue){
        return (T) getMap().getOrDefault(key, defaltValue);
    }

    public static SysUser getUser(){
        return (SysUser)getMap().get("user");
    }

    public static void setUser(SysUser user){
        getSession().setAttribute("user", user);
        getMap().put("user", user);
    }
}
