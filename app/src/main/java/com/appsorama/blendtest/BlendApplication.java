package com.appsorama.blendtest;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class BlendApplication extends Application {

    private static BlendApplication singleton;
    private Handler mHandler = new Handler();

    public static BlendApplication getInstance() {
        return singleton;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public void handlerPost(Runnable runnable) {
        mHandler.post(runnable);
    }
}
