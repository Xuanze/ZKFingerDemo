package com.zhongruan.android.zkfingerdemo.ui;


import com.zhongruan.android.zkfingerdemo.config.ABLConfig;
import com.zhongruan.android.zkfingerdemo.utils.NetUtil;
import com.zhongruan.android.zkfingerdemo.utils.PreferenceUtils;

import rx.android.BuildConfig;

import static com.zhongruan.android.zkfingerdemo.utils.Utils.isEqual;
import static com.zhongruan.android.zkfingerdemo.utils.Utils.stringIsEmpty;

public class ConfigApplication {
    private static ConfigApplication mApplication;

    public static ConfigApplication getApplication() {
        if (mApplication == null) {
            mApplication = new ConfigApplication();
        }
        return mApplication;
    }

    public String getIPStr() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.SERVER_IP, ABLConfig.SERVER_IP_DEFAULT);
    }

    public void setIPStr(String ip) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.SERVER_IP, ip);
    }

    public String getKCStr() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.KC, "");
    }

    public void setKCStr(String kcStr) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.KC, kcStr);
    }

    public String getCCStr() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.CC, "");
    }

    public void setCCStr(String ccStr) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.CC, ccStr);
    }

    public String getSocketPortStr() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.SERVER_SOCKET_PORT, ABLConfig.SERVER_SOCKET_PORT_DEFAULT);
    }

    public void setSocketPortStr(String port) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.SERVER_SOCKET_PORT, port);
    }

    public void setKDConnectState(boolean connectedOrNot) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefBoolean(ABLConfig.KD_CONNECT_STATE, connectedOrNot);
    }

    public boolean getKDConnectState() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefBoolean(ABLConfig.KD_CONNECT_STATE, false);
    }

    public String getDeviceIP() {
        String device_ip = BuildConfig.VERSION_NAME;
        int netType = NetUtil.getNetType(MyApplication.getApplication());
        if (netType == 1) {
            String str;
            device_ip = NetUtil.getWifiIp(MyApplication.getApplication());
            PreferenceUtils instance = PreferenceUtils.getInstance(MyApplication.getApplication());
            String str2 = ABLConfig.DEVICE_IP;
            if (stringIsEmpty(device_ip)) {
                str = BuildConfig.VERSION_NAME;
            } else {
                str = device_ip;
            }
            instance.setPrefString(str2, str);
        } else if (netType == 3) {
            device_ip = NetUtil.getLocalIpAddress();
            PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.DEVICE_IP, stringIsEmpty(device_ip) ? BuildConfig.VERSION_NAME : device_ip);
        }
        return stringIsEmpty(device_ip) ? "当前无IP" : device_ip;
    }

    public boolean isToSocket() {
        String netMethod = PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.NET_METHOD, ABLConfig.NET_METHOD_SOCKET);
        if (isEqual(ABLConfig.NET_METHOD_SOCKET, netMethod)) {
            return true;
        }
        return false;
    }

    public void setNetMethod(String netMethod) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.NET_METHOD, netMethod);
    }

    public void setUsbImportTime(String time) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.USB_IMPORT_TIME, time);
    }

    public String getUsbImportTime() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.USB_IMPORT_TIME, ABLConfig.IMPORT_TIME);
    }

    public void setUsbExportTime(String time) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.USB_EXPORT_TIME, time);
    }

    public String getUsbExportTime() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.USB_EXPORT_TIME, ABLConfig.IMPORT_TIME);
    }

    public void setCJExportTime(String time) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.CJ_EXPORT_TIME, time);
    }

    public String getCJExportTime() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.CJ_EXPORT_TIME, ABLConfig.IMPORT_TIME);
    }

    public void setNetImportTime(String time) {
        PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.NET_IMPORT_TIME, time);
    }

    public String getNetImportTime() {
        return PreferenceUtils.getInstance(MyApplication.getApplication()).getPrefString(ABLConfig.NET_IMPORT_TIME, ABLConfig.IMPORT_TIME);
    }
}
