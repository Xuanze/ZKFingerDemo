package com.zhongruan.android.zkfingerdemo.adapter;

import android.content.Context;
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
import com.zhongruan.android.zkfingerdemo.adapter.view.RzdjjlHistoryViw;
import com.zhongruan.android.zkfingerdemo.adapter.view.RzjlHistoryViw;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.view.UploadProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHJ on 2018/2/2.
 */

public class RZDJJLHistoryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<List<RzdjjlHistoryViw>> list_data;

    public RZDJJLHistoryAdapter(Context context, List<List<RzdjjlHistoryViw>> list_data) {
        this.list_data = list_data;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.pad_view_rzjl_history, null);
            dataHolder.mListItemTv = convertView.findViewById(R.id.list_item_tv);
            dataHolder.mListItemHistoryIv = convertView.findViewById(R.id.list_item_history_iv);
            dataHolder.mListItemStatisticSpreadLl = convertView.findViewById(R.id.list_item_statistic_spread_ll);
            convertView.setTag(dataHolder);
        } else {
            dataHolder = (DataHolder) convertView.getTag();
        }
        if (list_data == null) {
            dataHolder.mListItemHistoryIv.setVisibility(View.GONE);
            dataHolder.mListItemTv.setText("当前暂无历史记录");
        } else {
            //判断是否已经打开列表
            if (isExpanded) {
                dataHolder.mListItemHistoryIv.setBackgroundResource(R.drawable.img_module_tab_auth_statisticlist_arrow_down);
            } else {
                dataHolder.mListItemHistoryIv.setBackgroundResource(R.drawable.img_module_tab_auth_statisticlist_arrow_up);
            }
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pad_adapter_rzdjjl_history, null);
            viewHolder.mListItemHistoryCc = convertView.findViewById(R.id.list_item_history_cc);
            viewHolder.mListItemHistoryKc = convertView.findViewById(R.id.list_item_history_kc);
            viewHolder.mListItemHistoryTotal = convertView.findViewById(R.id.list_item_history_total);
            viewHolder.mListItemHistoryYyz = convertView.findViewById(R.id.list_item_history_yyz);
            viewHolder.mListItemHistoryWyz = convertView.findViewById(R.id.list_item_history_wyz);
            viewHolder.mListItemHistoryQk = convertView.findViewById(R.id.list_item_history_qk);
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
        viewHolder.mListItemHistoryQk.setText(Integer.toString(list_data.get(groupPosition).get(childPosition).getQk_sl()));

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
        viewHolder.mListItemPiechartHistory.setData(setPiechartData(list_data.get(groupPosition).get(childPosition).getRz_sl(), list_data.get(groupPosition).get(childPosition).getQk_sl(), list_data.get(groupPosition).get(childPosition).getAll_sl()));
        return convertView;
    }

    static class DataHolder {
        TextView mListItemTv;
        ImageView mListItemHistoryIv;
        LinearLayout mListItemStatisticSpreadLl;

    }

    static class ViewHolder {
        TextView mListItemHistoryCc, mListItemHistoryKc, mListItemHistoryTotal, mListItemHistoryYyz, mListItemHistoryWyz, mListItemHistoryQk, mListItemHistoryYsc, mListItemHistorySczrs;
        UploadProgressBar mListItemHistoryPb;
        PieChart mListItemPiechartHistory;
        LinearLayout mListItemUploadingLlHistory, mListItemUploadedLlHistory, mListItemNouploadLlHistory, mLlItemXqView;
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

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
