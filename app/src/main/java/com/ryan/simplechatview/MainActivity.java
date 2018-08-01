package com.ryan.simplechatview;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.simplechatview.lib.ChatDecoration;
import com.ryan.simplechatview.lib.MyChatMsg;
import com.ryan.simplechatview.lib.SimpleChatAdapter;
import com.ryan.simplechatview.lib.SimpleChatView;
import com.ryan.simplechatview.test.TestUtils;

import java.util.List;

/**
 * @author RyanLee
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 默认Item间距
     */
    private static final int DEFAULT_ITEM_SPACE = DensityUtils.dp2px(AppUtils.getContext(), 3);

    private FloatingActionButton mSingleMsgBtn;
    private FloatingActionButton mMultiMsgBtn;

    private SimpleChatView mChatView;
    private SimpleChatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSingleMsgBtn = findViewById(R.id.fab_single_message);
        mSingleMsgBtn.setOnClickListener(this);

        mMultiMsgBtn = findViewById(R.id.fab_multi_message);
        mMultiMsgBtn.setOnClickListener(this);

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
            case R.id.fab_single_message:
                sendRandomSingleMsg();
                break;
            case R.id.fab_multi_message:
                sendRandomMultiMsg();
                break;
            default:
                break;
        }
    }

    private void sendRandomMultiMsg() {
        List<MyChatMsg> list = TestUtils.getRandomMsgList(20);
        mAdapter.addItemList(list);
        mChatView.runToBottom();
    }

    private void sendRandomSingleMsg() {
        MyChatMsg chatMsg = TestUtils.getRandomMsg();
        mAdapter.addItem(chatMsg);
        mChatView.runToBottom();
    }
}
