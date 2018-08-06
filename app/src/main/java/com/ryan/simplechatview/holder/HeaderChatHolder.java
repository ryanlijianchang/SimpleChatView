package com.ryan.simplechatview.holder;

import android.view.View;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.chatlib.BaseChatViewHolder;
import com.ryan.simplechatview.R;

public class HeaderChatHolder extends BaseChatViewHolder {

    public HeaderChatHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        TextView tips = (TextView) getView(R.id.tv_header_text_msg);
        tips.setText(AppUtils.getContext().getResources().getString(R.string.test_healthy_live));
    }
}
