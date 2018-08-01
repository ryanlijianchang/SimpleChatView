package com.ryan.simplechatview.lib;

import java.util.List;

/**
 * @author RyanLee
 */
public interface ISimpleChat {

    /**
     * 发送多条消息
     * @param list List<MyChatMsg>
     */
    void sendMultiMsg(List<MyChatMsg> list);

    /**
     * 发送单条消息
     * @param chatMsg MyChatMsg
     */
    void sendSingleMsg(MyChatMsg chatMsg);

    /**
     * 更新聊天公屏
     * @param mBufferLists List<MyChatMsg>
     */
    void updateChatView(List<MyChatMsg> mBufferLists);

    /**
     * 释放资源
     */
    void release();
}
