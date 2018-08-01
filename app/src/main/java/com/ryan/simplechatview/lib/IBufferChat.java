package com.ryan.simplechatview.lib;

import java.util.List;

public interface IBufferChat extends Runnable{
    /**
     * 开始
     */
    void play();

    /**
     * 添加公屏到缓冲区
     * @param chatMsg MyChatMsg
     */
    void addChat(MyChatMsg chatMsg);

    /**
     * 添加公屏到缓冲区
     * @param chatLists
     */
    void addChat(List<MyChatMsg> chatLists);

    /**
     * 释放资源
     */
    void release();
}
