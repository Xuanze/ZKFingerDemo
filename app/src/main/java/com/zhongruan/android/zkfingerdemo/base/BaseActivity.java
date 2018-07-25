package com.zhongruan.android.zkfingerdemo.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.dialog.HintDialog;
import com.zhongruan.android.zkfingerdemo.idcardengine.IDCardData;
import com.zhongruan.android.zkfingerdemo.utils.CommonUtil;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;
import com.zhongruan.android.zkfingerdemo.utils.Utils;
import com.zkteco.android.biometric.ZKLiveFaceService;
import com.zkteco.android.biometric.core.device.ParameterHelper;
import com.zkteco.android.biometric.core.device.TransportType;
import com.zkteco.android.biometric.core.utils.LogHelper;
import com.zkteco.android.biometric.module.fingerprint.FingerprintCaptureListener;
import com.zkteco.android.biometric.module.fingerprint.FingerprintFactory;
import com.zkteco.android.biometric.module.fingerprint.FingerprintSensor;
import com.zkteco.android.biometric.module.fingerprint.exception.FingerprintSensorException;
import com.zkteco.android.biometric.module.idcard.IDCardReader;
import com.zkteco.android.biometric.module.idcard.IDCardReaderFactory;
import com.zkteco.android.biometric.module.idcard.exception.IDCardReaderException;
import com.zkteco.android.biometric.module.idcard.meta.IDCardInfo;
import com.zkteco.zkfinger.FingerprintService;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toast mToast;
    private HintDialog hintDialog;
    private boolean isExit;
    private ProgressDialog progressDialog;
    private String message;
    private BaseReceiver baseReceiver;
    private IDCardReader idCardReader = null;
    private static final int VID = 6997;
    private static final int PID = 289;
    private FingerprintSensor fingerprintSensor = null;
    public boolean bstart = false;
    public boolean bopen = false;
    public long context;
    public SoundPool soundPool;
    //定义一个HashMap用于存放音频流的ID
    public HashMap<Integer, Integer> musicId = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        baseReceiver = new BaseReceiver();
        setContentView();
        initViews();
        initListeners();
        initData();
        soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
        //通过load方法加载指定音频流，并将返回的音频ID放入musicId中
        musicId.put(1, soundPool.load(this, R.raw.beep, 1));
    }

    /**
     * 设置布局文件
     */
    public abstract void setContentView();

    /**
     * 初始化布局文件中的控件
     */
    public abstract void initViews();

    /**
     * 初始化控件的监听
     */
    public abstract void initListeners();

    /**
     * 进行数据初始化
     * initData
     */
    public abstract void initData();

    @Override
    protected void onResume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("mess");
        registerReceiver(baseReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(baseReceiver);
        super.onPause();
    }

    /**
     * 获取广播数据
     *
     * @author jiqinlin
     */
    public class BaseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mess = intent.getExtras().getString("mess");
            ShowHintDialog(context, mess.substring(2, mess.length()).toString(), "温馨提示", R.drawable.img_base_check, "知道了", false);
        }
    }

    public void ShowToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    isExit = false;
                    break;
                case 1:
                    hintDialog.dismiss();
                    break;
                case 2:
                    progressDialog.setMessage(message);//载入显示的信息
                    break;
            }
        }
    };

    public void ShowHintDialog(Context context, String hint, String title, int image, String buttonText, boolean isVisibility) {
        new HintDialog(context, R.style.dialog, hint, new HintDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    dialog.dismiss();
                }
            }
        }).setBackgroundResource(image).setNOVisibility(isVisibility).setLLButtonVisibility(true).setTitle(title).setPositiveButton(buttonText).show();
    }

    public void ShowHintDialog(Context context, String hint, String title, int image, int time, boolean isVisibility) {
        hintDialog = new HintDialog(context, R.style.dialog, hint).setBackgroundResource(image).setNOVisibility(isVisibility).setLLButtonVisibility(isVisibility).setTitle(title);
        hintDialog.show();
        mHandler.sendEmptyMessageDelayed(1, time);
    }

    public void showProgressDialog(Context context, String message, boolean cancelable, int time) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(cancelable);//能否够被取消
            progressDialog.setMessage(message);//载入显示的信息
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//圆环风格
        }
        progressDialog.show();
        this.message = message;
        mHandler.sendEmptyMessageDelayed(2, time);
        progressDialog.setMessage(message);//载入显示的信息
    }

    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    public void startIDCardReader() {
        LogHelper.setLevel(7);
        Map idrparams = new HashMap();
        idrparams.put(ParameterHelper.PARAM_KEY_VID, Integer.valueOf(1024));
        idrparams.put(ParameterHelper.PARAM_KEY_PID, Integer.valueOf(50010));
        this.idCardReader = IDCardReaderFactory.createIDCardReader(this, TransportType.USB, idrparams);
    }

    /**
     * 获取设备序列号
     *
     * @return
     */
    public String getSerialNumber() {
        try {
            if (!bopen) {
                OnBnOpen();
            }
            String samid = idCardReader.getSAMID(0);
            LogUtil.i("获取SAM编号成功：" + samid);
            return samid;
        } catch (IDCardReaderException e) {
            LogHelper.d("获取SAM模块失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
            return null;
        }
    }

    /**
     * 打开身份证模块
     */
    public void OnBnOpen() {
        try {
            if (bopen) return;
            idCardReader.open(0);
            bopen = true;
        } catch (IDCardReaderException e) {
            LogHelper.d("连接设备失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    /**
     * 读卡
     *
     * @return
     */
    public IDCardData OnBnRead() {
        try {
            if (bopen) {
                idCardReader.findCard(0);
                idCardReader.selectCard(0);
                IDCardInfo idCardInfo = new IDCardInfo();
                this.idCardReader.readCard(0, 1, idCardInfo);
                if (idCardInfo.getContentLength() > 0) {
                    return CommonUtil.getIDCardData(idCardInfo);
                }
            } else {
                OnBnOpen();
            }
        } catch (IDCardReaderException e) {
            LogUtil.e("读卡失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
            return null;
        }
        return null;
    }

    /**
     * 关闭身份证模块
     */
    public void OnBnClose() {
        try {
            this.idCardReader.close(0);
            LogUtil.i("关闭设备成功");
        } catch (IDCardReaderException e) {
            LogUtil.i("关闭设备失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    /**
     * 初始化指纹模块
     */
    public void OnBnBegin() {
        try {
            if (bstart) {
                LogUtil.i("已启动");
            }
            int limit[] = new int[1];
            if (0 != FingerprintService.init(limit)) {
                LogUtil.i("初始化指纹模块失败");
            }
            fingerprintSensor.open(0);
            fingerprintSensor.setFingerprintCaptureListener(0, (FingerprintCaptureListener) this);
            fingerprintSensor.startCapture(0);
            fingerprintSensor.setFingerprintCaptureMode(1, FingerprintCaptureListener.MODE_CAPTURE_TEMPLATE);
            bstart = true;
            LogUtil.i("开始捕捉成功");
        } catch (FingerprintSensorException e) {
            LogHelper.e(e.getMessage());
            LogUtil.i("开始捕获失败 错误代码=" + e.getErrorCode() + ",内部错误代码: " + e.getInternalErrorCode() + ",message=" + e.getMessage());
        }
    }

    /**
     * 注册指纹模块
     */
    public void startFingerprintSensor() {
        LogHelper.setLevel(Log.ASSERT);
        Map fingerprintParams = new HashMap();
        fingerprintParams.put(ParameterHelper.PARAM_KEY_VID, VID);
        fingerprintParams.put(ParameterHelper.PARAM_KEY_PID, PID);
        fingerprintSensor = FingerprintFactory.createFingerprintSensor(this, TransportType.USB, fingerprintParams);
    }

    /**
     * 停止指纹模块
     */

    public void OnBnStop() {
        try {
            if (bstart) {
                fingerprintSensor.stopCapture(0);
                bstart = false;
                fingerprintSensor.close(0);
                LogUtil.e("停止捕捉成功");
            } else {
                LogUtil.e("已经停止");
            }
        } catch (FingerprintSensorException e) {
            LogUtil.e("停止失败, 错误代码=" + e.getErrorCode() + "\nmessage=" + e.getMessage());
        }
    }

    /**
     * 注册人脸识别模块
     */
    public void setParameter() {
        if (Utils.stringIsEmpty(FileUtils.getName("lic"))) {
            Toast.makeText(getApplicationContext(), "注册文件不存在，请联系技术人员", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FileUtils.getName("lic"));
        byte[] buffer = new byte[8192];
        int len = 0;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            buffer = new byte[inputStream.available()];
            len = inputStream.available();
            inputStream.read(buffer);
        } catch (Exception e) {
        }
        int retCode = 0;
        retCode = ZKLiveFaceService.setParameter(0, 1012, buffer, len);
        if (retCode == 0) {
            getInit();
        } else {
            Toast.makeText(getApplicationContext(), "设置参数失败" + retCode, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化人脸算法
     */
    private void getInit() {
        long[] retContext = new long[1];
        int ret = ZKLiveFaceService.init(retContext);
        if (ret == 0) {
            context = retContext[0];
            String strValue = "528";
            if (ZKLiveFaceService.setParameter(context, 2001, strValue.getBytes(), strValue.length()) == 0) {
            } else {
            }
            strValue = "3";
            if (ZKLiveFaceService.setParameter(context, 1008, strValue.getBytes(), strValue.length()) == 0) {
            } else {
            }
        } else {
            Toast.makeText(this, "初始化算法失败，请检查许可文件", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IDCardReaderFactory.destroy(idCardReader);
        FingerprintFactory.destroy(fingerprintSensor);
    }

    /**
     * 点击两次退出程序
     */
    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            //参数用作状态码；根据惯例，非 0 的状态码表示异常终止。
            System.exit(0);
        }
    }
}
