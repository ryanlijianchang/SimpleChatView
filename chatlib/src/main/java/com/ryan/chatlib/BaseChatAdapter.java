package com.ryan.chatlib;

import android.support.v7.widget.RecyclerView;

import com.ryan.baselib.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RyanLee
 */
public abstract class BaseChatAdapter<D extends BaseChatMsg> extends RecyclerView.Adapter<BaseChatViewHolder> {

    public abstract void addItem(D chatMsg);

    public abstract void addItemList(List<D> list);

    public abstract void removeItems(int startPos, int endPos);
}
