package com.ryan.simplechatview.lib;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.baselib.util.ResourceUtils;
import com.ryan.simplechatview.R;

import java.util.List;

/**
 * 公屏消息管理器
 *
 * @author RyanLee
 */
public class SimpleChatManager implements ISimpleChat {
    private static final int DEFAULT_ITEM_SPACE = DensityUtils.dp2px(AppUtils.getContext(), 3);
    private static final int DEFAULT_SCROLL_ITEM_NUM = 10;

    private RecyclerView mChatView;
    private SimpleChatAdapter mAdapter;
    private LinearLayoutManager mLinearManager;

    /**
     * Item间隔
     */
    private int mItemSpace = DEFAULT_ITEM_SPACE;
    /**
     * 默认滚动条数
     */
    private int mScrollItemNum = DEFAULT_SCROLL_ITEM_NUM;


    public SimpleChatManager(RecyclerView mRecyclerView) {
        this.mChatView = mRecyclerView;

        initChatView();
    }

    private void initChatView() {
        mAdapter = new SimpleChatAdapter(null);
        // 设置Item间距
        mChatView.addItemDecoration(new ChatDecoration(mItemSpace));
        // 设置LayoutManager
        mLinearManager = new LinearLayoutManager(AppUtils.getContext());
        mChatView.setLayoutManager(mLinearManager);
        mChatView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        mChatView.setAdapter(mAdapter);
    }


    @Override
    public void sendMultiMsg(List<MyChatMsg> list) {
        mAdapter.addItemList(list);
        //runToBottom();
        addNewsView();
    }

    @Override
    public void sendSingleMsg(MyChatMsg chatMsg) {
        mAdapter.addItem(chatMsg);
        runToBottom();
    }

    private void runToBottom() {
        mChatView.post(new Runnable() {
            @Override
            public void run() {
                // 获取底部index
                int bottomIndex = mAdapter.getItemCount() - 1;
                // 获取最后一条可见的
                int lastVisibleIndex = mLinearManager.findLastVisibleItemPosition();
                if (bottomIndex - lastVisibleIndex >= mScrollItemNum) {
                    // 如果最后一条可见的Item和数据源最后一条Item的间隔超过mScrollItemNum
                    // 则先移动到最后mScrollItemNum条
                    mChatView.scrollToPosition(bottomIndex - mScrollItemNum);
                }
                mChatView.smoothScrollToPosition(bottomIndex);
                hideNewsView();
            }
        });
    }

    private void addNewsView() {
        ViewGroup parent = (ViewGroup) mChatView.getParent();
        TextView newsView = parent.findViewById(R.id.chat_news_id);
        if (newsView == null) {
            newsView = new TextView(mChatView.getContext());
            newsView.setId(R.id.chat_news_id);
            newsView.setText(ResourceUtils.getString(AppUtils.getContext(), R.string.str_news));
            newsView.setTextColor(ResourceUtils.getCorlor(AppUtils.getContext(), R.color.color_chat_news));
            newsView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            Drawable arrowDrawable = ResourceUtils.getDrawable(AppUtils.getContext(), R.drawable.ic_arrow_bottom);
            int drawableSize = DensityUtils.dp2px(AppUtils.getContext(), 12);
            if (arrowDrawable != null) {
                arrowDrawable.setBounds(0, 0, drawableSize, drawableSize);
                newsView.setCompoundDrawables(null, null, arrowDrawable, null);
                newsView.setCompoundDrawablePadding(5);
            }
            newsView.setBackgroundResource(R.drawable.bg_chat_news);
            parent.addView(newsView);

            ConstraintLayout.LayoutParams newsViewLps = (ConstraintLayout.LayoutParams) newsView.getLayoutParams();
            newsViewLps.bottomToBottom = mChatView.getId();

            newsView.setLayoutParams(newsViewLps);
            newsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    runToBottom();
                }
            });
        }
        newsView.setVisibility(View.VISIBLE);
    }

    private void hideNewsView() {
        ViewGroup parent = (ViewGroup) mChatView.getParent();
        TextView newsView = parent.findViewById(R.id.chat_news_id);
        if (newsView != null) {
            newsView.setVisibility(View.GONE);
        }
    }
}
