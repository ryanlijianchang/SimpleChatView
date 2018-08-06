package com.ryan.simplechatview.lib;

import android.os.Handler;
import android.os.Looper;

import com.ryan.baselib.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 公屏缓冲池
 * @author RyanLee
 */
public class BufferChat implements IBufferChat {
    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    private ISimpleChat iSimpleChat;

    private List<MyChatMsg> mBufferLists;

    private static final int REPEAT_TIME = 400;

    private static final Object LOCK = new Object();

    public BufferChat(ISimpleChat chatManager) {
        this.iSimpleChat = chatManager;
        mBufferLists = new ArrayList<>();
    }

    @Override
    public void play() {
        mUIHandler.removeCallbacks(this);
        mUIHandler.post(this);
    }

    @Override
    public void addChat(MyChatMsg chatMsg) {
        if (chatMsg == null) {
            return;
        }
        synchronized (LOCK) {
            mBufferLists.add(chatMsg);
        }
    }

    @Override
    public void addChat(List<MyChatMsg> chatLists) {
        if (ListUtils.isEmpty(chatLists)) {
            return;
        }
        synchronized (LOCK) {
            mBufferLists.addAll(chatLists);
        }
    }

    @Override
    public void release() {
        mUIHandler.removeCallbacks(this);
        iSimpleChat = null;
        mBufferLists.clear();
        mBufferLists = null;
    }

    @Override
    public void run() {
        if (iSimpleChat == null) {
            return;
        }

        if (ListUtils.isEmpty(mBufferLists)) {
            mUIHandler.removeCallbacks(this);
            mUIHandler.postDelayed(this, REPEAT_TIME);
            return;
        }

        synchronized (LOCK) {
            iSimpleChat.updateChatView(mBufferLists);
            mBufferLists.clear();
        }
        mUIHandler.removeCallbacks(this);
        mUIHandler.postDelayed(this, REPEAT_TIME);
    }
}
