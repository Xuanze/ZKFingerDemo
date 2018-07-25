package com.zhongruan.android.zkfingerdemo.zip.archiver;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/8/25.
 */
public abstract class BaseArchiver {
    protected String TAG = this.getClass().getSimpleName();
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    /**
     * 使用给定密码压缩指定文件或文件夹到指定位置.
     * <p>
     * dest可传最终压缩文件存放的绝对路径,也可以传存放目录,也可以传null或者"".<br />
     * 如果传null或者""则将压缩文件存放在当前目录,即跟源文件同目录,压缩文件名取源文件名,以.zip为后缀;<br />
     * 如果以路径分隔符(File.separator)结尾,则视为目录,压缩文件名取源文件名,以.zip为后缀,否则视为文件名.
     *
     * @param src         要压缩的文件或文件夹路径
     * @param dest        压缩文件存放路径
     * @param isCreateDir 是否在压缩文件里创建目录,仅在压缩文件为目录时有效.<br />
     *                    如果为false,将直接压缩目录下文件到压缩文件.
     * @param passwd      压缩使用的密码
     * @return 最终的压缩文件存放的绝对路径, 如果为null则说明压缩失败.
     */
    public abstract void doArchiver(String src, String dest, boolean isCreateDir, String passwd, String FileType, IArchiverListener listener);

    /**
     * 解压文件
     *
     * @param srcfile   文件名
     * @param unrarPath 文件解压存放路径
     * @param password  文件解压密码
     * @param listener  文件解压进度
     */
    public abstract void doUnArchiver(String srcfile, String unrarPath, String password, IArchiverListener listener);
}
