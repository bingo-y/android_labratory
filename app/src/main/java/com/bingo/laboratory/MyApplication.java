package com.bingo.laboratory;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by tuyx on 2017/4/19.
 * Description :
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);
    }
}
