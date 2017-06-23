package com.zank.store.utils;

import rx.Subscription;

/**
 * Created by Zank on 2017/4/7.
 * RxJava控制器
 */

public class RxUtils {

    // RxJava订阅
    public static Subscription subscription;

    // Activity退出时应取消订阅关系
    public static void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
