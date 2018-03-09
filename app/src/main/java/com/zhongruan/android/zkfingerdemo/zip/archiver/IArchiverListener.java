package com.zhongruan.android.zkfingerdemo.zip.archiver;

/**
 * Desc:解压进度接口
 */
public interface IArchiverListener {
    void onStartArchiver();
    void onProgressArchiver(int current, int total);
    void onEndArchiver();
}
