package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;


public class SettingDialog2 extends Dialog implements View.OnClickListener {
    private TextView contentTxt;
    private TextView titleTxt;
    private Button OkButton;
    private Button NoButton;

    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;


    public SettingDialog2(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.content = content;
        this.listener = listener;
    }


    public SettingDialog2 setTitle(String title) {
        this.title = title;
        return this;
    }

    public SettingDialog2 setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public SettingDialog2 setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_hint_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
    }

    private void initView() {
        contentTxt = findViewById(R.id.tv_select_Content);
        titleTxt = findViewById(R.id.dialog_select_title);
        OkButton =findViewById(R.id.yes_select_Button);
        OkButton.setOnClickListener(this);
        NoButton = findViewById(R.id.no_select_Button);
        NoButton.setOnClickListener(this);

        contentTxt.setText(content);
        if (!TextUtils.isEmpty(positiveName)) {
            OkButton.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            NoButton.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.no_select_Button:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.yes_select_Button:
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