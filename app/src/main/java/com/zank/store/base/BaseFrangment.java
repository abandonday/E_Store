package com.zank.store.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Zank on 2017/4/6.
 * Fragmnet基类
 */

public class BaseFrangment extends Fragment {

    private static Context context;
    private static Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        fragment = this;
    }
    public Context getContext() {
        return context;
    }
}
