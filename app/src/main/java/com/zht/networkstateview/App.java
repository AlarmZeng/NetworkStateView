package com.zht.networkstateview;

import android.app.Application;
import android.os.Handler;

/**
 * Created by ZHT on 2017/4/15.
 */

public class App extends Application {

    private static App mContext;

    private static Handler mMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mMainThreadHandler = new Handler();
    }

    public static App getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
}
