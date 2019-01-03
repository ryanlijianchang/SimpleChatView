# README #

[![Download](https://img.shields.io/badge/Download-V0.0.5-blue.svg)](https://bintray.com/ryanlijianchang/maven/SimpleChatView)
[![License](https://img.shields.io/badge/license-Apache2.0-green.svg)](
https://github.com/ryanlijianchang/SimpleChatView)
[![Build](https://img.shields.io/circleci/project/github/RedSparr0w/node-csgo-parser.svg)](
https://github.com/ryanlijianchang/SimpleChatView)

使用SimpleChatView实现公屏效果。实现效果如下：

![image](https://user-gold-cdn.xitu.io/2018/8/3/164fb844f5af8369?imageslim)

# 用法 #

### Step 1 ###

在你的`build.gradle`中添加依赖。

    implementation 'com.ryan.chatlib:chatlib:0.0.5'

### Step 2 ###

在你的layout文件中使用`SimpleChatView`。

	<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:id="@+id/cl_container"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <com.ryan.chatlib.SimpleChatView
	        android:id="@+id/chat"
	        android:layout_width="match_parent"
	        android:layout_height="300dp"
	        android:layout_marginBottom="45dp"
	        android:layout_marginEnd="100dp"
	        android:layout_marginLeft="@dimen/dp5"
	        android:layout_marginRight="100dp"
	        android:layout_marginStart="@dimen/dp5"
	        app:layout_constraintBottom_toBottomOf="parent" />
	
	</android.support.constraint.ConstraintLayout>

### Step 3 ###

定义你的数据接口，需要继承`BaseChatMsg`，如创建`MyChatMsg`:

	public class MyChatMsg extends BaseChatMsg {
		public int type;
		public String content;
	}

### Step 4 ###

创建适配器，需要继承`BaseChatAdapter`，并把数据接口传入，因为`BaseChatAdapter`使用的是继承于`BaseChatMsg`的泛型，所以需要把你定义的数据接口传入，如创建`SimpleChatAdapter`：

	public class SimpleChatAdapter extends BaseChatAdapter<MyChatMsg> {
		
		// 实现RecyclerView.Adapter必须实现的接口
		...
	
		// 还需要实现这三个接口
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

### Step 5 ###

在Activity创建完成之后，通过id绑定SimpleChatView，然后创建适配器，最后调用提供接口即可。

    mChatView = findViewById(R.id.chat);

    SimpleChatAdapter adapter = new SimpleChatAdapter(null);
    mChatView
            // 设置Adapter
            .setAdapter(adapter)
            // 设置缓冲时间50ms
            .setBufferTime(50)
            // 最后调用setUp
            .setUp();

在你需要释放资源的地方调用release()方法，释放资源：


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mChatView != null) {
            mChatView.release();
        }
    }

# API #

1. `setAdapter(T mBaseAdapter`：设置适配器
2. `sendSingleMsg(D msg)`：发送单条消息
3. `sendMultiMsg(List<D> datas)`：发送多条消息
4. `setBufferTime(@IntRange(from = 0) int bufferTime)`：设置缓冲时间，单位ms
5. `setUp()`：装载，必须最后调用
6. `release()`：释放资源

# 实现 #

具体实现过程已在掘金上发布了，如果你感兴趣，可以跳转到[这里](https://juejin.im/post/5b63345051882519ba007802#comment)。如果你觉得可以帮助到你，不妨点个Star。

# 版本特性 #

查看更多，请转移至[Releases](https://github.com/ryanlijianchang/SimpleChatView/releases)。


# License #

    
    Copyright 2018 ryanlijianchang
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.