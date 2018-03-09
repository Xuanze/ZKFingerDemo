package com.zhongruan.android.zkfingerdemo.socket;

import java.io.Serializable;

import rx.android.BuildConfig;

public class SocketHeadBean implements Serializable {
    private String contentInfo;
    private int contentLength;
    private int contentName;
    private int contentType;
    private String dateTime;
    private String passWord;
    private int resultInfo;
    private String sessionID;
    private String userName;
    private int version;
    private String wsWsIp;
    private String wsWsNo;

    public SocketHeadBean() {
        this.sessionID = null;
        this.userName = null;
        this.passWord = null;
        this.version = 0;
        this.contentType = 0;
        this.contentName = 0;
        this.resultInfo = 1;
        this.wsWsNo = BuildConfig.VERSION_NAME;
        this.wsWsIp = null;
        this.contentInfo = null;
        this.dateTime = null;
        this.contentLength = 0;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentType() {
        return this.contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getContentName() {
        return this.contentName;
    }

    public void setContentName(int contentName) {
        this.contentName = contentName;
    }

    public int getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(int resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getWsWsNo() {
        return this.wsWsNo;
    }

    public void setWsWsNo(String wsWsNo) {
        this.wsWsNo = wsWsNo;
    }

    public String getWsWsIp() {
        return this.wsWsIp;
    }

    public void setWsWsIp(String wsWsIp) {
        this.wsWsIp = wsWsIp;
    }

    public String getContentInfo() {
        return this.contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
}
