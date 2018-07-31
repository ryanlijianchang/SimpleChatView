package com.ryan.simplechatview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.simplechatview.demo.ChatDecoration;
import com.ryan.simplechatview.demo.SimpleChatAdapter;
import com.ryan.simplechatview.lib.SimpleChatView;
import com.ryan.simplechatview.test.TestUtils;

/**
 * @author RyanLee
 */
public class MainActivity extends AppCompatActivity {
    private SimpleChatView mChatView;
    private SimpleChatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChatView = findViewById(R.id.chat);
        mAdapter = new SimpleChatAdapter(TestUtils.getChatList());
        mChatView.addItemDecoration(new ChatDecoration(DensityUtils.dp2px(AppUtils.getContext(), 3)));
        mChatView.setLayoutManager(new LinearLayoutManager(this));
        mChatView.setAdapter(mAdapter);
    }
}
