package com.zhongruan.android.zkfingerdemo.ui;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.zhongruan.android.zkfingerdemo.BuildConfig;
import com.zhongruan.android.zkfingerdemo.db.DaoMaster;
import com.zhongruan.android.zkfingerdemo.db.DaoSession;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.GreenDaoContext;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;


public class MyApplication extends Application {

    private static DaoSession daoSession;
    //获取到主线程的上下文
    private static MyApplication mContext;
    private static DaoMaster daoMaster;
    private boolean shouldStopUploadingData = false;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        this.mContext = this;
        DbServices.getInstance(mContext);
        if (BuildConfig.DEBUG) {
            LogUtil.init(true, Log.VERBOSE);
        } else {
            LogUtil.init(false);
        }
    }

    public static MyApplication getApplication() {
        return mContext;
    }


    /**
     * 取得DaoMaster
     *
     * @param context 上下文
     * @return DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContext(), "aries.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public boolean isShouldStopUploadingData() {
        return this.shouldStopUploadingData;
    }

    public void setShouldStopUploadingData(boolean shouldStopUploadingData) {
        this.shouldStopUploadingData = shouldStopUploadingData;
    }

    /**
     * 取得DaoSession
     *
     * @param context 上下文
     * @return DaoSession
     */
    public static DaoSession getDaoInstant(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
