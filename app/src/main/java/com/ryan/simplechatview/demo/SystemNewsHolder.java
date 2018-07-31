package com.ryan.simplechatview.demo;

import android.view.View;
import android.widget.TextView;

import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.BaseChatViewHolder;
import com.ryan.simplechatview.lib.MyChatMsg;

public class SystemNewsHolder extends BaseChatViewHolder{
    public SystemNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(MyChatMsg data, int position) {
        TextView tips = (TextView) getView(R.id.tv_system_news_msg);
        tips.setText(data.systemNews);
    }
}
