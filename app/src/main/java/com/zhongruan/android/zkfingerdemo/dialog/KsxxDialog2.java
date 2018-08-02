package com.zhongruan.android.zkfingerdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.RzjlAdapter;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;

import java.io.File;

import fr.ganfra.materialspinner.MaterialSpinner;

public class KsxxDialog2 extends Dialog implements View.OnClickListener {
    private ImageView mIvKs;
    private TextView mTvKsName, mTvKsSex, mTvKsSeat, mTvKsKc, mTvKsno, mTvKsSfzh, mTvKsCc, mTvyzjg, mTvyzsj;
    private LinearLayout mTipLayout;
    /**
     * 缺考登记
     */
    private Button mBtQkdjBtg;
    /**
     * 违纪登记
     */
    private Button mBtWjdjBtg;

    private OnCloseListener listener;
    private Bk_ks bk_ks;
    private Context mContext;

    public KsxxDialog2(Context context, int themeResId, Bk_ks bk_ks, KsxxDialog2.OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.bk_ks = bk_ks;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_ksxx2);
        initView();
    }

    public void initView() {
        mIvKs = findViewById(R.id.ivKs);
        mTvKsName = findViewById(R.id.tvKsName);
        mTvKsSex = findViewById(R.id.tvKsSex);
        mTvKsSeat = findViewById(R.id.tvKsSeat);
        mTvKsKc = findViewById(R.id.tvKsKc);
        mTvKsno = findViewById(R.id.tvKsno);
        mTvKsSfzh = findViewById(R.id.tvKsSfzh);
        mTvKsCc = findViewById(R.id.tvKsCc);
        mTvyzjg = findViewById(R.id.tvyzjg);
        mTvyzsj = findViewById(R.id.tvyzsj);
        mTipLayout = findViewById(R.id.tip_layout);
        mBtQkdjBtg = findViewById(R.id.btQkdjBtg);
        mBtQkdjBtg.setOnClickListener(this);
        mBtWjdjBtg = findViewById(R.id.btWjdjBtgs);
        mBtWjdjBtg.setOnClickListener(this);
        Glide.with(mContext).load(new File(FileUtils.getAppSavePath() + "/" + bk_ks.getKs_xp())).into(mIvKs);
        mTvKsSeat.setText(bk_ks.getKs_zwh());
        mTvKsSfzh.setText(bk_ks.getKs_zjno());
        mTvKsKc.setText(bk_ks.getKs_kcmc());
        mTvKsno.setText(bk_ks.getKs_ksno());
        mTvKsName.setText(bk_ks.getKs_xm());
        mTvKsSex.setText(bk_ks.getKs_xb().equals("1") ? "男" : "女");
        if (bk_ks.getIsRZ().equals("2")) {
            mTvyzjg.setText("  现场登记缺考");
            mTvyzsj.setText(bk_ks.getRzTime());
        } else {
            mTvyzjg.setText("无");
            mTvyzsj.setText("无");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btQkdjBtg:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.btWjdjBtgs:
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