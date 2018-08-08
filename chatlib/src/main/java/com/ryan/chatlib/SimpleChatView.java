package com.ryan.chatlib;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

/**
 * @author RyanLee
 */
public class SimpleChatView<D extends BaseChatMsg, T extends BaseChatAdapter> extends RecyclerView {
    private SimpleChatManager<D> mSimpleChatManager;

    public SimpleChatView(Context context) {
        this(context, null);
    }

    public SimpleChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleChatView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        mSimpleChatManager = new SimpleChatManager<>(this);
    }

    public SimpleChatView setAdapter(T mBaseAdapter) {
        if (mSimpleChatManager != null) {
            mSimpleChatManager.setAdapter(mBaseAdapter);
        }
        return this;
    }

    public void sendSingleMsg(D msg) {
        if (mSimpleChatManager != null) {
            mSimpleChatManager.sendSingleMsg(msg);
        }
    }

    public void sendMultiMsg(List<D> datas) {
        if (mSimpleChatManager != null) {
            mSimpleChatManager.sendMultiMsg(datas);
        }
    }

    public SimpleChatView setBufferTime(@IntRange(from = 0) int bufferTime) {
        if (mSimpleChatManager != null) {
            mSimpleChatManager.setBufferTime(bufferTime);
        }
        return this;
    }

    public void setUp() {
        if (mSimpleChatManager != null) {
            mSimpleChatManager.ready();
        }
    }
}
