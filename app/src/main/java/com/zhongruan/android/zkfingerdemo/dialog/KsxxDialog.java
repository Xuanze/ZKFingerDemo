package com.zhongruan.android.zkfingerdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.RzjlAdapter;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;

import java.io.File;

import fr.ganfra.materialspinner.MaterialSpinner;

public class KsxxDialog extends Dialog implements View.OnClickListener {

    private ImageView mKwdjXp;
    private TextView mKwdjXm, mKwdjZh, mKwdjZjh, mKwdjKsh, mKwdjKc, ks_result;
    private MaterialSpinner bp;
    private Button mNoKsxxButton, mYesKsxxButton;
    private OnCloseListener listener;
    private Bk_ks bk_ks;
    private Context mContext;
    private String[] ITEMS;
    private RecyclerView recyclerView;
    private RzjlAdapter rzjlAdapter;

    public KsxxDialog(Context context, int themeResId, Bk_ks bk_ks, String[] ITEMS, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.bk_ks = bk_ks;
        this.listener = listener;
        this.ITEMS = ITEMS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_ksxx);
        initView();
    }

    private void initView() {
        mNoKsxxButton = findViewById(R.id.no_ksxx_Button);
        mYesKsxxButton = findViewById(R.id.yes_ksxx_Button);
        mKwdjXp = findViewById(R.id.kw_ivKs);
        mKwdjXm = findViewById(R.id.kw_tvKsName);
        mKwdjZh = findViewById(R.id.kw_tvKsSeat);
        ks_result = findViewById(R.id.kw_ks_result);
        mKwdjZjh = findViewById(R.id.kw_tvKsSfzh);
        mKwdjKc = findViewById(R.id.kw_tvKsKc);
        mKwdjKsh = findViewById(R.id.kw_tvKsno);
        recyclerView = findViewById(R.id.myRecycle_listview);
        rzjlAdapter = new RzjlAdapter(mContext, DbServices.getInstance(mContext).selectRZJL(bk_ks.getKs_ksno()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(rzjlAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        bp = findViewById(R.id.spinner);
        bp.setVisibility(View.VISIBLE);

        Picasso.with(mContext).load(new File(FileUtils.getAppSavePath() + "/" + bk_ks.getKs_xp())).into(mKwdjXp);
        mKwdjZh.setText(bk_ks.getKs_zwh());
        mKwdjZjh.setText(bk_ks.getKs_zjno());
        mKwdjKc.setText(bk_ks.getKs_kcmc());
        mKwdjKsh.setText(bk_ks.getKs_ksno());
        mKwdjXm.setText(bk_ks.getKs_xm() + "|" + (bk_ks.getKs_xb().equals("1") ? "男" : "女"));
        ks_result.setText(bk_ks.getIsRZ().equals("1") ? "通过" : "不通过");
        ks_result.setTextColor(bk_ks.getIsRZ().equals("1") ? mContext.getResources().getColor(R.color.green) : mContext.getResources().getColor(R.color.collect_yellow));

        mNoKsxxButton.setOnClickListener(this);
        mYesKsxxButton.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bp.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.no_ksxx_Button:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.yes_ksxx_Button:
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