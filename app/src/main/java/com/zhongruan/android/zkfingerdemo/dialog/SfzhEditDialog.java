package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.camera.util.DisplayUtil;
import com.zhongruan.android.zkfingerdemo.view.NumberInputView;


public class SfzhEditDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private OnCloseListener listener;
    private EditText mEtInput;
    /**
     * 港澳台证件及其他点击这里
     */
    private NumberInputView mNiInput;
    /**
     * 取消
     */
    private Button mNegativeButton;
    /**
     * 确定
     */
    private Button mPositiveButton;

    public SfzhEditDialog(Context context, int themeResId, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pad_dialog_base_layout_sfzh, null);
        setContentView(view);
        ViewGroup.LayoutParams localLayoutParams = view.getLayoutParams();
        localLayoutParams.width = (4 * (DisplayUtil.getScreenMetrics(mContext).x / 5));
        view.setLayoutParams(localLayoutParams);
        initView();
    }

    private void initView() {
        mEtInput = findViewById(R.id.et_input);
        mNiInput = findViewById(R.id.niv_input);
        mNegativeButton = findViewById(R.id.negativeButton);
        mNegativeButton.setOnClickListener(this);
        mPositiveButton = findViewById(R.id.positiveButton);
        mPositiveButton.setOnClickListener(this);
        mPositiveButton.setEnabled(false);
        InputFilter[] arrayOfInputFilter = new InputFilter[1];
        arrayOfInputFilter[0] = new InputFilter.LengthFilter(18);
        mEtInput.setFilters(arrayOfInputFilter);
        mNiInput.onTouchListener(new NumberInputView.OnTouchListener() {
            @Override
            public void getString(String str) {
                mEtInput.setText(str);
            }
        });
        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((mEtInput.getText().toString().equals("")) || (mEtInput.getText().toString().length() < 18)) {
                    mPositiveButton.setEnabled(false);
                } else {
                    mPositiveButton.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negativeButton:
                if (listener != null) {
                    listener.onClick(this, false, mEtInput.getText().toString());
                }
                break;
            case R.id.positiveButton:
                if (listener != null) {
                    listener.onClick(this, true, mEtInput.getText().toString());
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm, String Str);
    }
}