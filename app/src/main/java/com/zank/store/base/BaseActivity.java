package com.zank.store.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.zank.store.utils.ActivityCollector;
import com.zank.store.utils.LogUtils;

import butterknife.ButterKnife;

/**
 * Created by Zank on 2017/4/6.
 * Activity基类
 */

public class BaseActivity extends Activity {

    private static Context context;
    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        activity = this;
        // 开启日志记录
        LogUtils.i("BaseActivity", getClass().getSimpleName());
        // 注册注解
        ButterKnife.bind(this);
        // 添加Activity
        ActivityCollector.addActivity(this);

    }

    public static Context getContext() {
        return context;
    }

    public static Activity getActivity() {
        return activity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
