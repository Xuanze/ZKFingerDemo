package com.zhongruan.android.zkfingerdemo.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
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
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.RZJLHistoryAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.view.RzjlHistoryViw;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.ui.RZXQActivity;
import com.zhongruan.android.zkfingerdemo.ui.SelectKcCcActivity;
import com.zhongruan.android.zkfingerdemo.ui.TestActivity;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;
import com.zhongruan.android.zkfingerdemo.view.UploadProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RZJLFragment extends RxFragment implements View.OnClickListener {


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
    /**
     * 历史记录
     */
    private List<Bk_ks> bk_ks, bkKsLs;
    private String ccmc, kcmc;
    private List<Sfrz_rzjg> rzjg1, rzjg2;
    private String TAG = "HNZR";
    private ExpandableListView mEplvStatistic;
    private RZJLHistoryAdapter RZJLHistoryAdapter;
    private List<List<RzjlHistoryViw>> historyLists;

    // ExpandListView 列表状态 1展开 0关闭 该案例中设置为三级
    private int[] isExpand = new int[]{0, 0, 0};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rzjl, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListItemPiechartCc = view.findViewById(R.id.list_item_piechart_cc);
        mListItemPiechartKc = view.findViewById(R.id.list_item_piechart_kc);
        mListItemPiechartTotal = view.findViewById(R.id.list_item_piechart_total);
        mListItemPiechartYyz = view.findViewById(R.id.list_item_piechart_yyz);
        mListItemPiechartWyz = view.findViewById(R.id.list_item_piechart_wyz);
        mListItemPiechartYsc = view.findViewById(R.id.list_item_piechart_ysc);
        mListItemPiechartSczrs = view.findViewById(R.id.list_item_piechart_sczrs);
        mListItemPiechartPb = view.findViewById(R.id.list_item_piechart_pb);
        mListItemUploadingLl = view.findViewById(R.id.list_item_uploading_ll);
        mListItemUploadedLl = view.findViewById(R.id.list_item_uploaded_ll);
        mListItemNouploadLl = view.findViewById(R.id.list_item_noupload_ll);
        mListItemPiechart = view.findViewById(R.id.list_item_piechart);
        mListItemStatisticHistoryItem = view.findViewById(R.id.list_item_statistic_history_item);
        mEplvStatistic = view.findViewById(R.id.eplv_statistic);
        mListItemStatisticHistoryItem.setOnClickListener(this);
        initPiechart(mListItemPiechart);


        ccmc = DbServices.getInstance(getActivity()).selectCC().get(0).getCc_name();
        kcmc = DbServices.getInstance(getActivity()).selectKC().get(0).getKc_name();
        bk_ks = DbServices.getInstance(getActivity()).queryBKKSList(kcmc, ccmc);
        mListItemPiechartCc.setText(DbServices.getInstance(getActivity()).selectCC().get(0).getCc_name());
        mListItemPiechartKc.setText(DbServices.getInstance(getActivity()).selectKC().get(0).getKc_name() + " " + DbServices.getInstance(getActivity()).selectCC().get(0).getKm_name());
        mListItemPiechartTotal.setText(bk_ks.size() + "");
        mListItemPiechartYyz.setText(DbServices.getInstance(getActivity()).queryBkKsIsTG(kcmc, ccmc, "1") + "");
        mListItemPiechartWyz.setText(bk_ks.size() - DbServices.getInstance(getActivity()).queryBkKsIsTG(kcmc, ccmc, "1") + "");

        rzjg1 = DbServices.getInstance(getActivity()).selectWSBrzjg3(kcmc, ccmc, "23", "1");
        rzjg2 = DbServices.getInstance(getActivity()).selectKCCCrzjg3(kcmc, ccmc, "23");

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
        mListItemPiechart.setData(setPiechartData(DbServices.getInstance(getActivity()).queryBkKsIsTG(kcmc, ccmc, "1"), bk_ks.size()));
        mListItemPiechart.invalidate();
        bkKsLs = DbServices.getInstance(getActivity()).queryNOBKKSList(kcmc, ccmc);
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
            List<RzjlHistoryViw> rzjlHistoryViwList = new ArrayList<>();
            for (String a : remap.keySet()) {
                List<String> b = remap.get(a);
                for (String c : b) {
                    RzjlHistoryViw rzjlHistoryViw = new RzjlHistoryViw();
                    rzjlHistoryViw.setCc_mc(c);
                    rzjlHistoryViw.setKc_mc(a);
                    LogUtil.i("lhj", a + "  -------------  " + c);
                    rzjlHistoryViw.setAll_sl(DbServices.getInstance(getActivity()).queryBKKSList(a, c).size());
                    rzjlHistoryViw.setRz_sl(DbServices.getInstance(getActivity()).queryBkKsIsTG(a, c, "1"));
                    rzjlHistoryViw.setWrz_sl(DbServices.getInstance(getActivity()).queryBkKsWTG(a, c, "1"));
                    rzjlHistoryViw.setAll_sb(DbServices.getInstance(getActivity()).selectKCCCrzjg(a, c).size());
                    rzjlHistoryViw.setYSb_sl(DbServices.getInstance(getActivity()).selectWSBrzjg(a, c, "1").size());
                    rzjlHistoryViwList.add(rzjlHistoryViw);
                }
            }
            historyLists.add(rzjlHistoryViwList);
            LogUtil.i(TAG, rzjlHistoryViwList.size());
            RZJLHistoryAdapter = new RZJLHistoryAdapter(getActivity(), kcmc, ccmc, historyLists);
            mEplvStatistic.setAdapter(RZJLHistoryAdapter);

            // 通过监听展开和关闭事件动态设置高度
            mEplvStatistic.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    isExpand[groupPosition] = 1;
                    setListHeight(mEplvStatistic, RZJLHistoryAdapter);
                }
            });

            mEplvStatistic.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {
                    isExpand[groupPosition] = 0;
                    setListHeight(mEplvStatistic, RZJLHistoryAdapter);
                }
            });
        } else {
            RZJLHistoryAdapter = new RZJLHistoryAdapter(getActivity(), kcmc, ccmc, null);
            mEplvStatistic.setAdapter(RZJLHistoryAdapter);
        }
    }


    /*
     * ExpandListView自适应高度 根据子项数量
     * @param listView
     * @param listAdapter listView 的适配器
     * */
    public void setListHeight(ExpandableListView listView, RZJLHistoryAdapter listAdapter) {

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


    private PieData setPiechartData(int paramInt1, int paramInt2) {
        List<PieEntry> pieEntries = new ArrayList<>();
        float[] arrayOfFloat;
        if (paramInt2 != 0) {
            float f = (1.0F * paramInt1) / (1.0F * paramInt2);
            arrayOfFloat = new float[2];
            arrayOfFloat[0] = f;
            arrayOfFloat[1] = (1.0F - f);
            pieEntries.add(new PieEntry(arrayOfFloat[0], "已验证"));
            pieEntries.add(new PieEntry(arrayOfFloat[1], "未验证"));
        }
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add(Integer.valueOf(Color.rgb(44, 62, 80)));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_item_statistic_history_item:
                Intent intent = new Intent(getActivity(), RZXQActivity.class);
                intent.putExtra("rzjlkc", kcmc);
                intent.putExtra("rzjlcc", ccmc);
                startActivity(intent);
                break;
        }
    }
}
