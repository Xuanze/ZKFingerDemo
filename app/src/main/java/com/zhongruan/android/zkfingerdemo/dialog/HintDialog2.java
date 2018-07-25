package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;


public class HintDialog2 extends Dialog implements View.OnClickListener {

    private OnCloseListener listener;
    private int amgView;
    private TextView mTitle, mTvContent, mTvContent2, mTvContent3;
    private ImageView mIvTip;
    private Button mNegativeButton, mPositiveButton;
    private String content, content2, content3, title, oneButton, twoButton;

    public HintDialog2(Context context, int themeResId, String content, String content2, String content3, OnCloseListener listener) {
        super(context, themeResId);
        this.content = content;
        this.content2 = content2;
        this.content3 = content3;
        this.listener = listener;
    }

    public HintDialog2 setTitle(String title) {
        this.title = title;
        return this;
    }

    public HintDialog2 setBackgroundResource(int imageView) {
        this.amgView = imageView;
        return this;
    }

    public HintDialog2 setPositiveButton(String oneButton) {
        this.oneButton = oneButton;
        return this;
    }

    public HintDialog2 setNegativeButton(String twoButton) {
        this.twoButton = twoButton;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_hint_layout2);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        initView();
    }

    private void initView() {
        mTitle = findViewById(R.id.title);
        mIvTip = findViewById(R.id.ivTip);
        mTvContent = findViewById(R.id.tvContent);
        mTvContent2 = findViewById(R.id.tvContent2);
        mTvContent3 = findViewById(R.id.tvContent3);
        mNegativeButton = findViewById(R.id.negativeButton);
        mPositiveButton = findViewById(R.id.positiveButton);
        mNegativeButton.setOnClickListener(this);
        mPositiveButton.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }

        if (amgView != 0) {
            mIvTip.setBackgroundResource(amgView);
        }

        if (!TextUtils.isEmpty(content)) {
            mTvContent.setText(content);
        }

        if (!TextUtils.isEmpty(content2)) {
            mTvContent2.setText(content2);
        }

        if (!TextUtils.isEmpty(content3)) {
            mTvContent3.setText(content3);
        }

        if (!TextUtils.isEmpty(oneButton)) {
            mNegativeButton.setText(oneButton);
        }

        if (!TextUtils.isEmpty(twoButton)) {
            mPositiveButton.setText(twoButton);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negativeButton:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                break;
            case R.id.positiveButton:
                if (listener != null) {
                    listener.onClick(this, true);
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}