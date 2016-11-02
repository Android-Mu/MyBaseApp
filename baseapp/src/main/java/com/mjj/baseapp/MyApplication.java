package com.mjj.baseapp;

import android.app.Application;

/**
 * Description：全局应用程序类：用于保存和调用全局应用配置
 * Created by Mjj on 2016/11/2 0002.
 */

public class MyApplication extends Application {

    private static MyApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static MyApplication getInstance() {
        return appContext;
    }
}
