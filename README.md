# README #

[![Download](https://img.shields.io/badge/Download-V0.0.4-blue.svg)](https://bintray.com/ryanlijianchang/maven/SimpleChatView)
[![License](https://img.shields.io/badge/license-Apache2.0-green.svg)](
https://github.com/ryanlijianchang/SimpleChatView)
[![Build](https://img.shields.io/circleci/project/github/RedSparr0w/node-csgo-parser.svg)](
https://github.com/ryanlijianchang/SimpleChatView)

使用SimpleChatView实现公屏效果。实现效果如下：


# 用法 #

### Step 1 ###

在你的`build.gradle`中添加依赖。

    implementation 'com.ryan.chatlib:chatlib:0.0.4'

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

在Activity创建完成之后，通过findView找到SimpleChatView，然后创建Adapter（需要继承BaseChatAdapter，同时需要传入你的数据类型），最后调用提供接口即可。

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
5. `initPageParams(int pageMargin, int leftPageVisibleWidth)`：动态配置页边距和左右页可视宽度/高度
6. `getScrolledPosition()`：获取当前位置
7. `getLinearLayoutManager()`：获取LayoutManager
8. `getOrientation()`：获取当前的滑动方向 HORIZONTAL:0 VERTICAL:1
9. `autoPlay(boolean)`：是否自动播放
10. `intervalTime(int interval)`：自动播放间隔时间，单位ms
11. `initPosition(int position)`：开始处于的位置

# 实现 #

具体实现过程已在掘金上发布了，如果你感兴趣，可以跳转到[这里](https://juejin.im/post/5a30fe5a6fb9a045132ab1bf)。如果你觉得可以帮助到你，不妨点个Star。

# 版本特性 #

查看更多，请转移至[Releases](https://github.com/ryanlijianchang/Recyclerview-Gallery/releases)。

**V1.1.2**
1. BUG FIX。Fix By @[tingshuonitiao](https://github.com/tingshuonitiao)修复第一次进入时第0张图片leftPageVisibleWidth不展示的bug

**V1.1.1**

1. BUG FIX。修复第一次初始化时设置默认位置不对的问题。提供接口initPosition(int pos)设置初始位置，初始后可以直接调用smoothScrollToPosition(int pos)移动到需要的位置。
2. 更换设置ScrollListener时机。

**V1.1.0**

1. BUG FIX。修复在Fragment上使用出现的异常问题。
2. 增加自动播放接口。

**V1.0.9**

1. BUG FIX。修复滑动动画不顺畅问题。

**V1.0.8**

1. BUG FIX。修复从其他图片切换到第一张图片时抖动的问题。

**V1.0.7**

1. BUG FIX。修复横竖屏切换时UI异常问题。

**V1.0.6**

1. BUG FIX By @[jefshi](https://github.com/jefshi)。去除单例，修复前后台切换后，位置不对的问题。
2. BUG FIX By @[jefshi](https://github.com/jefshi)。修复接受到微信、QQ等消息通知后，图片高度不对问题。
3. Gradle升级V4.4。

**V1.0.5**

1. BUG FIX。修复了GalleryRecyclerView从前台切换到后台时闪退，位置错乱等问题。

**V1.0.4**

1. 增加helper属性，包括LinearySnapHelper和PagerSnapHelper。

**V1.0.3**

1. 修复了移动一页理论消耗距离应该是图片宽度加上2倍页边距。
2. 修复了修改页边距和可视宽度之后，没有生效。

**V1.0.2**

1. BUG FIX。修复LayoutManager使用非LinearyLayoutManager时不抛出异常。 

**V1.0.1**

1. BUG FIX。首次打开，获得焦点后滑动至第0项，避免第0项的margin不对。

**V1.0.0**

1. GalleryRecyclerview支持实现Gallery效果。
2. 支持动态修改滑动速度（像素/s）。
3. 支持动态修改切换动画的参数因子。
4. 支持配置动画类型。
5. 支持点击事件。
6. 支持动态配置页边距和左右页可视宽度/高度。



# License #

    
    Copyright 2017 ryanlijianchang
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.