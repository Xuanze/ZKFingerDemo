package com.zhongruan.android.zkfingerdemo.ui;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.Sb_settingDao;
import com.zhongruan.android.zkfingerdemo.db.Sfrz_rzfsDao;
import com.zhongruan.android.zkfingerdemo.db.Sfrz_rzztDao;
import com.zhongruan.android.zkfingerdemo.dialog.HintDialog;
import com.zhongruan.android.zkfingerdemo.utils.APPUtil;
import com.zhongruan.android.zkfingerdemo.utils.Utils;


public class WelcomeActivity extends BaseActivity {
    private ImageView loadingImageView;
    private TextView idTvLoadingmsg;
    private AudioManager audioManager; //音频

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initViews() {
        startIDCardReader();
        loadingImageView = findViewById(R.id.loadingImageView);
        idTvLoadingmsg = findViewById(R.id.id_tv_loadingmsg);
        ((AnimationDrawable) loadingImageView.getBackground()).start();
        audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
    }

    @Override
    public void initListeners() {
        if (APPUtil.isShortCutExist(this, getString(R.string.app_name))) {
            return;
        }
        APPUtil.createShortcut(this, getClass(), getString(R.string.app_name), R.drawable.app_icon);
    }

    @Override
    public void initData() {
        if (Build.MODEL.equals(Utils.DEVICETYPE_YLT2)) {
            if (DbServices.getInstance(getBaseContext()).loadAllrzfs().size() == 0 && DbServices.getInstance(getBaseContext()).loadAllrzzt().size() == 0) {
                String sn = getSerialNumber();
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sb_settingDao.TABLENAME + " (settingid,sb_ip,sb_sn,sb_ms,sb_hyfs,sb_finger_fz,sb_finger_cfcs,sb_face_xsd, sb_face_cfcs)   VALUES ('1','192.168.1.1','" + sn + "','0','0','0','0','0','0')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzztDao.TABLENAME + " (rzzt_no,rzzt_name)   VALUES ('11','考中补充拍照')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzztDao.TABLENAME + " (rzzt_no,rzzt_name)   VALUES ('13','考中考务登记')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzztDao.TABLENAME + " (rzzt_no,rzzt_name)   VALUES ('21','现场认证通过')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzztDao.TABLENAME + " (rzzt_no,rzzt_name)   VALUES ('22','现场认证不通过')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzztDao.TABLENAME + " (rzzt_no,rzzt_name)   VALUES ('23','现场认证缺考')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzztDao.TABLENAME + " (rzzt_no,rzzt_name)   VALUES ('24','现场等待认证')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001','卡号')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8002','身份证')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8003','指纹')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8004','声纹')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8005','照片比对')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8006','异常拍照')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8007','补充拍照')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8008','缺考')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8009','替考')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8010','迟到')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8011','延时交卷')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8012','启用备用卷')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8013','违规')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8014','人工审核通过')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8015','人工审核不通过')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001301', '51：携带规定以外的物品进入考场或者未放在指定位置')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001302', '52：未在规定的座位参加考试')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001303', '53：考试开始信号发出前答题或者考试结束信号发出后继续答题')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001304', '54：在考试过程中旁窥、交头接耳')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001305', '55：在考场或者教育考试机构禁止的范围内，喧哗、吸烟或者实施其他影响考场秩序的行为')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '56：未经考务工作人员同意在考试过程中擅自离开考场')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '57：将试卷、答卷（含答题卡、答题纸等）、草稿纸等考试用纸带出考场')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '58：用规定以外的笔或者纸答题或者在试卷规定以外的地方书写姓名、考号或者以其他方式在答卷（含答题卡、答题纸等）上标记信息')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '59：其他违反考场规则但尚未构成作弊的行为')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '61：携带与考试内容相关的文字资料或者存储有与考试内容相关资料的电子设备参加考试')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '62：抄袭或者协助他人抄袭试题答案或者与考试内容相关的资料')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '63：胁迫他人为自己抄袭提供方便')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '64：携带具有发送或者接收信息功能的设备')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '65：由他人冒名代替参加考试')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '66：故意销毁试卷、答卷（含答题卡、答题纸等）或者考试材料')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '67：在答卷（含答题卡、答题纸等）上填写与本人身份不符合的姓名、考号等信息')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '68：传、接物品或者交换试卷、答卷（含答题卡、答题纸等）、草稿纸')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '69：其他以不正当手段获得或者试图获得试题答案、考试成绩的行为')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '71：通过伪造证件。证明、档案及其他材料获得考试资格、加分资格和考试成绩')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '72：评卷过程中被认定答案雷同')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '73：考场纪律混乱、考试秩序失控，出现大面积考试作弊现象')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '74：考试工作人员协助实施作弊行为，事后查实')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '75：其他应认定为作弊的行为')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '76：组织团伙作弊')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '77：向考场外发送、传递试题信息')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '78：使用相关设备接收信息实施作弊')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '79：伪造、变造身份证、准考证及其他证明材料，由他人代替或者代替考生参加考试')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '81：故意扰乱考点、考场、评卷场所等考试工作场所秩序 ')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '82：拒绝、妨碍考务工作人员履行管理职责')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '83：威胁、侮辱、诽谤、诬陷或者以其他方式侵害考试工作人员、其他考生合法权益的行为')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '84：其他扰乱考试管理秩序的行为')");
                MyApplication.getDaoInstant(getBaseContext()).getDatabase().execSQL("INSERT INTO " + Sfrz_rzfsDao.TABLENAME + " (rzfs_no,rzfs_name)   VALUES ('8001306', '85：故意损坏考场设施设备')");
            }
            startActivity(new Intent(WelcomeActivity.this, TestActivity.class));
            finish();
        } else {
            new HintDialog(this, R.style.dialog, "暂未支持该型号，请联系技术服务人员！", new HintDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        dialog.dismiss();
                        finish();
                    }
                }
            }).setBackgroundResource(R.drawable.img_base_icon_error).setNOVisibility(false).setLLButtonVisibility(true).setTitle("提示").setPositiveButton("知道了").show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


