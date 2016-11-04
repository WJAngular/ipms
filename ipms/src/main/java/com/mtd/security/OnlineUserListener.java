package com.mtd.security;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

/**
 * Written by Wooce Yang on 2016/1/9.
 */
public class OnlineUserListener implements HttpSessionListener {
    public static HashMap sessionMap = new HashMap();

    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        // 初始化当前session
        sessionMap.put(session.getId(), session);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        // 判断当前session user是否有值
        if (session.getAttribute("user") != null   && session.getAttribute("user").toString().length() > 0) {
            // session销毁清空map 更新map
            sessionMap.remove(session.getAttribute("user").toString());
            session.invalidate();
        }
    }
}
