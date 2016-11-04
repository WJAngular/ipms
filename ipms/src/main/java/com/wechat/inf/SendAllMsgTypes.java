/**
 * 微信公众平台�?发模�?(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.wechat.inf;

/**
 * 消息类型
 *

 */
public enum SendAllMsgTypes {
    TEXT("text"),
    IMAGE("image"),
    VOICE("voice"),
    MPVIDEO("mpvideo"),
    MPNEWS("mpnews"),
    VIDEO("video");

    private String type;

    SendAllMsgTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
