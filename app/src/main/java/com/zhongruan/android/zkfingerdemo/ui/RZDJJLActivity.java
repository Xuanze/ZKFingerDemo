package com.zhongruan.android.zkfingerdemo.ui;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.RZDJJLHistoryAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.view.RzdjjlHistoryViw;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;
import com.zhongruan.android.zkfingerdemo.view.UploadProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RZDJJLActivity extends BaseActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener, View.OnTouchListener {
    private LinearLayout mLlRzjlBack;
    /**
     * 认证记录
     */
    private TextView mListItemPiechartCc;
    private TextView mListItemPiechartKc;
    /**
     * 0
     */
    private TextView mListItemPiechartTotal;
    /**
     * 0
     */
    private TextView mListItemPiechartYyz;
    /**
     * 0
     */
    private TextView mListItemPiechartWyz;
    private TextView mListItemPiechartYsc;
    private TextView mListItemPiechartSczrs;
    private UploadProgressBar mListItemPiechartPb;
    private LinearLayout mListItemUploadingLl;
    private LinearLayout mListItemUploadedLl;
    private LinearLayout mListItemNouploadLl;
    private PieChart mListItemPiechart;
    private LinearLayout mListItemStatisticHistoryItem;
    private ExpandableListView mEplvStatistic;
    /**
     * 0
     */
    private TextView mListItemPiechartQk;
    private List<Bk_ks> bk_ks, bkKsLs;
    private String ccmc, kcmc;
    private List<Sfrz_rzjg> rzjg1, rzjg2;
    private String TAG = "HNZR";
    private RZDJJLHistoryAdapter rzdjjlHistoryAdapter;
    private List<List<RzdjjlHistoryViw>> historyLists;
    // ExpandListView 列表状态 1展开 0关闭 该案例中设置为三级
    private int[] isExpand = new int[]{0, 0, 0};
    private boolean isHD = false;
    private float x, y;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_rzkwdjjl);
    }

    @Override
    public void initViews() {
        showProgressDialog(RZDJJLActivity.this, "正在加载数据...", false, 100);
        mLlRzjlBack = findViewById(R.id.ll_rzjl_back);
        mListItemPiechartCc = findViewById(R.id.list_item_piechart_cc);
        mListItemPiechartKc = findViewById(R.id.list_item_piechart_kc);
        mListItemPiechartTotal = findViewById(R.id.list_item_piechart_total);
        mListItemPiechartYyz = findViewById(R.id.list_item_piechart_yyz);
        mListItemPiechartYyz = findViewById(R.id.list_item_piechart_yyz);
        mListItemPiechartWyz = findViewById(R.id.list_item_piechart_wyz);
        mListItemPiechartYsc = findViewById(R.id.list_item_piechart_ysc);
        mListItemPiechartQk = findViewById(R.id.list_item_piechart_qk);
        mListItemPiechartSczrs = findViewById(R.id.list_item_piechart_sczrs);
        mListItemPiechartPb = findViewById(R.id.list_item_piechart_pb);
        mListItemUploadingLl = findViewById(R.id.list_item_uploading_ll);
        mListItemUploadedLl = findViewById(R.id.list_item_uploaded_ll);
        mListItemNouploadLl = findViewById(R.id.list_item_noupload_ll);
        mListItemPiechart = findViewById(R.id.list_item_piechart);
        mListItemStatisticHistoryItem = findViewById(R.id.list_item_statistic_history_item);
        mEplvStatistic = findViewById(R.id.eplv_statistic);
        initPiechart(mListItemPiechart);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initListeners() {
        mLlRzjlBack.setOnClickListener(this);
        mEplvStatistic.setOnChildClickListener(this);
        mListItemStatisticHistoryItem.setOnTouchListener(this);
        mListItemStatisticHistoryItem.setOnClickListener(this);
    }

    @Override
    public void initData() {
        ccmc = DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name();
        kcmc = DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_name();
        bk_ks = DbServices.getInstance(getBaseContext()).queryBKKSList(kcmc, ccmc);
        mListItemPiechartCc.setText(DbServices.getInstance(getBaseContext()).selectCC().get(0).getCc_name());
        mListItemPiechartKc.setText(DbServices.getInstance(getBaseContext()).selectKC().get(0).getKc_name() + " " + DbServices.getInstance(getBaseContext()).selectCC().get(0).getKm_name());
        mListItemPiechartTotal.setText(bk_ks.size() + "");
        mListItemPiechartYyz.setText(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(kcmc, ccmc, "1") + "");
        mListItemPiechartQk.setText(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(kcmc, ccmc, "2") + "");
        mListItemPiechartWyz.setText(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(kcmc, ccmc, "0") + "");
        rzjg1 = DbServices.getInstance(getBaseContext()).selectWSBrzjg(kcmc, ccmc, "1");
        rzjg2 = DbServices.getInstance(getBaseContext()).selectKCCCrzjg(kcmc, ccmc);
        if (rzjg2.size() != 0) {
            if (((int) (100.0d * ((((double) rzjg1.size()) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))) == 100) {
                mListItemUploadingLl.setVisibility(View.GONE);
                mListItemNouploadLl.setVisibility(View.GONE);
                mListItemUploadedLl.setVisibility(View.VISIBLE);
            } else {
                mListItemUploadingLl.setVisibility(View.VISIBLE);
                mListItemNouploadLl.setVisibility(View.GONE);
                mListItemUploadedLl.setVisibility(View.GONE);
                mListItemPiechartYsc.setText(rzjg1.size() + "");
                mListItemPiechartSczrs.setText(rzjg2.size() + "");
                mListItemPiechartPb.setProgress(((int) (100.0d * ((((double) (rzjg1.size())) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))));
            }
        } else {
            mListItemUploadedLl.setVisibility(View.GONE);
            mListItemUploadingLl.setVisibility(View.GONE);
            mListItemNouploadLl.setVisibility(View.VISIBLE);
        }
        mListItemPiechart.setData(setPiechartData(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(kcmc, ccmc, "1"), DbServices.getInstance(getBaseContext()).queryBkKsIsTG(kcmc, ccmc, "2"), bk_ks.size()));
        mListItemPiechart.invalidate();
        bkKsLs = DbServices.getInstance(getBaseContext()).queryNOBKKSList(kcmc, ccmc);
        if (bkKsLs.size() != 0) {
            LogUtil.i(TAG, bkKsLs.size());
            List<Map> listMap = new ArrayList<>();
            for (int i = 0; i < bkKsLs.size(); i++) {
                Map<String, String> map = new HashMap<>();
                map.put(bkKsLs.get(i).getKs_kcmc(), bkKsLs.get(i).getKs_ccmc());
                listMap.add(map);
            }
            List<String> keyList = new ArrayList<>();
            for (int i = 0; i < bkKsLs.size(); i++) {
                keyList.add(bkKsLs.get(i).getKs_kcmc());
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
            List<RzdjjlHistoryViw> rzdjjlHistoryViws = new ArrayList<>();
            for (String a : remap.keySet()) {
                List<String> b = remap.get(a);
                for (String c : b) {
                    RzdjjlHistoryViw rzdjjlHistoryViw = new RzdjjlHistoryViw();
                    rzdjjlHistoryViw.setCc_mc(c);
                    rzdjjlHistoryViw.setKc_mc(a);
                    LogUtil.i("lhj", a + "  -------------  " + c);
                    rzdjjlHistoryViw.setAll_sl(DbServices.getInstance(getBaseContext()).queryBKKSList(a, c).size());
                    rzdjjlHistoryViw.setRz_sl(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(a, c, "1"));
                    rzdjjlHistoryViw.setQk_sl(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(a, c, "2"));
                    rzdjjlHistoryViw.setWrz_sl(DbServices.getInstance(getBaseContext()).queryBkKsIsTG(a, c, "0"));
                    rzdjjlHistoryViw.setAll_sb(DbServices.getInstance(getBaseContext()).selectKCCCrzjg(a, c).size());
                    rzdjjlHistoryViw.setYSb_sl(DbServices.getInstance(getBaseContext()).selectWSBrzjg(a, c, "1").size());
                    rzdjjlHistoryViws.add(rzdjjlHistoryViw);
                }
            }
            historyLists.add(rzdjjlHistoryViws);
            rzdjjlHistoryAdapter = new RZDJJLHistoryAdapter(getBaseContext(), historyLists);
            mEplvStatistic.setAdapter(rzdjjlHistoryAdapter);
            // 通过监听展开和关闭事件动态设置高度
            mEplvStatistic.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    isExpand[groupPosition] = 1;
                    setListHeight(mEplvStatistic, rzdjjlHistoryAdapter);
                }
            });

            mEplvStatistic.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {
                    isExpand[groupPosition] = 0;
                    setListHeight(mEplvStatistic, rzdjjlHistoryAdapter);
                }
            });
        } else {
            rzdjjlHistoryAdapter = new RZDJJLHistoryAdapter(getBaseContext(), null);
            mEplvStatistic.setAdapter(rzdjjlHistoryAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_item_statistic_history_item:
                Intent intent = new Intent(RZDJJLActivity.this, RZXQActivity.class);
                intent.putExtra("rzjlkc", kcmc);
                intent.putExtra("rzjlcc", ccmc);
                startActivity(intent);
                break;
            case R.id.ll_rzjl_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getEventBus(Integer integer) {
        if (integer != null) {
            rzjg1 = DbServices.getInstance(getBaseContext()).selectWSBrzjg(kcmc, ccmc, "1");
            rzjg2 = DbServices.getInstance(getBaseContext()).selectKCCCrzjg(kcmc, ccmc);
            if (rzjg2.size() != 0) {
                if (((int) (100.0d * ((((double) rzjg1.size()) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))) == 100) {
                    mListItemUploadingLl.setVisibility(View.GONE);
                    mListItemNouploadLl.setVisibility(View.GONE);
                    mListItemUploadedLl.setVisibility(View.VISIBLE);
                } else {
                    mListItemUploadingLl.setVisibility(View.VISIBLE);
                    mListItemNouploadLl.setVisibility(View.GONE);
                    mListItemUploadedLl.setVisibility(View.GONE);
                    mListItemPiechartYsc.setText(rzjg1.size() + "");
                    mListItemPiechartSczrs.setText(rzjg2.size() + "");
                    mListItemPiechartPb.setProgress(((int) (100.0d * ((((double) (rzjg1.size())) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))));
                }
            } else {
                mListItemUploadedLl.setVisibility(View.GONE);
                mListItemUploadingLl.setVisibility(View.GONE);
                mListItemNouploadLl.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent intent = new Intent(RZDJJLActivity.this, RZXQActivity.class);
        RzdjjlHistoryViw rzdjjlHistoryViw = (RzdjjlHistoryViw) rzdjjlHistoryAdapter.getChild(groupPosition, childPosition);
        intent.putExtra("rzjlkc", rzdjjlHistoryViw.getKc_mc());
        intent.putExtra("rzjlcc", rzdjjlHistoryViw.getCc_mc());
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouch: ACTION_DOWN");
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouch: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouch: ACTION_UP");
                if (event.getX() != x && y != event.getY()) {
                    isHD = true;
                } else {
                    isHD = false;
                }
                break;
        }
        return isHD;
    }

    /**
     * ExpandListView自适应高度 根据子项数量
     *
     * @param listView
     * @param listAdapter listView 的适配器
     */
    public void setListHeight(ExpandableListView listView, RZDJJLHistoryAdapter listAdapter) {
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int total = 0;
        View listItem;
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            listItem = listAdapter.getGroupView(i, false, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            total += (listAdapter.getChildrenCount(0) - 1);
        }
        for (int i = 0; i < isExpand.length; i++) {
            if (isExpand[i] == 1)
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                    total += (listAdapter.getChildrenCount(i) - 1);
                }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * total);
        listView.setLayoutParams(params);
    }

    private PieData setPiechartData(int paramInt1, int paramInt2, int paramInt3) {
        List<PieEntry> pieEntries = new ArrayList<>();
        float[] arrayOfFloat;
        if (paramInt3 != 0) {
            float f = (1.0F * paramInt1) / (1.0F * paramInt3);
            float h = (1.0F * paramInt2) / (1.0F * paramInt3);
            arrayOfFloat = new float[3];
            arrayOfFloat[0] = f;
            arrayOfFloat[1] = h;
            arrayOfFloat[2] = (1.0F - f - h);
            pieEntries.add(new PieEntry(arrayOfFloat[0], "已验证"));
            pieEntries.add(new PieEntry(arrayOfFloat[1], "缺考"));
            pieEntries.add(new PieEntry(arrayOfFloat[2], "未验证"));
        }
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add(Integer.valueOf(Color.rgb(44, 62, 80)));
        localArrayList2.add(Integer.valueOf(Color.rgb(66, 22, 8)));
        localArrayList2.add(Integer.valueOf(Color.rgb(41, 128, 185)));
        PieDataSet localPieDataSet = new PieDataSet(pieEntries, "");
        localPieDataSet.setColors(localArrayList2);//设置饼块颜色
        localPieDataSet.setValueLinePart1OffsetPercentage(75.0F);
        localPieDataSet.setValueLinePart1Length(0.1F);
        localPieDataSet.setValueLinePart2Length(1.1F);
        localPieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        localPieDataSet.setValueLineWidth(1.0F);//设置线宽
        localPieDataSet.setValueTextSize(18.0F); // 文字大小
        PieData localPieData = new PieData(localPieDataSet);
        localPieData.setDrawValues(true);// 绘制每个点的值
        localPieData.setValueTextColor(-16777216);
        localPieData.setValueFormatter(new PercentFormatter());
        return localPieData;
    }

    private void initPiechart(PieChart pieChart) {
        // 设置饼图是否接收点击事件，默认为true
        pieChart.setTouchEnabled(false);
        //设置饼图是否使用百分比
        pieChart.setUsePercentValues(true);
        //设置圆盘中间文字的大小
        pieChart.setCenterTextSize(22.0F);
        //设置圆盘是否转动，默认转动
        pieChart.setRotationEnabled(false);
        //设置圆盘中间文字的颜色
        pieChart.setCenterTextColor(R.color.white);
        //是否显示饼图中间空白区域，默认显示
        pieChart.setDrawHoleEnabled(false);
        //设置饼图每一块说明的字体大小
        pieChart.setEntryLabelTextSize(16.0F);
        // 是否绘制图例
        pieChart.getLegend().setEnabled(false);
        // 忽略其自己计算的偏移。
        pieChart.setExtraOffsets(25.0F, 0.0F, 25.0F, 0.0F);
        // 双击时能都高亮，默认true
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setAnimation(null);
        pieChart.getDescription().setEnabled(false);
    }
}
