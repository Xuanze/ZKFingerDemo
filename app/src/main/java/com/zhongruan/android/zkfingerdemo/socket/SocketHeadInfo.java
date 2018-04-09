package com.zhongruan.android.zkfingerdemo.socket;


import android.content.Context;
import android.content.SharedPreferences;

import com.zhongruan.android.zkfingerdemo.BuildConfig;
import com.zhongruan.android.zkfingerdemo.config.ABLConfig;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.ui.MyApplication;
import com.zhongruan.android.zkfingerdemo.utils.BeanToMapUtil;
import com.zhongruan.android.zkfingerdemo.utils.PreferenceUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class SocketHeadInfo {
    private static final String[] FILEDS;
    private static final String[] KEYS;
    Map<String, Object> map;
    private SharedPreferences preferences;
    SocketHeadBean socketBean;

    static {
        FILEDS = new String[]{"SessionID:", "UserName:", "PassWord:", "Version:", "Content-Type:", "ContentName:", "ResultInfo:", "WsWsNo:", "WsWsIp:", "ContentInfo:", "Date:", "Content-Length:"};
        KEYS = new String[]{"sessionID", "userName", "passWord", "version", "contentType", "contentName", "resultInfo", "wsWsNo", "wsWsIp", "contentInfo", "dateTime", "contentLength"};
    }

    public SocketHeadInfo(Context context) {
        this.socketBean = new SocketHeadBean();
        this.map = new HashMap();
        this.preferences = PreferenceUtils.getInstance(MyApplication.getApplication()).getPreferences();
        this.socketBean.setWsWsIp(this.preferences.getString(ABLConfig.DEVICE_IP, BuildConfig.VERSION_NAME));
        this.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
    }

    public String getHeadInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("SessionID: " + this.socketBean.getSessionID() + "\r\n");
        sb.append("UserName: " + this.socketBean.getUserName() + "\r\n");
        sb.append("PassWord: " + this.socketBean.getPassWord() + "\r\n");
        sb.append("Version: " + this.socketBean.getVersion() + "\r\n");
        sb.append("Content-Type: " + this.socketBean.getContentType() + "\r\n");
        sb.append("ContentName: " + this.socketBean.getContentName() + "\r\n");
        sb.append("ResultInfo: " + this.socketBean.getResultInfo() + "\r\n");
        sb.append("WsWsNo: " + this.socketBean.getWsWsNo() + "\r\n");
        sb.append("WsWsIp: " + this.socketBean.getWsWsIp() + "\r\n");
        sb.append("ContentInfo: " + this.socketBean.getContentInfo() + "\r\n");
        sb.append("Date: " + this.socketBean.getDateTime() + "\r\n");
        sb.append("Content-Length: " + this.socketBean.getContentLength() + "\r\n");
        return sb.toString();
    }

    public void setHeadInfo(byte[] headerInfoByte) {
        try {
            String headerStr = new String(headerInfoByte, "UTF-8");
            String[] strs = headerStr.split("\r\n");
            int i;
            if (strs.length == FILEDS.length) {
                for (i = 0; i < strs.length; i++) {
                    if (strs[i].contains(FILEDS[i])) {
                        this.map.put(KEYS[i], strs[i].substring(strs[i].indexOf(FILEDS[i]) + FILEDS[i].length()).trim());
                    }
                }
            } else {
                for (i = 0; i < strs.length; i++) {
                    for (CharSequence contains : FILEDS) {
                        if (strs[i].contains(contains)) {
                            this.map.put(KEYS[i], strs[i].substring(strs[i].indexOf(FILEDS[i]) + FILEDS[i].length()).trim());
                            break;
                        }
                    }
                }
            }
            this.socketBean = (SocketHeadBean) BeanToMapUtil.map2Bean(this.map, SocketHeadBean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
