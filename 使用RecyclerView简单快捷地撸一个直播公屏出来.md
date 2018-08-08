# SimpleChatView #

## 前言 ##

虽然现在直播已经没有了当前那么火爆，但是仍然是很多App盈利收入的一个重要功能，像现在的网易新闻客户端、抖音短视频等都有引入直播这个功能，而公屏是直播的一个重要工具，所以我们了解一下公屏的实现也是有点必要的，公屏的实现可以有挺多做法的，但是就目前来讲，我认为比较快捷的方式就是使用RecyclerView来实现，仅此献上拙见。

## 功能 ##

公屏最简单的就是通过一个可滑动的列表进行展示用户发送出来的消息，当然，一般都是通过服务器给客户端推送单条或者一组数据，然后客户端再把新来的数据添加到原有的数据源上，再刷新列表。当然，我们可以给公屏支持多样式的消息，使公屏看起来更加丰富；其次，我们可以把数据源增加大小的限制，防止数据池过大导致内存暴增；再之，可以给数据源增加缓存功能，把收集回来的数据放到缓存池中，然后定时把缓存池更新到公屏上，避免对公屏过于频繁的绘制。

满足以上功能，基本上就可以成为一个及格的公屏了，本文也是基于以上几个基本的功能点进行分析，然后代码实现，其中难免会有不合理的地方，希望大家指出。

**1.插入数据**

支持插入单条或者多条数据，并且插入后在公屏中展示出来。

![image](http://osjnd854m.bkt.clouddn.com/ssss.gif)


**2.支持多样式公屏**

如上图所示，目前有四种样式的公屏，第一种就是系统通知（第一条绿色的直播规则），第二种就是普通的聊天消息，第三种就是送礼消息，第四种就是活动通知（粉红色的公屏），当然，如果需要更多种，都是可以继续增添的。

**3.支持公屏大小限制**

如下图所示，我限制了公屏数量是100条，当我继续发送公屏时，数据源的旧数据就会被擦除掉，然后新的数据才会放到数据池里面。这样做可以避免一些热门房间的刷公屏导致的内存过高的问题。

![image](http://osjnd854m.bkt.clouddn.com/dddddddd.gif)


**4.缓存池**

这里加了缓存池，大概400ms才会进行数据刷新，所以400ms内进行的插入操作都会先放到缓存池里面，然后时间到了之后才会对UI刷新，这样就可以避免UI的频繁绘制导致GPU过高的问题。

## 实现 ##

首先布局文件就是一个RecyclerView，以上实现的功能都是对RecyclerView的API进行调用罢了，我就不进行细讲，就主要的功能点进行分析。

**1.插入数据**

插入数据就是在RecyclerView的Adapter对数据源进行增加，然后notify即可。当然，RecyclerView是支持局部更新的，为了性能问题，我们在插入数据之后不需要刷新整个数据源，而是只更新新增的数据即可。

如果只是插入一条，可以调用

    notifyItemInserted(getItemCount());
    
如果是插入多条，可以调用

    notifyItemRangeInserted(startPos, addSize);
    
OK，那插入数据之后，我们就可以调用RecyclerView的`smoothScrollToPosition(int position)`方法，让数据滚到到你需要的位置，这里我们是滚到到最底部，即：

    // 获取底部index
    int bottomIndex = mAdapter.getItemCount() - 1;
    mChatView.smoothScrollToPosition(bottomIndex);


**2.多样式的公屏**

相信你对RecyclerView的getItemType是比较了解的，我们就可以根据消息的不同类型加载不同的layout文件，例如：

    @Override
    public BaseChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyChatMsg.TYPE_NORMAL_TEXT:
                return new NormalChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_normal_text, parent, false));
            case MyChatMsg.TYPE_SYSTEM_NEWS:
                return new SystemNewsHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_system_news_text, parent, false));
            case MyChatMsg.TYPE_GIFT_MSG:
                return new GiftNewsHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_gift_text, parent, false));
            default:
                return new HeaderChatHolder(LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.layout_header_text, parent, false));
        }
    }
    
然后，我们就可以在不同的layout里面进行不同View的定制。由于我们上图中的UI都是比较简单，所以我们的layout都是一个TextView即可满足，配合SpannableStringBuilder就可以做一些富文本的展示。

**3.限制公屏大小**

这个也是比较简单，只要在我们插入数据之后，比较数据源的大小和我们限制的大小，然后把超出的部分移除掉，然后再更新UI即可，例如：

    private void removeOverItems() {
        //获取数据源大小
        int dataSize = getDataSize();
        //获取定义的最大限制，如100
        int mMaxChatNum = DEFAULT_MAX_CHAT_NUM;
        if (dataSize > mMaxChatNum) {
            // 计算超出的大小
            int beyondSize = dataSize - mMaxChatNum;
            // 数据源把旧的数据源超出的部分移除
            mDatas.subList(0, beyondSize).clear();
            // 更新UI
            notifyItemRangeRemoved(1, beyondSize);
        }
    }
    
**4.缓存池**

缓存池其实就是自定义一个Runnable，然后在run()方法内，对Adapter的数据源变更，然后notify一下，这个Runnable是定时重复执行，例如400ms，避免了每插入一条数据就对Adapter进行notify造成的频繁绘制。详细代码请看源码。

## 源码 ##

大概功能介绍完成，如果有兴趣深入了解，可以移步到[SimpleChatView](https://github.com/ryanlijianchang/SimpleChatView)，如果你觉得还OK，可以给个Star支持一下。