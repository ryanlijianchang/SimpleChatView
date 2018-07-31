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
    /**
     * 默认Item间距
     */
    private static final int DEFAULT_ITEM_SPACE = DensityUtils.dp2px(AppUtils.getContext(), 3);
    /**
     * Item间距
     */
    private int mItemSpace = DEFAULT_ITEM_SPACE;

    private LinearLayoutManager mLinearManager;

    public SimpleChatView(Context context) {
        this(context, null);
    }

    public SimpleChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleChatView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView();
    }

    private void initView() {
        // 设置Item间距
        addItemDecoration(new ChatDecoration(mItemSpace));
        // 设置LayoutManager
        mLinearManager = new LinearLayoutManager(AppUtils.getContext());
        setLayoutManager(mLinearManager);
    }


    public void runToBottom() {
        post(new Runnable() {
            @Override
            public void run() {
                // 获取底部index
                int bottomIndex = getAdapter().getItemCount() - 1;
                // 获取最后一个可见的index
                int lastVisibleIndex = mLinearManager.findLastVisibleItemPosition();

                smoothScrollToPosition(bottomIndex);
            }
        });
    }

}
