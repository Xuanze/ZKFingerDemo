package com.zhongruan.android.zkfingerdemo.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;

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
                break;
            case R.id.ll_hyfs:
                break;
            case R.id.ll_finger_fz:
                break;
            case R.id.ll_finger_cfcs:
                break;
            case R.id.ll_face_xsd:
                break;
            case R.id.ll_face_cfcs:
                break;
        }
    }
}
