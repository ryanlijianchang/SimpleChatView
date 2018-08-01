package com.ryan.simplechatview.demo;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.BaseChatViewHolder;
import com.ryan.simplechatview.lib.MyChatMsg;

public class ActivityNewsHolder extends BaseChatViewHolder {

    public ActivityNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(MyChatMsg data, int position) {
        TextView textView = (TextView) getView(R.id.tv_activity_news);
        Drawable drawableLeft = ContextCompat.getDrawable(AppUtils.getContext(), R.drawable.ic_activity);
        Drawable drawableRight = ContextCompat.getDrawable(AppUtils.getContext(), R.drawable.ic_arrow);
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, DensityUtils.dp2px(AppUtils.getContext(), 18), DensityUtils.dp2px(AppUtils.getContext(), 18));
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, DensityUtils.dp2px(AppUtils.getContext(), 12), DensityUtils.dp2px(AppUtils.getContext(), 12));
        }
        textView.setCompoundDrawables(drawableLeft, null, drawableRight, null);
        textView.setText(data.activityNews);
    }
}
