package com.zhongruan.android.zkfingerdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.view.HistoryViw;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.ui.KWDJActivity;
import com.zhongruan.android.zkfingerdemo.view.UploadProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHJ on 2018/2/2.
 */

public class HistoryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<List<HistoryViw>> list_data;
    private List<Bk_ks> bk_ks;
    private List<Sfrz_rzjg> rzjg1, rzjg2;
    private Handler handler = new Handler();
    private String ccmc, kcmc;

    public HistoryAdapter(Context context, String kcmc, String ccmc, List<List<HistoryViw>> list_data) {
        this.list_data = list_data;
        this.context = context;
        this.ccmc = ccmc;
        this.kcmc = kcmc;
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return list_data == null ? 1 : list_data.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return list_data == null ? 0 : list_data.get(groupPosition).size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        return list_data == null ? null : list_data.get(groupPosition);
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list_data == null ? null : list_data.get(groupPosition).get(childPosition);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return list_data == null ? 0 : groupPosition;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return list_data == null ? null : childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        DataHolder dataHolder = null;
        if (dataHolder == null) {
            dataHolder = new DataHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pad_view_rzjl_cc, null);
            dataHolder.mListItemPiechartCc = convertView.findViewById(R.id.list_item_piechart_cc);
            dataHolder.mListItemPiechartKc = convertView.findViewById(R.id.list_item_piechart_kc);
            dataHolder.mListItemPiechartTotal = convertView.findViewById(R.id.list_item_piechart_total);
            dataHolder.mListItemPiechartYyz = convertView.findViewById(R.id.list_item_piechart_yyz);
            dataHolder.mListItemPiechartWyz = convertView.findViewById(R.id.list_item_piechart_wyz);
            dataHolder.mListItemPiechartYsc = convertView.findViewById(R.id.list_item_piechart_ysc);
            dataHolder.mListItemPiechartSczrs = convertView.findViewById(R.id.list_item_piechart_sczrs);
            dataHolder.mListItemPiechartPb = convertView.findViewById(R.id.list_item_piechart_pb);
            dataHolder.mListItemUploadingLl = convertView.findViewById(R.id.list_item_uploading_ll);
            dataHolder.mListItemUploadedLl = convertView.findViewById(R.id.list_item_uploaded_ll);
            dataHolder.mListItemNouploadLl = convertView.findViewById(R.id.list_item_noupload_ll);
            dataHolder.mListItemPiechart = convertView.findViewById(R.id.list_item_piechart);
            dataHolder.ll_xq_view = convertView.findViewById(R.id.ll_xq_view);
            dataHolder.data = convertView.findViewById(R.id.list_item_tv);
            dataHolder.imageView = convertView.findViewById(R.id.list_item_history_iv);
            convertView.setTag(dataHolder);
        } else {
            dataHolder = (DataHolder) convertView.getTag();
        }
        dataHolder.ll_xq_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, KWDJActivity.class));
            }
        });
        dataHolder.mListItemPiechartCc.setText(DbServices.getInstance(context).selectCC().get(0).getCc_name());
        dataHolder.mListItemPiechartKc.setText(DbServices.getInstance(context).selectKC().get(0).getKc_name() + " " + DbServices.getInstance(context).selectCC().get(0).getKm_name());
        initPiechart(dataHolder.mListItemPiechart);
        handler.postDelayed(dataHolder.runnable, 10);
        if (list_data == null) {
            dataHolder.imageView.setVisibility(View.GONE);
            dataHolder.data.setText("当前暂无历史记录");
        } else {
            //判断是否已经打开列表
            if (isExpanded) {
                dataHolder.imageView.setBackgroundResource(R.drawable.img_module_tab_auth_statisticlist_arrow_up);
            } else {
                dataHolder.imageView.setBackgroundResource(R.drawable.img_module_tab_auth_statisticlist_arrow_down);
            }
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pad_adapter_rzjl_history, null);
            viewHolder.mListItemHistoryCc = convertView.findViewById(R.id.list_item_history_cc);
            viewHolder.mListItemHistoryKc = convertView.findViewById(R.id.list_item_history_kc);
            viewHolder.mListItemHistoryTotal = convertView.findViewById(R.id.list_item_history_total);
            viewHolder.mListItemHistoryYyz = convertView.findViewById(R.id.list_item_history_yyz);
            viewHolder.mListItemHistoryWyz = convertView.findViewById(R.id.list_item_history_wyz);
            viewHolder.mListItemHistoryYsc = convertView.findViewById(R.id.list_item_history_ysc);
            viewHolder.mListItemHistorySczrs = convertView.findViewById(R.id.list_item_history_sczrs);
            viewHolder.mListItemHistoryPb = convertView.findViewById(R.id.list_item_history_pb);
            viewHolder.mListItemPiechartHistory = convertView.findViewById(R.id.list_item_piechart_history);
            initPiechart(viewHolder.mListItemPiechartHistory);
            viewHolder.mListItemUploadingLlHistory = convertView.findViewById(R.id.list_item_uploading_ll_history);
            viewHolder.mListItemUploadedLlHistory = convertView.findViewById(R.id.list_item_uploaded_ll_history);
            viewHolder.mListItemNouploadLlHistory = convertView.findViewById(R.id.list_item_noupload_ll_history);
            viewHolder.mLlItemXqView = convertView.findViewById(R.id.ll_item_xq_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mListItemHistoryCc.setText(list_data.get(groupPosition).get(childPosition).getCc_mc());
        viewHolder.mListItemHistoryKc.setText(list_data.get(groupPosition).get(childPosition).getKc_mc());
        viewHolder.mListItemHistoryTotal.setText(Integer.toString(list_data.get(groupPosition).get(childPosition).getAll_sl()));
        viewHolder.mListItemHistoryYyz.setText(Integer.toString(list_data.get(groupPosition).get(childPosition).getRz_sl()));
        viewHolder.mListItemHistoryWyz.setText(Integer.toString(list_data.get(groupPosition).get(childPosition).getWrz_sl()));

        if (list_data.get(groupPosition).get(childPosition).getAll_sb() != 0) {
            if ((int) (100.0d * ((((double) (list_data.get(groupPosition).get(childPosition).getYSb_sl())) * 1.0d) / (((double) (list_data.get(groupPosition).get(childPosition).getAll_sb())) * 1.0d))) == 100) {
                viewHolder.mListItemUploadingLlHistory.setVisibility(View.GONE);
                viewHolder.mListItemNouploadLlHistory.setVisibility(View.GONE);
                viewHolder.mListItemUploadedLlHistory.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mListItemUploadingLlHistory.setVisibility(View.VISIBLE);
                viewHolder.mListItemNouploadLlHistory.setVisibility(View.GONE);
                viewHolder.mListItemUploadedLlHistory.setVisibility(View.GONE);

                viewHolder.mListItemHistoryYsc.setText(Integer.toString(list_data.get(groupPosition).get(childPosition).getYSb_sl()));
                viewHolder.mListItemHistorySczrs.setText(Integer.toString(list_data.get(groupPosition).get(childPosition).getAll_sb()));
                viewHolder.mListItemHistoryPb.setProgress((int) (100.0d * ((((double) (list_data.get(groupPosition).get(childPosition).getYSb_sl())) * 1.0d) / (((double) (list_data.get(groupPosition).get(childPosition).getAll_sb())) * 1.0d))));
            }
        } else {
            viewHolder.mListItemUploadedLlHistory.setVisibility(View.GONE);
            viewHolder.mListItemUploadingLlHistory.setVisibility(View.GONE);
            viewHolder.mListItemNouploadLlHistory.setVisibility(View.VISIBLE);
        }
        viewHolder.mListItemPiechartHistory.setData(setPiechartData(list_data.get(groupPosition).get(childPosition).getRz_sl(), list_data.get(groupPosition).get(childPosition).getAll_sl()));
        return convertView;
    }

    class DataHolder {
        TextView mListItemPiechartCc;
        TextView mListItemPiechartKc;
        TextView mListItemPiechartTotal;
        TextView mListItemPiechartYyz;
        TextView mListItemPiechartWyz;
        TextView mListItemPiechartYsc;
        TextView mListItemPiechartSczrs;
        UploadProgressBar mListItemPiechartPb;
        LinearLayout mListItemUploadingLl;
        LinearLayout mListItemUploadedLl;
        LinearLayout mListItemNouploadLl;
        PieChart mListItemPiechart;
        LinearLayout ll_xq_view;
        TextView data;
        ImageView imageView;
        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bk_ks = DbServices.getInstance(context).queryBKKSList(kcmc, ccmc);
                mListItemPiechartTotal.setText(bk_ks.size() + "");
                mListItemPiechartYyz.setText(DbServices.getInstance(context).queryBkKsIsTG(kcmc, ccmc, "1") + "");
                mListItemPiechartWyz.setText(bk_ks.size() - DbServices.getInstance(context).queryBkKsIsTG(kcmc, ccmc, "1") + "");
                rzjg2 = DbServices.getInstance(context).selectCCrzjg(ccmc);
                rzjg1 = DbServices.getInstance(context).selectRZJGSB(ccmc);
                if (rzjg2.size() != 0) {
                    if (((int) (100.0d * ((((double) (rzjg2.size() - rzjg1.size())) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))) == 100) {
                        mListItemUploadingLl.setVisibility(View.GONE);
                        mListItemNouploadLl.setVisibility(View.GONE);
                        mListItemUploadedLl.setVisibility(View.VISIBLE);
                    } else {
                        mListItemUploadingLl.setVisibility(View.VISIBLE);
                        mListItemNouploadLl.setVisibility(View.GONE);
                        mListItemUploadedLl.setVisibility(View.GONE);
                        mListItemPiechartYsc.setText((rzjg2.size() - rzjg1.size()) + "");
                        mListItemPiechartSczrs.setText(rzjg2.size() + "");
                        mListItemPiechartPb.setProgress(((int) (100.0d * ((((double) (rzjg2.size() - rzjg1.size())) * 1.0d) / (((double) rzjg2.size()) * 1.0d)))));
                    }
                } else {
                    mListItemUploadedLl.setVisibility(View.GONE);
                    mListItemUploadingLl.setVisibility(View.GONE);
                    mListItemNouploadLl.setVisibility(View.VISIBLE);
                }
                mListItemPiechart.setData(setPiechartData(DbServices.getInstance(context).queryBkKsIsTG(kcmc, ccmc, "1"), bk_ks.size()));
                mListItemPiechart.invalidate();
            }
        };
    }

    static class ViewHolder {
        TextView mListItemHistoryCc;
        TextView mListItemHistoryKc;
        TextView mListItemHistoryTotal;
        TextView mListItemHistoryYyz;
        TextView mListItemHistoryWyz;
        TextView mListItemHistoryYsc;
        TextView mListItemHistorySczrs;
        UploadProgressBar mListItemHistoryPb;
        PieChart mListItemPiechartHistory;
        LinearLayout mListItemUploadingLlHistory;
        LinearLayout mListItemUploadedLlHistory;
        LinearLayout mListItemNouploadLlHistory;
        LinearLayout mLlItemXqView;
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
