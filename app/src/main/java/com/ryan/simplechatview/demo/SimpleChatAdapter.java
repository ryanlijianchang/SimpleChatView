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
            case TYPE_HEADER:
                return new HeaderChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_header_text, parent, false));
            case MyChatMsg.TYPE_NORMAL_TEXT:
                return new NormalChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_normal_text, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseChatViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            holder.bindData(null, position);
            return;
        }
        holder.bindData(mDatas.get(position - 1), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        MyChatMsg msg = mDatas.get(position - 1);
        if (msg == null) {
            return MyChatMsg.TYPE_NORMAL_TEXT;
        }
        return msg.type;
    }

    @Override
    public int getItemCount() {
        return ListUtils.isEmpty(mDatas) ? 1 : mDatas.size() + 1;
    }
}
