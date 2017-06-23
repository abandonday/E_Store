package com.zank.store.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zank on 2017/4/7.
 * Activity控制器
 */

public class ActivityCollector {

    // Activity数组
    private static final List<Activity> activityList = new ArrayList<>();

    // 添加Activity
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    // 删除Activity
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    // 删除所有Activity
    public static void removeAllActivity(){
        for (Activity activity : activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
