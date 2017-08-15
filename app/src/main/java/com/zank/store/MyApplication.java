package com.zank.store;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.zank.store.utils.DeviceTool;

/**
 * Created by Zank on 2017/8/15.
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 阿里热更新initialize最好放在attachBaseContext最前面,否则极有可能导致崩溃而查询服务器是否有可用补丁的操作可以在后面的任意地方。
        SophixManager.getInstance().setContext(this)
                .setAppVersion(DeviceTool.getAPKVersion(this))
                .setSecretMetaData("24585529-1","00ef9a7a2e7716f9176518f1b848a716","MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnlZ5eSnGWqytBiUrf2+N/aistHg3V4KjFzfIHSd1PQc2/WdqyOPGG6qa4yIIAQpfZ72QKloZqcFhyk9Ep/blEAZBsm31MjZEFxnMykBFRBvFy48ceGiYgU3SWW33nEtLtiKkw3YxNyLiLdOx6tTPyADkq52drfFYy3dWUbUY6vBb79v7M4WNd656MHXfOmnEMgYcV1x/1AgXqt0L/TAvde3SMAkXmgdaTU7o3PjAUw3z65tZRwdZ5JDZzf3GsyE8iBeoDhn1S0Do6cMg0wcclgyGC8QgyuBNtYCc6G6RTvKCxRovN2XVx3O2zYy60LfLvzOE0rFCsVN7Dm82RR3OVAgMBAAECggEASbVT/5LsnOFSWDPjm8xiJlNzBBgY674xXTb0weHqFgcSUJxbxVGoYbfpdpKyIx0nHvLc5I9KNZyOOm7ra3WuO8wYUrydAsOtSoX7evzEsQfFwMuQc5v6svMh71P0b2LnJRSbneOD515mVOgrbtkuLL2OZ2F6Klt4qkzJuMvLPN9uCJ6mM93P608f7nCqC9GmSGql2Ze07h7HVH2tBfUwXC+Hx3DCeBdQmkiizmLbeMHcL8y7RI4peXnVUOhFbF0hdGxQV/s5qhL4fN3ypjTLJl5nRCwahibA5XQsPuQKadhpkO1SmKdgrXokdleGZHwhqIwYDjBpFPmC+4x9CtjhwQKBgQD65lwneIRJ9txmNjiLPc+/YBLbnGWHb1c6FfaFZpAxBSC2+PkB5KVDktuWQgB57GWQ3Y8FHTV4fNvINIyW7B6E3ArDwjnfYDSnkZyTE2ex3TVDvm/g3bj3jnYmDem0qwg7Un5Y/iPpDYAPEwVZt6d+YAK1rDWDR7faXpotT3RPcQKBgQCq/bMUVrMEhZex7Dh93cSFTXdqWLh0Pnps4UZ6TFzXV7YnojfdsolubH9y6VWaapK4r82kqeH0bU+bxetrA0jxRktAOJTwh/c7BX3FdPtl0Ma+DDwIu+1K9Plsy1ENmTVkNnHXlYdfSSuSHFtWtrbo5iuXW2EKgEFUCBQRJI/cZQKBgQDWNmujv3+i7sOJ+O0Z5PJEgLBZFBSTJ3qfuzS4/LJLSAeAcN2YwjBfmzkj9sIVVsH4h1GemiuW7XNwYX1PNdVwtbXzIRbsImWSU/4MjFrSPv1FE4jNwkA0DEvPfb11ZTkn/Oym6oSaa85UEHdW/Dhi63Ikzhz6MvdD19Vmrhmt4QKBgQCjeBOCODjA8+7IPGrtedxtDFhDpToCd9CW0u+L8AE6JjKQxXbfTdYxaSyW8RfTnltSkU/qQjcwdGiNJbQhBGzxrSA372AEbBghpuhMo7/L4MJ/4jZqqy76YCUsq1t7wqLWgW5oih13LP6oms9/oQJQueumsceeQGs0KNz2XpviOQKBgQD1cItu5BWxW3ifKMx3jdlULuGjyIo2It1+WpvpF5wJXUsHE7majXUXiColx82N80Z9ZMNDRzOc0T5xvdoHJYmKz7YgtBK6NGpZ+zh7iIZx8WQl7lGMf7Rmua+2z+tIwnMQc63hsZF8h/9UgzyhQhWA32XOx1M20D5Jy9WsslUbDA==")
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
