package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;


public class HintDialog extends Dialog implements View.OnClickListener {
    private TextView contentTxt, titleTxt;
    private Button OkButton, NoButton;
    private LinearLayout ll_button;
    private ImageView imageView;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private int amgView;
    private boolean noVisibility, isVisibility;

    public HintDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.content = content;
    }

    public HintDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.content = content;
        this.listener = listener;
    }

    public HintDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public HintDialog setBackgroundResource(int imageView) {
        this.amgView = imageView;
        return this;
    }

    public HintDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public HintDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    public HintDialog setNOVisibility(boolean noVisibility) {
        this.noVisibility = noVisibility;
        return this;
    }

    public HintDialog setLLButtonVisibility(boolean isVisibility) {
        this.isVisibility = isVisibility;
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
        imageView = findViewById(R.id.iv_select_Tip);
        ll_button = findViewById(R.id.ll_button);
        OkButton = findViewById(R.id.yes_select_Button);
        OkButton.setOnClickListener(this);
        NoButton = findViewById(R.id.no_select_Button);
        NoButton.setOnClickListener(this);
        if (noVisibility) {
            NoButton.setVisibility(View.VISIBLE);
        } else {
            NoButton.setVisibility(View.GONE);
        }
        if (isVisibility) {
            ll_button.setVisibility(View.VISIBLE);
        } else {
            ll_button.setVisibility(View.GONE);
        }
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
        if (amgView != 0) {
            imageView.setBackgroundResource(amgView);
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