package com.zhongruan.android.zkfingerdemo.ui;

import android.view.View;
import android.widget.LinearLayout;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;

/**
 * Created by LHJ on 2018/3/9.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLlDataBack;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initViews() {
        mLlDataBack = findViewById(R.id.ll_data_back);
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
        }
    }
}
