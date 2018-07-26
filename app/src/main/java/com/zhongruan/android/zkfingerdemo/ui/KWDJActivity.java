package com.zhongruan.android.zkfingerdemo.ui;


import android.app.Dialog;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.DJSelectKsAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.RZSelectKsAdapter;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.dialog.KsxxDialog2;
import com.zhongruan.android.zkfingerdemo.utils.DateUtil;

import java.util.List;


/**
 * Created by Administrator on 2017/8/11.
 */

public class KWDJActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLlRzjlBack;
    private GridView mGvKs;
    private TextView mTvInputTip;
    private DJSelectKsAdapter setAdapter;
    private List<Bk_ks> bk_ks;
    private Bk_ks bkKs;
    private String SN = DbServices.getInstance(getBaseContext()).loadAllSbSetting().get(0).getSb_sn();
    private String qkid;
    private int position;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_kwdj);
    }

    @Override
    public void initViews() {
        mLlRzjlBack = findViewById(R.id.ll_kwdj_back);
        mGvKs = findViewById(R.id.gvKs);
        mTvInputTip = findViewById(R.id.tv_inputTip);
        mGvKs.setVisibility(View.GONE);
    }

    @Override
    public void initListeners() {
        mLlRzjlBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        showProgressDialog(KWDJActivity.this, "正在加载数据...", false, 100);
        bk_ks = DbServices.getInstance(getBaseContext()).queryBKKSList(DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_name(), DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name());
        mTvInputTip.setText(DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name() + " " + DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_name() + " " + DbServices.getInstance(getBaseContext()).selectCC().get(0).getKm_name());
        setAdapter = new DJSelectKsAdapter(KWDJActivity.this, bk_ks);
        mGvKs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bkKs = bk_ks.get(i);
                position = i;
                new KsxxDialog2(KWDJActivity.this, R.style.dialog, bkKs, new KsxxDialog2.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            String time = DateUtil.getNowTime();
                            DbServices.getInstance(getBaseContext()).saveRzjg("23", bkKs.getKs_ksno(), DbServices.getInstance(getBaseContext()).selectCC().get(0).getKm_no(), DbServices.getInstance(getBaseContext()).loadAllkd().get(0).getKd_no(), bkKs.getKs_kcno(), bkKs.getKs_zwh(), SN, time, "0");
                            qkid = DbServices.getInstance(getBaseContext()).selectRzjgtime(time).toString();
                            DbServices.getInstance(getBaseContext()).saveRzjl("8008", bkKs.getKs_ksno(), DbServices.getInstance(getBaseContext()).selectCC().get(0).getKm_name(), DbServices.getInstance(getBaseContext()).loadAllkd().get(0).getKd_no(), bkKs.getKs_kcno(), bkKs.getKs_zwh(), SN, "0", time, "", "", qkid, "0");
                            DbServices.getInstance(getBaseContext()).saveBkKss(bkKs.getKs_kcno(), bkKs.getKs_ccno(), bkKs.getKs_zjno());
                            updateSingle(position);
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                        }
                    }
                }).show();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGvKs.setAdapter(setAdapter);
                mGvKs.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                    }
                }, 1000);
            }
        }, 500);
    }

    /**
     * 局部更新GridView
     *
     * @param position
     */
    private void updateSingle(int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = mGvKs.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = mGvKs.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = mGvKs.getChildAt(position - firstVisiblePosition);
            LinearLayout linearLayout = view.findViewById(R.id.ll_kslist);
            linearLayout.setBackgroundColor(ContextCompat.getColor(KWDJActivity.this, R.color.button_wjdj));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_kwdj_back:
                finish();
                break;
        }
    }
}
