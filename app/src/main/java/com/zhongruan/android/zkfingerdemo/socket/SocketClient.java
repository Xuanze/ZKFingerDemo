package com.zhongruan.android.zkfingerdemo.socket;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

import com.zhongruan.android.zkfingerdemo.config.ABLConfig;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.ui.ConfigApplication;
import com.zhongruan.android.zkfingerdemo.ui.MyApplication;
import com.zhongruan.android.zkfingerdemo.utils.DateUtil;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;
import com.zhongruan.android.zkfingerdemo.utils.PreferenceUtils;

import net.lingala.zip4j.util.InternalZipConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import rx.android.BuildConfig;

@SuppressLint({"SimpleDateFormat"})
public class SocketClient {

    private static final int LINKTIMEOUT = 10000;
    private static final int SENDTIMEOUT = 30000;
    private static final int RECEIVETIMEOUT = 1200000;
    private String ip;
    private int port;
    private Socket socket;

    public SocketClient(String ipStr) {
        this.socket = null;
        this.ip = ipStr;
        this.port = Integer.parseInt(ConfigApplication.getApplication().getSocketPortStr());
    }

    public Socket getSocket() {
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(this.ip, this.port), LINKTIMEOUT);
            this.socket.setSoTimeout(SENDTIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.socket;
    }

