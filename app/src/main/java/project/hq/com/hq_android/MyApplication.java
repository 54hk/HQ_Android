package project.hq.com.hq_android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import project.hq.com.hq_android.utils.auto.AutoOptions;
import project.hq.com.hq_android.utils.auto.AutoUtils;

/**
 * Created by 54hk on 2017/12/2.
 */

public class MyApplication extends Application {
    public static Activity baseActivity;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
//        初始化 AutoUtils ------> 屏幕适配
        AutoOptions builder = new AutoOptions.Builder().init(this).setAutoType(AutoOptions
                .AutoType.PX).setHasStatusBar(true).setDesign(750, 1334).build();
        //设置AutoOptions
        AutoUtils.setAutoOptions(builder);

    }
}
