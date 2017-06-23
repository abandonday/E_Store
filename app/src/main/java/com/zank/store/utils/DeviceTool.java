package com.zank.store.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.zank.store.constant.CtNetWork;

/**
 * Created by Zank on 2017/4/24.
 * 获取设备的所有信息
 */

public class DeviceTool {
    /**
     * 获取序列号
     */
    public static String getDeviceId(Context context) {
        String deviceId = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
            if (deviceId == null || "".equals(deviceId)) {
                deviceId = "0000000000";
            }
        } catch (Exception e) {
            deviceId = "";
        }
        return deviceId;
    }

    /**
     * 获取手机型号
     */

    /**
     * 获取手机Android版本
     */

    /**
     * 获取当前手机号码
     */
    public static String getNativePhoneNumber(Context context) {
        String number = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            number = telephonyManager.getLine1Number();
        } catch (Exception e) {
            number = "";
        }
        return number;
    }

    /**
     * 获取wifimac地址
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        return wifiMac;
    }

    /**
     * 判断是否已连接到网络.
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查网络接连类型
     * SysConstants.NETWORK_TYPE_NONE: 无网络连接;
     * SysConstants.NETWORK_TYPE_WIFI: 通过WIFI连接网络;
     * SysConstants.NETWORK_TYPE_WAP: 通过GPRS连接网络.
     */
    public static int checkNetWorkType(Context context) {
        // 是否飞行模式
        if (isAirplaneModeOn(context)) {
            return CtNetWork.NETWORK_TYPE_NONE;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return CtNetWork.NETWORK_TYPE_WIFI;
        }

        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
            String type = connectivityManager.getActiveNetworkInfo().getExtraInfo();
            if ("wap".equalsIgnoreCase(type.substring(type.length() - 3))) {
                return CtNetWork.NETWORK_TYPE_WAP;
            } else {
                return CtNetWork.NETWORK_TYPE_NET;
            }
        }

        return CtNetWork.NETWORK_TYPE_NONE;
    }

    /**
     * 判断手机是否处于飞行模式.
     */
    public static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;

    }
}
