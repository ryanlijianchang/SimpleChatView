package com.ryan.simplechatview.test;

import com.ryan.baselib.util.AppUtils;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.demo.MyChatMsg;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<MyChatMsg> getChatList() {
        List<MyChatMsg> datas = new ArrayList<>();
        String[] contents = AppUtils.getContext().getResources().getStringArray(R.array.test_msg);

        for (int i = 0; i < 10; i++) {
            MyChatMsg chatMsg = new MyChatMsg();
            chatMsg.type = MyChatMsg.TYPE_NORMAL_TEXT;
            chatMsg.content = contents[i];
            datas.add(chatMsg);
        }
        return datas;
    }
}
