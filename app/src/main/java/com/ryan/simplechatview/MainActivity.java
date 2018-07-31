package com.ryan.simplechatview;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.simplechatview.lib.ChatDecoration;
import com.ryan.simplechatview.lib.MyChatMsg;
import com.ryan.simplechatview.lib.SimpleChatAdapter;
import com.ryan.simplechatview.lib.SimpleChatView;
import com.ryan.simplechatview.test.TestUtils;

/**
 * @author RyanLee
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 默认Item间距
     */
    private static final int DEFAULT_ITEM_SPACE = DensityUtils.dp2px(AppUtils.getContext(), 3);

    private FloatingActionButton mRandomSendBtn;

    private SimpleChatView mChatView;
    private SimpleChatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandomSendBtn = findViewById(R.id.fab_add_comment);
        mRandomSendBtn.setOnClickListener(this);

        mChatView = findViewById(R.id.chat);
        mAdapter = new SimpleChatAdapter(null);
        // 设置Item间距
        mChatView.addItemDecoration(new ChatDecoration(DEFAULT_ITEM_SPACE));
        // 设置LayoutManager
        LinearLayoutManager mLinearManager = new LinearLayoutManager(AppUtils.getContext());
        mChatView.setLayoutManager(mLinearManager);
        mChatView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        mChatView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_comment:
                sendRandomMsg();
                break;
            default:
                break;
        }
    }

    private void sendRandomMsg() {
        MyChatMsg chatMsg = TestUtils.getRandomMsg();
        mAdapter.addItem(chatMsg);
        mChatView.runToBottom();
    }
}
