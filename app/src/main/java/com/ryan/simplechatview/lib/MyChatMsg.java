package com.ryan.simplechatview.lib;

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
     * 公屏类型
     */
    public int type;
    /**
     * 内容
     */
    public String content;
    /**
     * 系统消息
     */
    public String systemNews;
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
