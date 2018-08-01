package com.ryan.simplechatview.lib;

import java.util.List;

/**
 * @author RyanLee
 */
public interface ISimpleChat {

    /**
     * 发送多条消息
     * @param list
     */
    void sendMultiMsg(List<MyChatMsg> list);

    /**
     * 发送单条消息
     * @param chatMsg
     */
    void sendSingleMsg(MyChatMsg chatMsg);
}
