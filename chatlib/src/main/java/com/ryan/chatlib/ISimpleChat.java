package com.ryan.chatlib;

import java.util.List;

/**
 * @author RyanLee
 */
public interface ISimpleChat<D extends BaseChatMsg> {

    /**
     * 发送多条消息
     *
     * @param list List<D>
     */
    void sendMultiMsg(List<D> list);

    /**
     * 发送单条消息
     *
     * @param chatMsg D
     */
    void sendSingleMsg(D chatMsg);

    /**
     * 更新聊天公屏
     *
     * @param mBufferLists List<D>
     */
    void updateChatView(List<D> mBufferLists);

    /**
     * 释放资源
     */
    void release();
}
