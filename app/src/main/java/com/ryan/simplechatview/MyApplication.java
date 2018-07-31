package com.ryan.simplechatview;

import android.app.Application;
import android.content.Context;

import com.ryan.baselib.util.AppUtils;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        AppUtils.setBaseContext(base);
    }
}
