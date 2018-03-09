package com.zhongruan.android.zkfingerdemo.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.db.entity.Ks_kc;

import java.util.ArrayList;
import java.util.List;

public class SelectKcAdapter extends BaseAdapter {
    private boolean[] ischoiced;
    private List<Ks_kc> kcList;
    private Context mContext;

    static class ViewHolder {
        LinearLayout list_item_kc_ll;
        TextView list_item_kc_name;
    }

    public SelectKcAdapter(Context mContext, List<Ks_kc> kcList) {
        this.mContext = mContext;
        this.kcList = new ArrayList();
        this.kcList.addAll(kcList);
        this.ischoiced = new boolean[kcList.size()];
    }

    public int getCount() {
        return this.kcList.size();
    }

    public Object getItem(int position) {
        return this.kcList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(this.mContext, R.layout.pad_adapter_select_kc, null);
            holder = new ViewHolder();
            holder.list_item_kc_name = convertView.findViewById(R.id.list_item_kc_name);
            holder.list_item_kc_ll = convertView.findViewById(R.id.list_item_kc_ll);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (this.kcList != null && this.kcList.size() > 0) {
            holder.list_item_kc_name.setText(this.kcList.get(position).getKc_name());
        }
        if (this.ischoiced[position]) {
            holder.list_item_kc_ll.setBackgroundResource(R.drawable.selector_module_tab_arrange_levelsubject_selectcc_selected);
            holder.list_item_kc_name.setTextColor(this.mContext.getResources().getColor(R.color.color_module_tab_arrange_levelsubject_selectcc_text_white));
        } else {
            holder.list_item_kc_ll.setBackgroundResource(R.drawable.selector_module_tab_arrange_levelsubject_selectcc_unselected);
            holder.list_item_kc_name.setTextColor(this.mContext.getResources().getColor(R.color.color_module_tab_arrange_levelsubject_selectcc_text_darkgray));
        }
        return convertView;
    }


    public void choiceSingleState(int position) {
        boolean z = false;
        for (int i = 0; i < this.ischoiced.length; i++) {
            if (i != position) {
                this.ischoiced[i] = false;
            }
        }
        boolean[] zArr = this.ischoiced;
        if (!this.ischoiced[position]) {
            z = true;
        }
        zArr[position] = z;
        notifyDataSetChanged();
    }

    public List<Ks_kc> getChosenKcList() {
        ArrayList<Ks_kc> kcSelectedList = new ArrayList();
        for (int i = 0; i < this.ischoiced.length; i++) {
            if (this.ischoiced[i]) {
                kcSelectedList.add(this.kcList.get(i));
            }
        }
        return kcSelectedList;
    }
}
