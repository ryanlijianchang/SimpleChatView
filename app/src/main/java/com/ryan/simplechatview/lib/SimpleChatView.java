package com.ryan.simplechatview.lib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;

/**
 * @author RyanLee
 */
public class SimpleChatView extends RecyclerView {

    public SimpleChatView(Context context) {
        this(context, null);
    }

    public SimpleChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleChatView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void runToBottom() {
        post(new Runnable() {
            @Override
            public void run() {
                // 获取底部index
                int bottomIndex = getAdapter().getItemCount() - 1;
                smoothScrollToPosition(bottomIndex);
            }
        });
    }
}
