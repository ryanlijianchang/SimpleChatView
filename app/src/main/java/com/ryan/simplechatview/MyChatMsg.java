package com.ryan.simplechatview;

import com.ryan.chatlib.BaseChatMsg;

public class MyChatMsg extends BaseChatMsg {
    /**
     * 普通聊天消息
     */
    public static final int TYPE_NORMAL_TEXT = 0;
    /**
     * 系统消息
     */
    public static final int TYPE_SYSTEM_NEWS = 1;
    /**
     * 礼物消息
     */
    public static final int TYPE_GIFT_MSG = 2;
    /**
     * 活动消息
     */
    public static final int TYPE_ACTIVITY_NEWS = 3;


    // 头灯类型 VIP
    public static final int HEAD_LIGHT_VIP = 1;
    // 头灯类型 钻石用户
    public static final int HEAD_LIGHT_DIAMOND = 2;


    /**
     * 公屏类型
     */
    public int type;
    /**
     * 尾灯类型
     * TYPE_VIP = 1, TYPE_DIAMOND = 2
     */
    public int headLight;
    /**
     * 内容
     */
    public String content;
    /**
     * 系统消息
     */
    public String systemNews;
    /***
     * 活动消息
     */
    public String activityNews;
    /**
     * 发送者名字
     */
    public String sendUserName;
    /**
     * 艾特的用户名
     */
    public String atUserName;
    /**
     * 礼物名字
     */
    public String giftName;
    /**
     * 礼物图片
     */
    public int giftRes;
    /**
     * 礼物数量
     */
    public String giftNum;
}
