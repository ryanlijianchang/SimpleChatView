package com.ryan.chatlib;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.baselib.util.ResourceUtils;

import java.util.List;

/**
 * 公屏消息管理器
 *
 * @author RyanLee
 */
public class SimpleChatManager<D extends BaseChatMsg> implements ISimpleChat<D> {
    private static final int DEFAULT_ITEM_SPACE = DensityUtils.dp2px(AppUtils.getContext(), 3);
    private static final int DEFAULT_SCROLL_ITEM_NUM = 10;
    private static final int DEFAULT_MAX_CHAT_NUM = 100;

    private RecyclerView mChatView;
    private BaseChatAdapter mAdapter;
    private LinearLayoutManager mLinearManager;

    /**
     * Item间隔
     */
    private int mItemSpace = DEFAULT_ITEM_SPACE;
    /**
     * 默认滚动条数
     */
    private int mScrollItemNum = DEFAULT_SCROLL_ITEM_NUM;
    /**
     * 缓冲区
     */
    private IBufferChat iBufferChat;


    public SimpleChatManager(@NonNull RecyclerView mRecyclerView) {
        this.mChatView = mRecyclerView;
    }

    private void initBufferChat() {
        iBufferChat = new BufferChat(this);
        iBufferChat.play();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addTouchListener() {
        mChatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //handledScroll = true;
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    /**
     * 监听是否滑动到最新
     */
    private void addScrollListener() {
        mChatView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleIndex = getLastVisibleIndex();
                    int itemSize = getItemSize();
                    if (lastVisibleIndex == itemSize - 1) {
                        hideNewsView();
                    }
                }
            }
        });
    }

    private void initChatView() {
        // 设置Item间距
        mChatView.addItemDecoration(new ChatDecoration(mItemSpace));
        // 设置LayoutManager
        mLinearManager = new LinearLayoutManager(AppUtils.getContext());
        mChatView.setLayoutManager(mLinearManager);
        mChatView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        mChatView.setAdapter(mAdapter);
    }


    @Override
    public void sendMultiMsg(List<D> list) {
        if (iBufferChat != null) {
            iBufferChat.addChat(list);
        }
    }

    @Override
    public void sendSingleMsg(D chatMsg) {
        if (iBufferChat != null) {
            iBufferChat.addChat(chatMsg);
        }
    }

    @Override
    public void updateChatView(List<D> mBufferLists) {
        // 如果不是在底部，则不会自动滚动到最新的消息
        boolean isAtBottom = isAtBottom();
        if (!isAtBottom) {
            addNewsView();
            mAdapter.addItemList(mBufferLists);
            removeOverItems();
        } else {
            mAdapter.addItemList(mBufferLists);
            removeOverItems();
            runToBottom();
        }


    }

    @Override
    public void release() {
        hideNewsView();
        if (iBufferChat != null) {
            iBufferChat.release();
        }
    }

    private void removeOverItems() {
        int dataSize = getDataSize();
        int mMaxChatNum = DEFAULT_MAX_CHAT_NUM;
        if (dataSize > mMaxChatNum) {
            int beyondSize = dataSize - mMaxChatNum;
            mAdapter.removeItems(0, beyondSize);
        }
    }

    /**
     * 是否处于底部
     *
     * @return true 是 false 否
     */
    private boolean isAtBottom() {
        return mLinearManager.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1;
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
            int chatIndex = parent.getChildCount();
            for (int i = 0; i < parent.getChildCount(); i++) {
                if (parent.getChildAt(i).equals(mChatView)) {
                    chatIndex = i;
                }
            }
            parent.addView(newsView, chatIndex + 1);

            ViewGroup.LayoutParams layoutParams = newsView.getLayoutParams();
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ConstraintLayout.LayoutParams newsViewLps = (ConstraintLayout.LayoutParams) layoutParams;
                newsViewLps.bottomToBottom = mChatView.getId();
                newsView.setLayoutParams(newsViewLps);
            } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams newsViewLps = (RelativeLayout.LayoutParams) layoutParams;
                newsViewLps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                int chatBottom = mChatView.getBottom();
                int parentHeight = parent.getHeight();
                newsViewLps.bottomMargin = parentHeight - chatBottom;
                newsView.setLayoutParams(newsViewLps);
            } else if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams newsViewLps = (FrameLayout.LayoutParams) layoutParams;
                newsViewLps.gravity = Gravity.BOTTOM;
                int chatBottom = mChatView.getBottom();
                int parentHeight = parent.getHeight();
                newsViewLps.bottomMargin = parentHeight - chatBottom;
                newsViewLps.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                newsViewLps.height = FrameLayout.LayoutParams.WRAP_CONTENT;
                newsView.setLayoutParams(newsViewLps);
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams newsViewLps = (LinearLayout.LayoutParams) layoutParams;
                newsViewLps.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                newsViewLps.height = DensityUtils.dp2px(AppUtils.getContext(), 36);
                newsViewLps.topMargin = -newsViewLps.height;
                newsView.setLayoutParams(newsViewLps);
            }

            newsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    runToBottom();
                }
            });
        }
        newsView.setVisibility(View.VISIBLE);
        showTipsAnimation(newsView);
    }

    private void showTipsAnimation(TextView newsView) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        alphaAnimation.setDuration(1100);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        newsView.startAnimation(alphaAnimation);
    }

    private void hideNewsView() {
        TextView newsView = getNewsTipsView();
        if (newsView != null) {
            newsView.clearAnimation();
            newsView.setVisibility(View.GONE);
        }
    }

    private int getLastVisibleIndex() {
        return mLinearManager.findLastCompletelyVisibleItemPosition();
    }

    private int getItemSize() {
        return mAdapter.getItemCount();
    }

    private int getDataSize() {
        return mAdapter.getItemCount() - 1;
    }

    /**
     * 获取News提示的View
     *
     * @return textView
     */
    private TextView getNewsTipsView() {
        ViewGroup parent = (ViewGroup) mChatView.getParent();
        return parent.findViewById(R.id.chat_news_id);
    }

    public void setAdapter(BaseChatAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void ready() {
        initChatView();
        addScrollListener();
        addTouchListener();
        initBufferChat();
    }

}
