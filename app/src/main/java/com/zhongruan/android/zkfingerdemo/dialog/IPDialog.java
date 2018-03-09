package com.zhongruan.android.zkfingerdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;


public class IPDialog extends Dialog implements View.OnClickListener {
    private EditText ip1, ip2, ip3, ip4;
    private String strip1 = "";
    private String strip2 = "";
    private String strip3 = "";
    private String strip4 = "";

    private TextView titleTxt;
    private Button OkButton, NoButton;
    private OnEditInputFinishedListener listener;
    private String title;

    public IPDialog(Context context, int themeResId, OnEditInputFinishedListener listener) {
        super(context, themeResId);
        this.listener = listener;
    }

    public IPDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public IPDialog setStrip1(String ip1) {
        this.strip1 = ip1;
        return this;
    }

    public IPDialog setStrip2(String ip2) {
        this.strip2 = ip2;
        return this;
    }

    public IPDialog setStrip3(String ip3) {
        this.strip3 = ip3;
        return this;
    }

    public IPDialog setStrip4(String ip4) {
        this.strip4 = ip4;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_ip_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
    }

    private void initView() {
        ip1 = findViewById(R.id.ip1);
        ip2 = findViewById(R.id.ip2);
        ip3 = findViewById(R.id.ip3);
        ip4 = findViewById(R.id.ip4);
        titleTxt = findViewById(R.id.dialog_ip_title);
        OkButton = findViewById(R.id.ok_ip_Button);
        OkButton.setOnClickListener(this);
        NoButton = findViewById(R.id.no_ip_Button);
        NoButton.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }

        ip1.setText(strip1);
        ip2.setText(strip2);
        ip3.setText(strip3);
        ip4.setText(strip4);

        ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    OkButton.setEnabled(true);
                } else {
                    OkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    OkButton.setEnabled(true);
                } else {
                    OkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    OkButton.setEnabled(true);
                } else {
                    OkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    OkButton.setEnabled(true);
                } else {
                    OkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.no_ip_Button:
                if (listener != null) {
                    listener.editInputFinished(this, "", false);
                }
                this.dismiss();
                break;
            case R.id.ok_ip_Button:
                if (listener != null) {
                    listener.editInputFinished(this, ip1.getText().toString() + "." + ip2.getText().toString() + "." + ip3.getText().toString() + "." + ip4.getText().toString(), true);
                }
                break;
        }
    }

    public interface OnEditInputFinishedListener {
        void editInputFinished(Dialog dialog, String ipStr, boolean confirm);
    }
}