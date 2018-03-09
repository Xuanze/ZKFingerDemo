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


public class EditDialog extends Dialog implements View.OnClickListener, TextWatcher {
    private EditText contentTxt;
    private TextView titleTxt;
    private Button OkButton;
    private Button NoButton;
    private String hint;
    private Context mContext;
    private String positiveName;
    private String negativeName;
    private String title;

    private OnEditInputFinishedListener mListener; //接口


    public interface OnEditInputFinishedListener {
        void editInputFinished(Dialog dialog, String password, boolean confirm);
    }

    public EditDialog(Context context, int themeResId, OnEditInputFinishedListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.mListener = listener;
    }

    public EditDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public EditDialog setHint(String hint) {
        this.hint = hint;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_edit_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
    }

    private void initView() {
        contentTxt = findViewById(R.id.et_input);
        titleTxt = findViewById(R.id.dialog_edit_title);
        OkButton = findViewById(R.id.ok_edit_Button);
        OkButton.setOnClickListener(this);
        NoButton = findViewById(R.id.no_edit_Button);
        NoButton.setOnClickListener(this);
        contentTxt.addTextChangedListener(this);
        if (!TextUtils.isEmpty(positiveName)) {
            OkButton.setText(positiveName);
        }
        if (!TextUtils.isEmpty(hint)) {
            contentTxt.setHint(hint);
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
            case R.id.ok_edit_Button:
                if (mListener != null) {
                    String password = contentTxt.getText().toString();
                    mListener.editInputFinished(this, password, true);
                }
                this.dismiss();
                break;
            case R.id.no_edit_Button:
                if (mListener != null) {
                    mListener.editInputFinished(this, "", false);
                }
                break;
        }
    }

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

}