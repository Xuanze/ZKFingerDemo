package com.zhongruan.android.zkfingerdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;


public class NetUtil {
    public static final int NET_TYPE_DEFAULT = 0;
    public static final int NET_TYPE_ETHERNET = 3;
    public static final int NET_TYPE_GPRS = 2;
    public static final int NET_TYPE_NONE = -1;
    public static final int NET_TYPE_WIFI = 1;


    public static int getNetType(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return NET_TYPE_NONE;
        }
        if (info.getType() == NET_TYPE_WIFI) {
            return NET_TYPE_WIFI;
        }
        if (info.getType() == 0) {
            return NET_TYPE_GPRS;
        }
        if (info.getType() == 9) {
            return NET_TYPE_ETHERNET;
        }
        return NET_TYPE_DEFAULT;
    }


    public static String getWifiIp(Context context) {
        WifiManager wifimanage = (WifiManager) context.getSystemService(WIFI_SERVICE);
        if (!wifimanage.isWifiEnabled()) {
            return null;
        }
        WifiInfo wifiInfo = wifimanage.getConnectionInfo();
        String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
        return ipAddress;
    }


    public static String getLocalIpAddress() {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                Enumeration enumIpAddr = ((NetworkInterface) en.nextElement()).getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}
