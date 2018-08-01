package com.ryan.simplechatview.test;

import android.content.res.TypedArray;

import com.ryan.baselib.util.AppUtils;
import com.ryan.simplechatview.R;
import com.ryan.simplechatview.lib.MyChatMsg;

import java.util.Random;

public class TestUtils {
    private static int mRandomGift;

    private static int getRandomType() {
        int maxType = 3;
        return new Random().nextInt(maxType);
    }

    public static MyChatMsg getRandomMsg() {
        MyChatMsg msg = new MyChatMsg();
        msg.type = getRandomType();
        msg.headLight = getRandomHeadLight();
        msg.content = getRandomContent();
        msg.systemNews = getRandomSystemNews();
        msg.sendUserName = getRandomUserName();
        msg.atUserName = getRandomAtUserName();
        msg.giftName = getRandomGiftName();
        msg.giftRes = getRandomGiftRes();
        msg.giftNum = getRandomGiftNum();
        return msg;
    }

    private static int getRandomGiftRes() {
        TypedArray ar = AppUtils.getContext().getResources().obtainTypedArray(R.array.test_giftid);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        return resIds[mRandomGift];
    }

    private static String getRandomContent() {
        String[] array = AppUtils.getContext().getResources().getStringArray(R.array.test_content);
        return array[new Random().nextInt(array.length)];
    }

    private static String getRandomUserName() {
        String[] array = AppUtils.getContext().getResources().getStringArray(R.array.test_usernames);
        return array[new Random().nextInt(array.length)];
    }

    private static String getRandomAtUserName() {
        String[] array = AppUtils.getContext().getResources().getStringArray(R.array.test_atusernames);
        return array[new Random().nextInt(array.length)];
    }

    private static String getRandomGiftName() {
        String[] array = AppUtils.getContext().getResources().getStringArray(R.array.test_giftname);
        mRandomGift = new Random().nextInt(array.length);
        return array[mRandomGift];
    }


    public static String getRandomGiftNum() {
        String[] array = AppUtils.getContext().getResources().getStringArray(R.array.test_giftnum);
        return array[new Random().nextInt(array.length)];
    }

    public static String getRandomSystemNews() {
        String[] array = AppUtils.getContext().getResources().getStringArray(R.array.test_system_news);
        return array[new Random().nextInt(array.length)];
    }

    public static int getRandomHeadLight() {
        return new Random().nextInt(3);
    }
}
