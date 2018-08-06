package com.ryan.simplechatview;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.chatlib.BaseChatViewHolder;

public class ActivityNewsHolder extends BaseChatViewHolder {

    public ActivityNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        MyChatMsg data = (MyChatMsg) obj;
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
