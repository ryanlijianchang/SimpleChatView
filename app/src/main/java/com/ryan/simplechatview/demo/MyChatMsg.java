package com.ryan.simplechatview.demo;

import com.ryan.simplechatview.lib.BaseChatMsg;

public class MyChatMsg extends BaseChatMsg {
    public static final int TYPE_NORMAL_TEXT = 1;

    /**
     * 公屏类型
     */
    public int type;
    /**
     * 内容
     */
    public String content;
}
