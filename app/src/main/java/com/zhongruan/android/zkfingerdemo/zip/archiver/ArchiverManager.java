package com.zhongruan.android.zkfingerdemo.zip.archiver;

import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ArchiverManager extends BaseArchiver {
    private volatile static ArchiverManager mInstance;
    private BaseArchiver mCurrentArchiver;
    private Executor mThreadPool;

    public static ArchiverManager getInstance() {
        if (mInstance == null) {
            synchronized (ArchiverManager.class) {
                mInstance = new ArchiverManager();
            }
        }
        return mInstance;
    }

    protected ArchiverManager() {
        mThreadPool = Executors.newSingleThreadExecutor();
    }

    @Override
    public void doArchiver(final String src, final String dest, final boolean isCreateDir, final String passwd, final String FileType, final IArchiverListener listener) {
        mCurrentArchiver = setCurrentArchiver(FileType);
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mCurrentArchiver.doArchiver(src, dest, isCreateDir, passwd, FileType, listener);
            }
        });
    }

    @Override
    public synchronized void doUnArchiver(final String srcfile, final String unrarPath, final String password, final IArchiverListener listener) {
        mCurrentArchiver = getCorrectArchiver(getFileType(srcfile));
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mCurrentArchiver.doUnArchiver(srcfile, unrarPath, password, listener);
            }
        });
    }

    /**
     * 获取文件类型
     *
     * @param filename
     * @return
     */
    private String getFileType(String filename) {
        Log.i(TAG, "getFileType: " + filename);
        String type = null;
        if (TextUtils.isEmpty(filename))
            return type;
        String[] temp = filename.split("\\.");
        type = temp[temp.length - 1];
        return type;
    }

    private BaseArchiver getCorrectArchiver(String type) {
        switch (type) {
            case ArchiverType._ZIP:
                return new ZipArchiver();
            default:
                return null;
        }
    }

    private BaseArchiver setCurrentArchiver(String type) {

        switch (type) {
            case ArchiverType._ZIP:
                return new ZipArchiver();
            default:
                return null;
        }
    }

    public static class ArchiverType {
        public final static String _ZIP = "zip";
    }
}
