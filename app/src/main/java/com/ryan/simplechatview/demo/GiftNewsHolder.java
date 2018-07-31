package com.ryan.simplechatview.demo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.BitmapUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.baselib.widget.CenteredImageSpan;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.BaseChatViewHolder;
import com.ryan.simplechatview.lib.MyChatMsg;

/**
 * 送礼的Holder
 *
 * @author RyanLee
 */
public class GiftNewsHolder extends BaseChatViewHolder {

    public GiftNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(MyChatMsg data, int position) {
        TextView tips = (TextView) getView(R.id.tv_gift_msg);
        String strTo = AppUtils.getContext().getResources().getString(R.string.str_to);
        String strSendTo = AppUtils.getContext().getResources().getString(R.string.str_send_to);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(data.sendUserName).append(strTo).append(strSendTo).append(data.giftName);
        int leftLen = builder.length();
        builder.append(" ").append(data.giftNum);

        int imageNewSize = DensityUtils.dp2px(AppUtils.getContext(), 20);
        CenteredImageSpan imageSpan = new CenteredImageSpan(AppUtils.getContext(), BitmapUtils.decodeResToBitmap(data.giftRes, imageNewSize, imageNewSize));
        builder.setSpan(imageSpan, leftLen, leftLen + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int totalLen = builder.length();
        // 设置送礼信息颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#368CFD"));
        builder.setSpan(colorSpan, data.sendUserName.length() + strTo.length(), totalLen, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //设置用户名颜色
        colorSpan = new ForegroundColorSpan(ContextCompat.getColor(AppUtils.getContext(), R.color.color_chat_username));
        builder.setSpan(colorSpan, 0, data.sendUserName.length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tips.setText(builder);
    }

}
