package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;


public class FaceDialog extends Dialog implements View.OnClickListener {
    private TextView tvComfirm, tvCancel;
    private ImageView imageView;
    private OnCloseListener listener;
    private Bitmap bitmap;

    public FaceDialog(Context context, int themeResId, OnCloseListener listener) {
        super(context, themeResId);
        this.listener = listener;
    }

    public FaceDialog setFaceBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_face);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.ivPhoto);
        tvComfirm = findViewById(R.id.tvComfirm);
        tvComfirm.setOnClickListener(this);
        tvCancel = findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(this);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.tvComfirm:
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