    public boolean closeSocket() {
        try {
            if (this.socket != null && this.socket.isConnected()) {
                this.socket.close();
                this.socket = null;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断终端跟服务器连接状态，接收考点端发送的数据
     *
     * @param sessionId
     * @param contentInfo
     * @return
     */
    public Map<String, Object> receiveUnLockMessage(Context context, String sessionId, String contentInfo) {
        Map<String, Object> map = new HashMap();
        getSocket();
        LogUtil.i(socket.toString());
        if (this.socket != null) {
            if (this.socket.isConnected()) {
                LogUtil.i("receiveUnLockMessage:", socket);
                try {
                    OutputStream outputStream = this.socket.getOutputStream();
                    Date nowTime = new Date(System.currentTimeMillis());
                    String retStrFormatNowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTime);
                    SocketHeadInfo headInfo = new SocketHeadInfo(context);
                    headInfo.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo.socketBean.setSessionID(sessionId);
                    headInfo.socketBean.setContentType(1);
                    headInfo.socketBean.setContentName(106);
                    headInfo.socketBean.setContentInfo(contentInfo);
                    headInfo.socketBean.setDateTime(retStrFormatNowDate);
                    headInfo.socketBean.setContentLength(0);

                    byte[] byteHeadLength = new byte[10];
                    byte[] byteHeadInfo = headInfo.getHeadInfo().getBytes(InternalZipConstants.CHARSET_UTF8);
                    byte[] headSize = String.valueOf(byteHeadInfo.length).getBytes(InternalZipConstants.CHARSET_UTF8);

                    System.arraycopy(headSize, 0, byteHeadLength, 0, headSize.length);
                    outputStream.write(byteHeadLength);
                    outputStream.write(byteHeadInfo);
                    outputStream.flush();

                    InputStream inputStream = this.socket.getInputStream();

                    byte[] byteHeadInfo2 = readBytes(inputStream, Integer.parseInt(new String(readBytes(inputStream, 10)).trim()));

                    SocketHeadInfo headInfo2 = new SocketHeadInfo(context);
                    headInfo2.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo2.setHeadInfo(byteHeadInfo2);
                    int version = headInfo2.socketBean.getVersion();
                    map.put("version", Integer.valueOf(version));
                    String time = headInfo2.socketBean.getDateTime();
                    map.put("time", time);
                    map.put("mess", headInfo2.socketBean.getContentInfo());
                    closeSocket();
                    return map;
                } catch (Exception e) {
                    e.printStackTrace();
                    closeSocket();
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 网络下载考生认证数据包
     *
     * @param sessionId
     * @param contentName
     * @param filepath
     * @param handler
     * @return
     */
    public int receiveUnLockField(Context context, String sessionId, int contentName, String filepath, Handler handler) {
        FileUtils.newFolder(filepath);
        getSocket();
        if (this.socket != null) {
            if (this.socket.isConnected()) {
                LogUtil.i("receiveUnLockField:", socket);
                try {
                    this.socket.setSoTimeout(RECEIVETIMEOUT);
                    OutputStream outputStream = this.socket.getOutputStream();
                    byte[] byteHeadLength = new byte[10];
                    Date date = new Date(System.currentTimeMillis());
                    String retStrFormatNowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    SocketHeadInfo headInfo = new SocketHeadInfo(context);
                    headInfo.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo.socketBean.setSessionID(sessionId);
                    headInfo.socketBean.setContentType(1);
                    headInfo.socketBean.setContentName(contentName);
                    headInfo.socketBean.setDateTime(retStrFormatNowDate);

                    byte[] byteHeadInfo = headInfo.getHeadInfo().getBytes(InternalZipConstants.CHARSET_UTF8);
                    byte[] headSize = String.valueOf(byteHeadInfo.length).getBytes(InternalZipConstants.CHARSET_UTF8);

                    System.arraycopy(headSize, 0, byteHeadLength, 0, headSize.length);

                    byte[] byteContemt = BuildConfig.VERSION_NAME.getBytes(InternalZipConstants.CHARSET_UTF8);
                    outputStream.write(byteHeadLength);
                    outputStream.write(byteHeadInfo);
                    outputStream.write(byteContemt);
                    outputStream.flush();
                    InputStream inputStream = this.socket.getInputStream();
                    byte[] headInfo1 = readBytes(inputStream, Integer.parseInt(new String(readBytes(inputStream, 10), "UTF-8").trim()));
                    SocketHeadInfo headInfo2 = new SocketHeadInfo(context);
                    headInfo2.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo2.setHeadInfo(headInfo1);
                    int fileLength = headInfo2.socketBean.getContentLength();
                    String contentInfo = headInfo2.socketBean.getContentInfo();
                    LogUtil.i("fileLength", fileLength + " |");
                    LogUtil.i("headInfo2", headInfo2.getHeadInfo() + " |");
                    if (fileLength > 0) {
                        LogUtil.i("fileLength > 0", "有数据的");
                        if (headInfo2.socketBean.getContentName() == 105) {
                            String pwd = new String(readBytes(inputStream, 100), "UTF-8").trim();
                            PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.SOCKET_ZIPPW, pwd);
                            fileLength -= 100;
                        }
                        File file = new File(filepath + contentInfo);
                        if (file.exists()) {
                            file.delete();
                        }
                        OutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[InternalZipConstants.BUFF_SIZE];
                        int totlen = 0;
                        while (totlen < fileLength) {
                            LogUtil.i("==准备读取==", " |");
                            int n = inputStream.read(buffer);
                            LogUtil.i("==正在读取==", n + " |");
                            fileOutputStream.write(buffer, 0, n);
                            totlen += n;
                            if (handler != null) {
                                double d = (double) fileLength;
                                int percent = (int) (((((double) totlen) * 1.0d) / fileLength) * 100.0d);

                                Message message1 = new Message();
                                message1.what = 16;
                                message1.arg1 = percent;
                                handler.sendMessage(message1);
                            }
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        LogUtil.i("==读取完毕==", "===");
                    } else {
                        LogUtil.i("fileLength == 0", "没有数据的");
                    }
                    closeSocket();
                    if (fileLength > 0) {
                        return 1;
                    }
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    closeSocket();
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * 网络上报认证记录和照片
     *
     * @param sessionId
     * @param contentName
     * @param filepath
     * @param contentInfo
     * @return
     */

    public Map<String, Object> sendFile(Context context, String sessionId, int contentName, String filepath, String contentInfo) {
        LogUtil.i("解锁sendFile");
        String msg = (BuildConfig.VERSION_NAME + DateUtil.getNowTime_Millisecond() + ABLConfig.BMD_BZ_SPLIT) + "contentName:" + contentName + ABLConfig.BMD_BZ_SPLIT;
        if (contentName == ABLConfig.RZJL) {
            msg = msg + "认证记录;";
        } else if (contentName == ABLConfig.RZJG) {
            msg = msg + "认证结果;";
        }
        Map<String, Object> map = new HashMap();
        map.put("success", Boolean.valueOf(false));
        map.put("value", BuildConfig.VERSION_NAME);
        getSocket();
        LogUtil.i("socket=" + this.socket);
        if (this.socket != null) {
            LogUtil.i("socket.isConnected()=" + this.socket.isConnected());
        }
        if (this.socket != null) {
            if (this.socket.isConnected()) {
                try {
                    OutputStream outputStream = this.socket.getOutputStream();
                    byte[] byteFile = FileUtils.getBytes(filepath);
                    LogUtil.i("照片：byteFile", filepath + " | " + byteFile + " | " + (byteFile != null ? byteFile.length : 0));
                    String retStrFormatNowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                    SocketHeadInfo headInfo = new SocketHeadInfo(context);
                    headInfo.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo.socketBean.setSessionID(sessionId);
                    headInfo.socketBean.setContentType(2);
                    headInfo.socketBean.setContentName(contentName);
                    headInfo.socketBean.setContentInfo(contentInfo);
                    headInfo.socketBean.setDateTime(retStrFormatNowDate);
                    msg = msg + "上传字符串:" + contentInfo + ABLConfig.BMD_BZ_SPLIT;
                    if (byteFile == null) {
                        headInfo.socketBean.setContentLength(0);
                    } else {
                        headInfo.socketBean.setContentLength(byteFile.length);
                    }
                    LogUtil.i("发送sendFile head", BuildConfig.VERSION_NAME + headInfo.getHeadInfo());
                    byte[] byteHeadLength = new byte[10];
                    byte[] byteHeadInfo = headInfo.getHeadInfo().getBytes(InternalZipConstants.CHARSET_UTF8);
                    byte[] headSize = String.valueOf(byteHeadInfo.length).getBytes(InternalZipConstants.CHARSET_UTF8);
                    System.arraycopy(headSize, 0, byteHeadLength, 0, headSize.length);
                    outputStream.write(byteHeadLength);
                    outputStream.write(byteHeadInfo);
                    if (byteFile != null) {
                        outputStream.write(byteFile);
                    }
                    outputStream.flush();
                    InputStream inputStream = this.socket.getInputStream();
                    byte[] byteHeadInfo2 = readBytes(inputStream, Integer.parseInt(new String(readBytes(inputStream, 10)).trim()));
                    inputStream.read(byteHeadInfo2);
                    SocketHeadInfo headInfo2 = new SocketHeadInfo(context);
                    headInfo2.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo2.setHeadInfo(byteHeadInfo2);
                    LogUtil.i("接收到sendFile head", BuildConfig.VERSION_NAME + headInfo2.getHeadInfo());
                    int success = headInfo2.socketBean.getResultInfo();
                    msg = msg + "接收处理结果:" + success + ABLConfig.BMD_BZ_SPLIT;
                    if (success == 1) {
                        map.put("success", Boolean.valueOf(true));
                    }
                    map.put("value", headInfo2.socketBean.getContentInfo());
                    closeSocket();
                } catch (Exception e) {
                    map.put("success", Boolean.valueOf(false));
                    msg = msg + (new StringBuilder().append("处理异常:").append(e.getMessage()).toString() == null ? BuildConfig.VERSION_NAME : e.getMessage() + ABLConfig.BMD_BZ_SPLIT);
                    e.printStackTrace();
                } finally {
                    LogUtil.i(msg);
                    closeSocket();
                }
            }
        }
        return map;
    }

    /**
     * 上报认证结果记录
     *
     * @param contentName
     * @param contentInfo
     * @return
     */

    public Map<String, Object> sendString(Context context, int contentName, String contentInfo) {
        String msg = BuildConfig.VERSION_NAME + DateUtil.getNowTime_Millisecond() + ABLConfig.BMD_BZ_SPLIT;
        msg = msg + "contentName:" + contentName + ABLConfig.BMD_BZ_SPLIT;
        if (contentName == ABLConfig.RZJG) {
            msg = msg + "认证结果;";
        }
        Map<String, Object> map = new HashMap();
        map.put("success", Boolean.valueOf(false));
        map.put("value", BuildConfig.VERSION_NAME);
        getSocket();
        LogUtil.i("socket=" + this.socket);
        if (this.socket != null) {
            LogUtil.i("socket.isConnected()=" + this.socket.isConnected());
        }
        if (this.socket != null) {
            if (this.socket.isConnected()) {
                try {
                    OutputStream outputStream = this.socket.getOutputStream();
                    Date date = new Date(System.currentTimeMillis());
                    String retStrFormatNowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    SocketHeadInfo headInfo = new SocketHeadInfo(context);
                    headInfo.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo.socketBean.setSessionID(BuildConfig.VERSION_NAME);
                    headInfo.socketBean.setContentType(1);
                    headInfo.socketBean.setContentName(contentName);
                    headInfo.socketBean.setContentInfo(contentInfo);
                    headInfo.socketBean.setDateTime(retStrFormatNowDate);
                    headInfo.socketBean.setContentLength(0);
                    msg = msg + "上传字符串:" + contentInfo + ABLConfig.BMD_BZ_SPLIT;
                    String headInfoStr = headInfo.getHeadInfo();
                    LogUtil.i("headInfoStr", headInfoStr + " |");
                    byte[] byteHeadLength = new byte[10];
                    byte[] byteHeadInfo = headInfo.getHeadInfo().getBytes(InternalZipConstants.CHARSET_UTF8);
                    byte[] headSize = String.valueOf(byteHeadInfo.length).getBytes(InternalZipConstants.CHARSET_UTF8);
                    System.arraycopy(headSize, 0, byteHeadLength, 0, headSize.length);
                    outputStream.write(byteHeadLength);
                    outputStream.write(byteHeadInfo);
                    outputStream.flush();
                    int length = byteHeadInfo.length;
                    LogUtil.i("byteHeadInfo", length + " | " + headSize.length + " | " + byteHeadInfo);
                    InputStream inputStream = this.socket.getInputStream();
                    byte[] byteHeadInfo2 = readBytes(inputStream, Integer.parseInt(new String(readBytes(inputStream, 10)).trim()));
                    SocketHeadInfo headInfo2 = new SocketHeadInfo(context);
                    headInfo2.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo2.setHeadInfo(byteHeadInfo2);
                    LogUtil.i("接收到sendString head", BuildConfig.VERSION_NAME + headInfo2.getHeadInfo());
                    int success = headInfo2.socketBean.getResultInfo();
                    msg = msg + "接收处理结果:" + success + ABLConfig.BMD_BZ_SPLIT;
                    if (success == 1) {
                        map.put("success", Boolean.valueOf(true));
                    }
                    map.put("value", headInfo2.socketBean.getContentInfo());
                    closeSocket();
                } catch (Exception e) {
                    String str;
                    map.put("success", Boolean.valueOf(false));
                    StringBuilder append = new StringBuilder().append(msg);
                    if (("处理异常:" + e.getMessage()) == null) {
                        str = BuildConfig.VERSION_NAME;
                    } else {
                        str = e.getMessage() + ABLConfig.BMD_BZ_SPLIT;
                    }
                    msg = append.append(str).toString();
                    e.printStackTrace();
                } finally {
                    LogUtil.i(msg);
                    closeSocket();
                }
            }
        }
        return map;
    }

    /**
     * 更新app
     * receiveUnLockField(BuildConfig.VERSION_NAME, this.downloadType ? ABLConfig.DATAVERSION_APP_FILE : ABLConfig.DATAVERSION_ENGINE_FILE, FileUtils.getSDCardPath(), this.BUNDLE_KEY_DOWNLOAD_NAME, this.handler);
     *
     * @param sessionId
     * @param contentName
     * @param filepath
     * @param contentInfoStr
     * @param handler
     * @return
     */
    public boolean receiveUnLockField(Context context, String sessionId, int contentName, String filepath, String contentInfoStr, Handler handler) {
        FileUtils.newFolder(filepath);
        getSocket();
        if (this.socket != null) {
            if (this.socket.isConnected()) {
                try {
                    this.socket.setSoTimeout(RECEIVETIMEOUT);
                    OutputStream outputStream = this.socket.getOutputStream();
                    byte[] byteHeadLength = new byte[10];
                    LogUtil.i("receiveField", "byteHeadLength");
                    Date date = new Date(System.currentTimeMillis());
                    String retStrFormatNowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    SocketHeadInfo headInfo = new SocketHeadInfo(context);
                    headInfo.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo.socketBean.setSessionID(sessionId);
                    headInfo.socketBean.setContentType(1);
                    headInfo.socketBean.setContentName(contentName);
                    headInfo.socketBean.setContentInfo(contentInfoStr);
                    headInfo.socketBean.setDateTime(retStrFormatNowDate);
                    byte[] byteHeadInfo = headInfo.getHeadInfo().getBytes(InternalZipConstants.CHARSET_UTF8);
                    byte[] headSize = String.valueOf(byteHeadInfo.length).getBytes(InternalZipConstants.CHARSET_UTF8);
                    System.arraycopy(headSize, 0, byteHeadLength, 0, headSize.length);
                    byte[] byteContemt = BuildConfig.VERSION_NAME.getBytes(InternalZipConstants.CHARSET_UTF8);
                    outputStream.write(byteHeadLength);
                    outputStream.write(byteHeadInfo);
                    outputStream.write(byteContemt);
                    outputStream.flush();
                    InputStream inputStream = this.socket.getInputStream();
                    byte[] headInfo1 = readBytes(inputStream, Integer.parseInt(new String(readBytes(inputStream, 10), "UTF-8").trim()));
                    SocketHeadInfo headInfo2 = new SocketHeadInfo(context);
                    headInfo2.socketBean.setWsWsNo(DbServices.getInstance(context).loadAllSbSetting().get(0).getSb_sn());
                    headInfo2.setHeadInfo(headInfo1);
                    int fileLength = headInfo2.socketBean.getContentLength();
                    String contentInfo = headInfo2.socketBean.getContentInfo();
                    LogUtil.i("fileLength", fileLength + " |");
                    if (fileLength > 0) {
                        int percent;
                        Message msg;
                        if (headInfo2.socketBean.getContentName() == 105) {
                            String pwd = new String(readBytes(inputStream, 100), "UTF-8").trim();
                            PreferenceUtils.getInstance(MyApplication.getApplication()).setPrefString(ABLConfig.SOCKET_ZIPPW, pwd);
                            fileLength -= 100;
                        }
                        File file = new File(filepath + contentInfo);
                        if (file.exists()) {
                            file.delete();
                        }
                        long startTime = System.currentTimeMillis();
                        OutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
                        int totlen = 0;
                        while (totlen < fileLength) {
                            Thread.sleep(20);
                            int n = inputStream.read(buffer);
                            fileOutputStream.write(buffer, 0, n);
                            totlen += n;
                            LogUtil.i("totlen", totlen + " | " + n + " | " + fileLength);
                            if (handler != null) {
                                percent = totlen;
                                msg = new Message();
                                msg.what = percent;
                                msg.obj = Integer.valueOf(fileLength);
                                msg.arg1 = 0;
                                handler.sendMessage(msg);
                            }
                        }
                        long endTime = System.currentTimeMillis();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        if (totlen <= 0 || fileLength == 0) {
                            if (handler != null) {
                                percent = totlen;
                                msg = new Message();
                                msg.what = 0;
                                msg.obj = Integer.valueOf(0);
                                msg.arg1 = 2;
                                handler.sendMessage(msg);
                            }
                        } else if (handler != null) {
                            percent = totlen;
                            msg = new Message();
                            msg.what = fileLength;
                            msg.obj = Integer.valueOf(fileLength);
                            msg.arg1 = 1;
                            handler.sendMessage(msg);
                        }
                    }
                    closeSocket();
                    if (fileLength > 0) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    closeSocket();
                    return false;
                }
            }
        }
        return false;
    }

    private byte[] readBytes(InputStream in, int length) throws Exception {
        byte[] bytes = new byte[length];
        int total = 0;
        while (total < length) {
            byte[] temp;
            if (length - total > 1024) {
                temp = new byte[1024];
            } else {
                temp = new byte[(length - total)];
            }
            int n = in.read(temp);
            System.arraycopy(temp, 0, bytes, total, n);
            total += n;
        }
        return bytes;
    }
}
