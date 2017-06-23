package com.zank.store.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.zank.store.base.BaseActivity;

/**
 * Created by Zank on 2017/4/7.
 * UI控制器
 */

public class UIUtils {

    public static Context getContext() {
        return BaseActivity.getContext();
    }

    public static Activity getActivity(){
        return BaseActivity.getActivity();
    }

    public static Drawable getDrawable(int id){
        // ContextCompat为v4包下，使用时注意
        return ContextCompat.getDrawable(getContext(), id);
    }

    public static int getColor(int id){
        return ContextCompat.getColor(getContext(),id);
    }

    public static String getString(int id){
        return getContext().getResources().getString(id);
    }

    public static String[] getStringArray(int id){
        return getContext().getResources().getStringArray(id);
    }

}
