package com.ryan.simplechatview.demo;

import android.view.View;
import android.widget.TextView;

import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.BaseChatMsg;
import com.ryan.simplechatview.lib.BaseChatViewHolder;

public class NormalChatHolder extends BaseChatViewHolder {

    public NormalChatHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(BaseChatMsg data, int position) {
        MyChatMsg msg = (MyChatMsg) data;
        TextView text = (TextView) getView(R.id.tv_normal_text_msg);
        text.setText(msg.content);
    }
}
