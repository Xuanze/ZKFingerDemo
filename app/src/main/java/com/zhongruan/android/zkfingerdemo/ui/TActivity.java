package com.zhongruan.android.zkfingerdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.view.NumberInputView;

public class TActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTitle;
    private View mTitleLine;
    private EditText mEtInput;
    /**
     * 港澳台证件及其他点击这里
     */
    private TextView mTvOthers;
    private NumberInputView mNiInput;
    private LinearLayout mLlInput;
    private LinearLayout mContent;
    private View mLine;
    /**
     * 取消
     */
    private Button mNegativeButton;
    /**
     * 确定
     */
    private Button mPositiveButton;
    private LinearLayout mLlBottom;
    private LinearLayout mLlDialog;

    @Override
    public void setContentView() {
        setContentView(R.layout.pad_dialog_base_layout_sfzh);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:OnCreate Method has been created, run FindViewById again to generate code
        setContentView(R.layout.pad_dialog_base_layout_sfzh);
        initView();
    }

    public void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mTitleLine = (View) findViewById(R.id.titleLine);
        mEtInput = (EditText) findViewById(R.id.et_input);
        mTvOthers = (TextView) findViewById(R.id.tv_others);
        mNiInput = (NumberInputView) findViewById(R.id.niv_input);
        mLlInput = (LinearLayout) findViewById(R.id.ll_input);
        mContent = (LinearLayout) findViewById(R.id.content);
        mLine = (View) findViewById(R.id.line);
        mNegativeButton = (Button) findViewById(R.id.negativeButton);
        mNegativeButton.setOnClickListener(this);
        mPositiveButton = (Button) findViewById(R.id.positiveButton);
        mPositiveButton.setOnClickListener(this);
        mLlBottom = (LinearLayout) findViewById(R.id.llBottom);
        mLlDialog = (LinearLayout) findViewById(R.id.llDialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.negativeButton:
                break;
            case R.id.positiveButton:
                break;
        }
    }

}
