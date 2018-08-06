package com.ryan.simplechatview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ListUtils;
import com.ryan.chatlib.BaseChatAdapter;
import com.ryan.chatlib.BaseChatViewHolder;
import com.ryan.simplechatview.ActivityNewsHolder;
import com.ryan.simplechatview.MyChatMsg;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.holder.GiftNewsHolder;
import com.ryan.simplechatview.holder.HeaderChatHolder;
import com.ryan.simplechatview.holder.NormalChatHolder;
import com.ryan.simplechatview.holder.SystemNewsHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RyanLee
 */
public class SimpleChatAdapter extends BaseChatAdapter<MyChatMsg> {
    private static final int TYPE_HEADER = -1000;

    private List<MyChatMsg> mDatas;

    SimpleChatAdapter(List<MyChatMsg> datas) {
        if (ListUtils.isEmpty(datas)) {
            mDatas = new ArrayList<>();
        } else {
            mDatas = datas;
        }
    }

    @Override
    public BaseChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyChatMsg.TYPE_NORMAL_TEXT:
                return new NormalChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_normal_text, parent, false));
            case MyChatMsg.TYPE_SYSTEM_NEWS:
                return new SystemNewsHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_system_news_text, parent, false));
            case MyChatMsg.TYPE_GIFT_MSG:
                return new GiftNewsHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_gift_text, parent, false));
            case MyChatMsg.TYPE_ACTIVITY_NEWS:
                return new ActivityNewsHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_activity_news, parent, false));
            case TYPE_HEADER:
            default:
                return new HeaderChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_header_text, parent, false));
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

    @Override
    public synchronized void removeItems(int startPos, int endPos) {
        mDatas.subList(startPos, endPos).clear();
        notifyItemRangeRemoved(1, (endPos - startPos));
    }


    @Override
    public synchronized void addItem(MyChatMsg chatMsg) {
        mDatas.add(chatMsg);
        notifyItemInserted(getItemCount());
    }

    @Override
    public synchronized void addItemList(List<MyChatMsg> list) {
        int startPos = getItemCount();
        int addSize = ListUtils.isEmpty(list) ? 0 : list.size();
        mDatas.addAll(list);
        notifyItemRangeInserted(startPos, addSize);
    }


}
