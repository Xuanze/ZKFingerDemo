package com.zhongruan.android.zkfingerdemo.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;

/**
 * Created by LHJ on 2018/3/9.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLlDataBack;
    /**
     * 基础设置
     */
    private TextView mTitleData;
    private TextView mTvMsChange;
    private LinearLayout mLlMsChange;
    private TextView mTvHyfs;
    private LinearLayout mLlHyfs;
    private TextView mTvFingerFz;
    private LinearLayout mLlFingerFz;
    private TextView mTvFingerCfcs;
    private LinearLayout mLlFingerCfcs;
    private TextView mTvFaceXsd;
    private LinearLayout mLlFaceXsd;
    private TextView mTvFaceCfcs;
    private LinearLayout mLlFaceCfcs;
    private LinearLayout mSettingLl;
    private AlertDialog.Builder builder;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initViews() {
        mLlDataBack = findViewById(R.id.ll_data_back);
        mLlDataBack = findViewById(R.id.ll_data_back);
        mTitleData = findViewById(R.id.titleData);
        mTvMsChange = findViewById(R.id.tv_ms_change);
        mLlMsChange = findViewById(R.id.ll_ms_change);
        mLlMsChange.setOnClickListener(this);
        mTvHyfs = findViewById(R.id.tv_hyfs);
        mLlHyfs = findViewById(R.id.ll_hyfs);
        mLlHyfs.setOnClickListener(this);
        mTvFingerFz = findViewById(R.id.tv_finger_fz);
        mLlFingerFz = findViewById(R.id.ll_finger_fz);
        mLlFingerFz.setOnClickListener(this);
        mTvFingerCfcs = findViewById(R.id.tv_finger_cfcs);
        mLlFingerCfcs = findViewById(R.id.ll_finger_cfcs);
        mLlFingerCfcs.setOnClickListener(this);
        mTvFaceXsd = findViewById(R.id.tv_face_xsd);
        mLlFaceXsd = findViewById(R.id.ll_face_xsd);
        mLlFaceXsd.setOnClickListener(this);
        mTvFaceCfcs = findViewById(R.id.tv_face_cfcs);
        mLlFaceCfcs = findViewById(R.id.ll_face_cfcs);
        mLlFaceCfcs.setOnClickListener(this);
        mSettingLl = findViewById(R.id.setting_ll);
        if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ms()) == 0) {
            mTvMsChange.setText("采集模式");
        } else {
            mTvMsChange.setText("认证模式");
        }
        if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_hyfs()) == 0) {
            mTvHyfs.setText("指纹+拍照");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_hyfs()) == 1) {
            mTvHyfs.setText("指纹+人脸");
        }
        if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_fz()) == 0) {
            mTvFingerFz.setText("低");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_fz()) == 1) {
            mTvFingerFz.setText("中");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_fz()) == 2) {
            mTvFingerFz.setText("高");
        }
        if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_cfcs()) == 0) {
            mTvFingerCfcs.setText("3次");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_cfcs()) == 1) {
            mTvFingerCfcs.setText("6次");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_cfcs()) == 2) {
            mTvFingerCfcs.setText("9次");
        }
        if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_xsd()) == 0) {
            mTvFaceXsd.setText("低");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_xsd()) == 1) {
            mTvFaceXsd.setText("中");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_xsd()) == 2) {
            mTvFaceXsd.setText("高");
        }
        if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_cfcs()) == 0) {
            mTvFaceCfcs.setText("3次");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_cfcs()) == 1) {
            mTvFaceCfcs.setText("6次");
        } else if (Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_cfcs()) == 2) {
            mTvFaceCfcs.setText("9次");
        }
    }

    @Override
    public void initListeners() {
        mLlDataBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_data_back:
                finish();
                break;
            case R.id.ll_ms_change:
                final String[] msArry = new String[]{"采集模式", "认证模式"};
                builder = new AlertDialog.Builder(this);// 自定义对话框
                builder.setSingleChoiceItems(msArry, Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_ms()), new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvMsChange.setText(msArry[which]);
                        DbServices.getInstance(getBaseContext()).saveSbMs(which + "");
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
            case R.id.ll_hyfs:
                final String[] hyArry = new String[]{"指纹+拍照", "指纹+人脸", "身份证+人脸"};
                builder = new AlertDialog.Builder(this);// 自定义对话框
                builder.setSingleChoiceItems(hyArry, Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_hyfs()), new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvHyfs.setText(hyArry[which]);
                        DbServices.getInstance(getBaseContext()).saveSbHyfs(which + "");
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
            case R.id.ll_finger_fz:
                final String[] fzArry = new String[]{"低", "中", "高"};
                builder = new AlertDialog.Builder(this);// 自定义对话框
                builder.setSingleChoiceItems(fzArry, Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_fz()), new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvFingerFz.setText(fzArry[which]);
                        DbServices.getInstance(getBaseContext()).saveSbZwfz(which + "");
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
            case R.id.ll_finger_cfcs:
                final String[] cfcsArry = new String[]{"3", "6", "9"};
                builder = new AlertDialog.Builder(this);// 自定义对话框
                builder.setSingleChoiceItems(cfcsArry, Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_finger_cfcs()), new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvFingerCfcs.setText(cfcsArry[which] + "次");
                        DbServices.getInstance(getBaseContext()).saveSbZwcfcs(which + "");
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
            case R.id.ll_face_xsd:
                final String[] xsdArry = new String[]{"低", "中", "高"};
                builder = new AlertDialog.Builder(this);// 自定义对话框
                builder.setSingleChoiceItems(xsdArry, Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_xsd()), new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvFaceXsd.setText(xsdArry[which]);
                        DbServices.getInstance(getBaseContext()).saveSbFaceXsd(which + "");
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
            case R.id.ll_face_cfcs:
                final String[] cfArry = new String[]{"3", "6", "9"};
                builder = new AlertDialog.Builder(this);// 自定义对话框
                builder.setSingleChoiceItems(cfArry, Integer.parseInt(DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_face_cfcs()), new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvFaceCfcs.setText(cfArry[which] + "次");
                        DbServices.getInstance(getBaseContext()).saveSbFaceCfcs(which + "");
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
        }
    }
}
