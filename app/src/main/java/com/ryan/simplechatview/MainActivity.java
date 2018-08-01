package com.ryan.simplechatview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ryan.simplechatview.lib.SimpleChatManager;
import com.ryan.simplechatview.test.TestUtils;

/**
 * @author RyanLee
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleChatManager mSimpleChatManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        // 随机发送单条消息按钮
        FloatingActionButton mSingleMsgBtn = findViewById(R.id.fab_single_message);
        mSingleMsgBtn.setOnClickListener(this);

        // 随机发送多条消息按钮
        FloatingActionButton mMultiMsgBtn = findViewById(R.id.fab_multi_message);
        mMultiMsgBtn.setOnClickListener(this);

        RecyclerView mChatView = findViewById(R.id.chat);

        // 初始化SimpleChatManager
        mSimpleChatManager = new SimpleChatManager(mChatView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_single_message:
                mSimpleChatManager.sendSingleMsg(TestUtils.getRandomMsg());
                break;
            case R.id.fab_multi_message:
                mSimpleChatManager.sendMultiMsg(TestUtils.getRandomMsgList(20));
                break;
            default:
                break;
        }
    }

}
