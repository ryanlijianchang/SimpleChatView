package com.ryan.simplechatview.demo;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.BaseChatViewHolder;
import com.ryan.simplechatview.lib.MyChatMsg;

public class NormalChatHolder extends BaseChatViewHolder {

    public NormalChatHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(MyChatMsg data, int position) {
        TextView text = (TextView) getView(R.id.tv_normal_text_msg);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(data.sendUserName);
        builder.append(AppUtils.getContext().getResources().getString(R.string.str_to));
        // 用户名的长度
        int leftLen = builder.length();
        if (!TextUtils.isEmpty(data.atUserName)) {
            builder.append(AppUtils.getContext().getResources().getString(R.string.str_at)).append(data.atUserName).append(" ");
        }
        // 用户名到@的长度
        int nLeftLen = builder.length();
        builder.append(data.content);
        //设置@用户名的颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#80D640"));
        builder.setSpan(colorSpan, leftLen, nLeftLen, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        text.setText(builder);
    }
}
