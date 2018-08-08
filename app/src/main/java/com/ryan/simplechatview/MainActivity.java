package com.ryan.simplechatview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ryan.chatlib.SimpleChatView;
import com.ryan.simplechatview.testdata.TestUtils;

/**
 * @author RyanLee
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleChatView<MyChatMsg, SimpleChatAdapter> mChatView;

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

        mChatView = findViewById(R.id.chat);

        SimpleChatAdapter adapter = new SimpleChatAdapter(null);
        mChatView
                // 设置Adapter
                .setAdapter(adapter)
                // 设置缓冲时间50ms
                .setBufferTime(50)
                // 最后调用setUp
                .setUp();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_single_message:
                mChatView.sendSingleMsg(TestUtils.getRandomMsg());
                break;
            case R.id.fab_multi_message:
                mChatView.sendMultiMsg(TestUtils.getRandomMsgList(10));
                break;
            default:
                break;
        }
    }

}
