package com.ryan.simplechatview.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ListUtils;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.BaseChatViewHolder;

import java.util.List;

public class SimpleChatAdapter extends RecyclerView.Adapter<BaseChatViewHolder> {
    private static final int TYPE_HEADER = -1000;

    private List<MyChatMsg> mDatas;

    public SimpleChatAdapter(List<MyChatMsg> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public BaseChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyChatMsg.TYPE_NORMAL_TEXT:
                return new TextChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_normal_text, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseChatViewHolder holder, int position) {
        MyChatMsg msg = mDatas.get(position);
        if (holder == null || msg == null) {
            return;
        }
        holder.bindData(msg, position);
    }

    @Override
    public int getItemViewType(int position) {
        MyChatMsg msg = mDatas.get(position);
        if (msg == null) {
            return MyChatMsg.TYPE_NORMAL_TEXT;
        }
        return msg.type;
    }

    @Override
    public int getItemCount() {
        return ListUtils.isEmpty(mDatas) ? 0 : mDatas.size();
    }
}
