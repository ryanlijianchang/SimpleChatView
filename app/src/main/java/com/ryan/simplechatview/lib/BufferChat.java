package com.ryan.simplechatview.lib;

import android.os.Handler;
import android.os.Looper;

import com.ryan.baselib.util.ListUtils;

import java.util.List;

public class BufferChat implements IBufferChat {
    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    private ISimpleChat iSimpleChat;

    private List<MyChatMsg> mBufferMsgs;

    private static final int REPEAT_TIME = 400;

    private static final Object LOCK = new Object();

    public BufferChat(ISimpleChat chatManager) {
        this.iSimpleChat = chatManager;
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
            mBufferMsgs.add(chatMsg);
        }
    }

    @Override
    public void addChat(List<MyChatMsg> chatLists) {
        if (ListUtils.isEmpty(chatLists)) {
            return;
        }
        synchronized (LOCK) {
            mBufferMsgs.addAll(chatLists);
        }
    }

    @Override
    public void release() {
        mUIHandler.removeCallbacks(this);
        iSimpleChat = null;
        mBufferMsgs.clear();
        mBufferMsgs = null;
    }

    @Override
    public void run() {
        if (iSimpleChat == null) {
            return;
        }

        if (ListUtils.isEmpty(mBufferMsgs)) {
            mUIHandler.removeCallbacks(this);
            mUIHandler.postDelayed(this, REPEAT_TIME);
        }

        synchronized (LOCK) {
            iSimpleChat.updateChatView(mBufferMsgs);
            mBufferMsgs.clear();
        }
        mUIHandler.removeCallbacks(this);
        mUIHandler.postDelayed(this, REPEAT_TIME);
    }
}
