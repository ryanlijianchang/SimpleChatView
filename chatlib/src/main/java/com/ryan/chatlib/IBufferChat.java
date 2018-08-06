package com.ryan.chatlib;

import java.util.List;

/**
 * @author RyanLee
 */
public interface IBufferChat<D> extends Runnable {
    /**
     * 开始
     */
    void play();

    /**
     * 添加公屏到缓冲区
     *
     * @param chatMsg D
     */
    void addChat(D chatMsg);

    /**
     * 添加公屏到缓冲区
     *
     * @param chatLists List
     */
    void addChat(List<D> chatLists);

    /**
     * 释放资源
     */
    void release();
}
