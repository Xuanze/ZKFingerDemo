package com.zhongruan.android.zkfingerdemo.ui;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.HistoryAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.view.HistoryViw;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RZJLActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLlRzjlBack;
    private List<Bk_ks> bkKsList;
    private String ccmc, kcmc;
    private ExpandableListView expandableListView;
    private HistoryAdapter historyAdapter;
    private List<List<HistoryViw>> historyLists;
    private String TAG = "HNZR";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_rzjl);
    }

    @Override
    public void initViews() {
        mLlRzjlBack = findViewById(R.id.ll_rzjl_back);
        expandableListView = findViewById(R.id.eplv_statistic);
        ccmc = DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name();
        kcmc = DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_name();
        bkKsList = DbServices.getInstance(getBaseContext()).queryNOBKKSList(kcmc, ccmc);
        LogUtil.i(TAG, bkKsList.size());
        if (bkKsList.size() != 0) {
            LogUtil.i(TAG, bkKsList.size());
            List<Map> listMap = new ArrayList<>();
            for (int i = 0; i < bkKsList.size(); i++) {
                Map<String, String> map = new HashMap<>();
                map.put(bkKsList.get(i).getKs_kcmc(), bkKsList.get(i).getKs_ccmc());
                listMap.add(map);
            }
            List<String> keyList = new ArrayList<>();
            for (int i = 0; i < bkKsList.size(); i++) {
                keyList.add(bkKsList.get(i).getKs_kcmc());
            }
            Map<String, List<String>> remap = new HashMap<>();
            LogUtil.i(TAG, keyList.size());
            for (String key : keyList) {
                List<String> valueList = new ArrayList<>();
                for (Map m : listMap) {
                    if (m.get(key) != null && !"".equals(m.get(key)) && !valueList.contains(m.get(key))) {
                        valueList.add(m.get(key) + "");
                    }
                    remap.put(key, valueList);
                }
            }
            historyLists = new ArrayList<>();
            for (String a : remap.keySet()) {
                List<HistoryViw> historyViwList = new ArrayList<>();
                List<String> b = remap.get(a);
                for (String c : b) {
                    HistoryViw historyViw = new HistoryViw();
                    historyViw.setCc_mc(c);
                    historyViw.setKc_mc(a);
                    LogUtil.i("lhj", a + "  -------------  " + c);
                    historyViw.setAll_sl(DbServices.getInstance(getBaseContext()).queryBKKSList(a, c).size());
                    historyViw.setRz_sl(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(a, c, "1"));
                    historyViw.setWrz_sl((DbServices.getInstance(getBaseContext()).queryBKKSList(a, c).size() - (DbServices.getInstance(getBaseContext()).queryBkKsIsTG(a, c, "1"))));
                    historyViw.setAll_sb((DbServices.getInstance(getBaseContext()).selectCCrzjg(c).size() - DbServices.getInstance(getBaseContext()).selectRZJGSB(c).size()));
                    historyViw.setYSb_sl(DbServices.getInstance(getBaseContext()).selectCCrzjg(c).size());
                    historyViwList.add(historyViw);
                }
                historyLists.add(historyViwList);
            }
            historyAdapter = new HistoryAdapter(this, kcmc, ccmc, historyLists);
            expandableListView.setAdapter(historyAdapter);
        } else {
            historyAdapter = new HistoryAdapter(this, kcmc, ccmc, null);
            expandableListView.setAdapter(historyAdapter);
        }
    }

    @Override
    public void initListeners() {
        mLlRzjlBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_rzjl_back:
                finish();
                break;
        }
    }
}
