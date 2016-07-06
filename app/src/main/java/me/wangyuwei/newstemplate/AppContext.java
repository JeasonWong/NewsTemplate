package me.wangyuwei.newstemplate;

import android.app.Application;
import android.content.Context;

/**
 * 作者： 巴掌 on 16/7/6 21:28
 */
public class AppContext extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext(){
        return sContext;
    }
}
