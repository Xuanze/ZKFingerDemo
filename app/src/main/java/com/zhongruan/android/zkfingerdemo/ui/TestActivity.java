package com.zhongruan.android.zkfingerdemo.ui;

import android.app.AlarmManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.config.ABLConfig;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.Ks_ccDao;
import com.zhongruan.android.zkfingerdemo.db.Ks_kcDao;
import com.zhongruan.android.zkfingerdemo.db.entity.Ks_cc;
import com.zhongruan.android.zkfingerdemo.db.entity.Ks_kc;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjl;
import com.zhongruan.android.zkfingerdemo.dialog.HintDialog;
import com.zhongruan.android.zkfingerdemo.dialog.HintDialog2;
import com.zhongruan.android.zkfingerdemo.dialog.IPDialog;
import com.zhongruan.android.zkfingerdemo.socket.SocketClient;
import com.zhongruan.android.zkfingerdemo.utils.ABLSynCallback;
import com.zhongruan.android.zkfingerdemo.utils.DateUtil;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;
import com.zhongruan.android.zkfingerdemo.utils.Utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import rx.android.BuildConfig;

/**
 * Created by Administrator on 2017/8/1.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llNowtime, llLocalip, llNetip, linearlayoutSfcj, linearlayoutCjjl, linearlayoutSfrz, linearlayoutKwdj, linearlayoutSjgl, ll_test_xqkc, ll_test_jcsz;
    private TextView nowtimeTv, nowdayTv, localipTv, tvConnectState, netipTv, upload_app_tv, version_app_tv;
    private ImageView imgConnectState;
    private Handler handler = new Handler();
    private boolean connectedOrNot = false;
    private SocketClient client;
    private Map<String, Object> map;
    private List<Sfrz_rzjg> rzjg1, rzjg2;
    private List<Sfrz_rzjl> rzjl;
    private List<Ks_cc> cc;
    private List<Ks_kc> kc;
    private Handler checkMessageHandler = new Handler() {
        public void handleMessage(Message msg) {
            startCheckMeesageFromKD();
        }
    };
    private boolean receive;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    public void initViews() {
        llNowtime = findViewById(R.id.ll_nowtime);
        nowtimeTv = findViewById(R.id.nowtime_tv);
        nowdayTv = findViewById(R.id.nowday_tv);
        llLocalip = findViewById(R.id.ll_localip);
        localipTv = findViewById(R.id.localip_tv);
        llNetip = findViewById(R.id.ll_netip);
        imgConnectState = findViewById(R.id.img_connect_state);
        tvConnectState = findViewById(R.id.tv_connect_state);
        netipTv = findViewById(R.id.netip_tv);
        upload_app_tv = findViewById(R.id.upload_app_tv);
        linearlayoutSfcj = findViewById(R.id.linearlayout_sfcj);
        linearlayoutCjjl = findViewById(R.id.linearlayout_cjjl);
        linearlayoutSfrz = findViewById(R.id.linearlayout_sfrz);
        linearlayoutKwdj = findViewById(R.id.linearlayout_kwdj);
        linearlayoutSjgl = findViewById(R.id.linearlayout_sjgl);
        ll_test_xqkc = findViewById(R.id.ll_test_xqkc);
        ll_test_jcsz = findViewById(R.id.ll_test_jcsz);
        version_app_tv = findViewById(R.id.version_app_tv);
    }

    Runnable runnable01 = new Runnable() {
        @Override
        public void run() {
            nowtimeTv.setText(DateUtil.getNowTimeNoDate());
            nowdayTv.setText(DateUtil.getDateByFormat("yyyy年MM月dd日"));
            handler.postDelayed(runnable01, 1000);
        }
    };

    Runnable runnable02 = new Runnable() {
        @Override
        public void run() {
            if (ConfigApplication.getApplication().isToSocket()) {
                connectedOrNot = ConfigApplication.getApplication().getKDConnectState();
            }
            localipTv.setText(ConfigApplication.getApplication().getDeviceIP());
            tvConnectState.setText(connectedOrNot ? "已连接校端" : "未连接校端");
            imgConnectState.setBackgroundResource(connectedOrNot ? R.drawable.img_module_tab_footer_base_icon_connect : R.drawable.img_module_tab_footer_base_icon_disconnect);
            handler.postDelayed(runnable02, 5000);
        }
    };

    @Override
    public void initListeners() {
        llNowtime.setOnClickListener(this);
        llLocalip.setOnClickListener(this);
        llNetip.setOnClickListener(this);
        linearlayoutSfcj.setOnClickListener(this);
        linearlayoutCjjl.setOnClickListener(this);
        linearlayoutSfrz.setOnClickListener(this);
        linearlayoutKwdj.setOnClickListener(this);
        linearlayoutSjgl.setOnClickListener(this);
        ll_test_xqkc.setOnClickListener(this);
        ll_test_jcsz.setOnClickListener(this);
        version_app_tv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        netipTv.setText(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ip());
        startCheckMeesageFromKD();
        new Thread(runnable01).start();
        handler.postDelayed(runnable02, 500);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_nowtime:
                startActivity(new Intent(this, TimeActivity.class));
                break;
            case R.id.ll_localip:
                startActivity(new Intent("android.settings.AIRPLANE_MODE_SETTINGS"));
                break;
            case R.id.ll_netip:
                new IPDialog(this, R.style.dialog, new IPDialog.OnEditInputFinishedListener() {
                    @Override
                    public void editInputFinished(Dialog dialog, String ipStr, boolean confirm) {
                        if (confirm) {
                            netipTv.setText(ipStr);
                            DbServices.getInstance(getBaseContext()).saveSbip(ipStr);
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                        }
                    }
                }).setTitle("修改IP地址").setStrip1(StringUtils.substringBefore(netipTv.getText().toString(), ".")).setStrip2(StringUtils.substringBefore(StringUtils.substringAfter(netipTv.getText().toString(), "."), ".")).setStrip3(StringUtils.substringAfterLast(StringUtils.substringBeforeLast(netipTv.getText().toString(), "."), ".")).setStrip4(StringUtils.substringAfterLast(netipTv.getText().toString(), ".")).show();
                break;
            case R.id.linearlayout_sfcj:
                startActivity(new Intent(this, CJActivity.class));
                break;
            case R.id.linearlayout_cjjl:
                startActivity(new Intent(this, CJJLActivity.class));
                break;
            case R.id.linearlayout_sfrz:
                kc = DbServices.getInstance(getBaseContext()).selectKC();
                cc = DbServices.getInstance(getBaseContext()).selectCC();
                LogUtil.i("kc", kc);
                LogUtil.i("cc", cc);
                if (kc.size() == 0) {
                    new HintDialog(this, R.style.dialog, "未发现数据,是否现在导入数据？", new HintDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                dialog.dismiss();
                                startActivity(new Intent(TestActivity.this, DataActivity.class));
                            } else {
                                dialog.dismiss();
                            }
                        }
                    }).setBackgroundResource(R.drawable.img_base_icon_info).setNOVisibility(true).setLLButtonVisibility(true).setTitle("未导入数据").setPositiveButton("确定").show();
                } else if (cc.size() == 0) {
                    Intent intent = new Intent(this, SelectKcCcActivity.class);
                    intent.putExtra("sfrz", "1");
                    startActivity(intent);
                } else {
                    if (!DateUtil.isTime(DateUtil.dateToLong(DateUtil.getNowTime_Millisecond3()), DateUtil.dateToLong(cc.get(0).getCc_kssj()), DateUtil.dateToLong(cc.get(0).getCc_jssj()))) {
                        new HintDialog2(this, R.style.dialog, "当前场次不在当前考试时间", "当前时间：" + DateUtil.getNowTimeChinese(), "所选场次：" + DateUtil.getChineseTime(DateUtil.getStringToDate(cc.get(0).getCc_kssj())) + "-" + DateUtil.getChineseTime(DateUtil.getStringToDate(cc.get(0).getCc_jssj())), new HintDialog2.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm) {
                                    Intent intent = new Intent(TestActivity.this, SelectKcCcActivity.class);
                                    intent.putExtra("sfrz", "1");
                                    startActivity(intent);
                                    dialog.dismiss();
                                } else {
                                    startActivity(new Intent(TestActivity.this, TimeActivity.class));
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("提示").setBackgroundResource(R.drawable.img_base_icon_question).setNegativeButton("重新选择场次").setPositiveButton("修改当前时间").show();
                    } else {
                        startActivity(new Intent(this, RZActivity.class));
                    }
                }
                break;
            case R.id.linearlayout_kwdj:
                kc = DbServices.getInstance(getBaseContext()).selectKC();
                cc = DbServices.getInstance(getBaseContext()).selectCC();
                if (kc.size() == 0) {
                    new HintDialog(this, R.style.dialog, "未发现数据,是否现在导入数据？", new HintDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                dialog.dismiss();
                                startActivity(new Intent(TestActivity.this, DataActivity.class));
                            } else {
                                dialog.dismiss();
                            }
                        }
                    }).setBackgroundResource(R.drawable.img_base_icon_info).setNOVisibility(true).setLLButtonVisibility(true).setTitle("未导入数据").setPositiveButton("确定").show();
                } else if (cc.size() == 0) {
                    Intent intent = new Intent(this, SelectKcCcActivity.class);
                    intent.putExtra("sfrz", "2");
                    startActivity(intent);
                } else {
                    startActivity(new Intent(this, RZJLActivity.class));
                }
                break;
            case R.id.linearlayout_sjgl:
                startActivity(new Intent(this, DataActivity.class));
                break;
            case R.id.ll_test_xqkc:
                kc = DbServices.getInstance(getBaseContext()).selectKC();
                if (kc.size() == 0) {
                    new HintDialog(this, R.style.dialog, "未发现数据,是否现在导入数据？", new HintDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                dialog.dismiss();
                                startActivity(new Intent(TestActivity.this, DataActivity.class));
                            } else {
                                dialog.dismiss();
                            }
                        }
                    }).setBackgroundResource(R.drawable.img_base_icon_info).setNOVisibility(true).setLLButtonVisibility(true).setTitle("未导入数据").setPositiveButton("确定").show();
                } else {
                    new HintDialog(this, R.style.dialog, "是否需要重新选择考场？", new HintDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                String kcmc = DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_name();
                                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("UPDATE " + Ks_kcDao.TABLENAME + " SET  kc_extract = 0");
                                dialog.dismiss();
                                Intent intent = new Intent(TestActivity.this, SelectKcCcActivity.class);
                                intent.putExtra("sfrz", "3");
                                intent.putExtra("kcmc", kcmc);
                                startActivity(intent);
                            } else {
                                dialog.dismiss();
                            }
                        }
                    }).setBackgroundResource(R.drawable.img_base_icon_question).setNOVisibility(true).setLLButtonVisibility(true).setTitle("提示").setPositiveButton("是").setNegativeButton("否").show();
                }
                break;
            case R.id.ll_test_jcsz:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }

    private void startCheckMeesageFromKD() {
        if (MyApplication.getApplication().isShouldStopUploadingData()) {
            checkAgain();
            return;
        }
        if (DbServices.getInstance(TestActivity.this).loadAllrzjg().size() > 0 && DbServices.getInstance(getBaseContext()).selectCC().size() > 0) {
            String ccmc = DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name();
            rzjg2 = DbServices.getInstance(TestActivity.this).selectCCrzjg(ccmc);
            rzjl = DbServices.getInstance(getBaseContext()).selectRZJLSB();
            rzjg1 = DbServices.getInstance(getBaseContext()).selectRZJGSB(DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name());
            LogUtil.i("数据上报：rzjg1", rzjg1.size());
            LogUtil.i("数据上报：rzjg2", rzjg2.size());
            LogUtil.i("数据上报：rzjl", rzjl.size());
            upload_app_tv.setText("上传 " + ((int) (100.0d * ((((double) (rzjg2.size() - rzjg1.size())) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))) + "%（" + (rzjg2.size() - rzjg1.size()) + " / " + rzjg2.size() + "）");
            if (ConfigApplication.getApplication().getKDConnectState() && rzjg1.size() != 0 || rzjl.size() != 0) {
                if (rzjg1.size() != 0) {
                    for (int i = 0; i < rzjg1.size(); i++) {
                        uploadRzjg(rzjg1.get(i));
                    }
                }
                if (rzjl.size() != 0) {
                    for (int i = 0; i < rzjl.size(); i++) {
                        uploadRzjl(rzjl.get(i));
                    }
                }
            }
        } else if (DbServices.getInstance(TestActivity.this).loadAllrzjg().size() == 0) {
            upload_app_tv.setText("暂无数据上传");
        }
        ABLSynCallback.call(new ABLSynCallback.BackgroundCall() {
            public Object callback() {
                String kcno = DbServices.getInstance(getBaseContext()).loadAllkc().size() > 0 ? BuildConfig.VERSION_NAME + DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_no() : BuildConfig.VERSION_NAME;
                LogUtil.i("所选的考场：", kcno);
                client = new SocketClient(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ip());
                Map<String, Object> messages = client.receiveUnLockMessage(TestActivity.this, BuildConfig.VERSION_NAME, kcno);
                LogUtil.i(kcno + " | 获取到考点端消息：" + messages);
                client.closeSocket();
                return messages;
            }
        }, new ABLSynCallback.ForegroundCall() {
            public void callback(Object obj) {
                if (obj != null) {
                    ConfigApplication.getApplication().setKDConnectState(true);
                    Map<String, Object> messages = (Map) obj;
                    String message = BuildConfig.VERSION_NAME;
                    if (messages.get("mess") != null) {
                        message = (String) messages.get("mess");
                    }
                    if (!Utils.stringIsEmpty(message) && message.length() > 1) {
                        Intent intent = new Intent();
                        intent.setAction("mess");
                        intent.putExtra("mess", message);
                        sendBroadcast(intent);
                    }
                    LogUtil.i("messages", messages + " |");
                    if (messages.get("time") != null) {
                        String time = (String) messages.get("time");
                        LogUtil.i("mess", time);
                        timeSynchronization(time);
                    }
                } else {
                    ConfigApplication.getApplication().setKDConnectState(false);
                }
                checkAgain();
            }
        });
    }

    private void checkAgain() {
        this.checkMessageHandler.removeMessages(11111);
        Message msg = new Message();
        msg.what = 11111;
        this.checkMessageHandler.sendMessageDelayed(msg, 10000);
    }

    private void uploadRzjg(final Sfrz_rzjg rzjg) {
        ABLSynCallback.call(new ABLSynCallback.BackgroundCall() {
            public Object callback() {
                new Runnable() {
                    @Override
                    public void run() {
                        SocketClient socketClient = new SocketClient(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ip());
                        map = socketClient.sendString(TestActivity.this, ABLConfig.RZJG, rzjg.toString());
                        LogUtil.i("数据上报：uploadRzjg", map);
                    }
                }.run();
                return map.get("success");
            }
        }, new ABLSynCallback.ForegroundCall() {
            public void callback(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    DbServices.getInstance(TestActivity.this).saveRZJG(rzjg.getRzjg_time());
                }
            }
        });
    }

    private void downApp() {
        ABLSynCallback.call(new ABLSynCallback.BackgroundCall() {
            @Override
            public Object callback() {
                new Runnable() {
                    @Override
                    public void run() {
                        SocketClient socketClient = new SocketClient(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ip());
                        receive = socketClient.receiveUnLockField(TestActivity.this, BuildConfig.VERSION_NAME, ABLConfig.DATAVERSION_APP_FILE, FileUtils.getSDCardPath(), ABLConfig.BUNDLE_KEY_DOWNLOAD_NAME, checkMessageHandler);
                    }
                }.run();
                return receive;
            }
        }, new ABLSynCallback.ForegroundCall() {
            @Override
            public void callback(Object obj) {
                if (((Boolean) obj).booleanValue()) {

                }
            }
        });
    }

    private void uploadRzjl(final Sfrz_rzjl rzjl) {
        ABLSynCallback.call(new ABLSynCallback.BackgroundCall() {
            public Object callback() {
                new Runnable() {
                    @Override
                    public void run() {
                        SocketClient socketClient = new SocketClient(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ip());
                        map = socketClient.sendFile(TestActivity.this, BuildConfig.VERSION_NAME, ABLConfig.RZJL, FileUtils.getAppSavePath() + "/" + rzjl.getRzjl_pith(), rzjl.toString());
                        LogUtil.i("数据上报：uploadRzjl", map);
                    }
                }.run();
                return map.get("success");
            }
        }, new ABLSynCallback.ForegroundCall() {
            public void callback(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    DbServices.getInstance(TestActivity.this).saveRZJL(rzjl.getRzjl_time());
                }
            }
        });
    }

    public boolean timeSynchronization(String time) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).setTime(c.getTimeInMillis());
            return true;
        } catch (Exception e) {
            LogUtil.i("出错了");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        MyApplication.getApplication().setShouldStopUploadingData(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable01);
        handler.removeCallbacks(runnable02);
        checkMessageHandler.removeMessages(11111);
        ConfigApplication.getApplication().setKDConnectState(false);
    }
}
