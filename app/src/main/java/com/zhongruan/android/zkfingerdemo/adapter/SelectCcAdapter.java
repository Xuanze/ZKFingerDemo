package com.zhongruan.android.zkfingerdemo.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.db.entity.Ks_cc;

import java.util.ArrayList;
import java.util.List;

public class SelectCcAdapter extends BaseAdapter {
    private boolean[] ischoiced;
    private List<Ks_cc> ccList;
    private Context mContext;

    static class ViewHolder {
        LinearLayout list_item_cc_ll;
        TextView list_item_cc_name;
        TextView list_item_cc_no;
    }

    public SelectCcAdapter(Context mContext, List<Ks_cc> ccList) {
        this.mContext = mContext;
        this.ccList = new ArrayList();
        this.ccList.addAll(ccList);
        this.ischoiced = new boolean[ccList.size()];
    }

    public int getCount() {
        return this.ccList.size();
    }

    public Object getItem(int position) {
        return this.ccList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(this.mContext, R.layout.pad_adapter_select_cc, null);
            holder = new ViewHolder();
            holder.list_item_cc_name = convertView.findViewById(R.id.list_item_cc_name);
            holder.list_item_cc_no = convertView.findViewById(R.id.list_item_cc_no);
            holder.list_item_cc_ll = convertView.findViewById(R.id.list_item_cc_ll);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (this.ccList != null && this.ccList.size() > 0) {
            holder.list_item_cc_no.setText(this.ccList.get(position).getCc_no());
            holder.list_item_cc_name.setText(this.ccList.get(position).getCc_name());
        }
        if (this.ischoiced[position]) {
            holder.list_item_cc_ll.setBackgroundResource(R.drawable.selector_module_tab_arrange_levelsubject_selectcc_selected);
            holder.list_item_cc_name.setTextColor(this.mContext.getResources().getColor(R.color.color_module_tab_arrange_levelsubject_selectcc_text_white));
            holder.list_item_cc_no.setTextColor(this.mContext.getResources().getColor(R.color.color_module_tab_arrange_levelsubject_selectcc_text_white));
        } else {
            holder.list_item_cc_ll.setBackgroundResource(R.drawable.selector_module_tab_arrange_levelsubject_selectcc_unselected);
            holder.list_item_cc_name.setTextColor(this.mContext.getResources().getColor(R.color.color_module_tab_arrange_levelsubject_selectcc_text_darkgray));
            holder.list_item_cc_no.setTextColor(this.mContext.getResources().getColor(R.color.color_module_tab_arrange_levelsubject_selectcc_text_darkgray));
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

    public List<Ks_cc> getChosenCcList() {
        ArrayList<Ks_cc> kcSelectedList = new ArrayList();
        for (int i = 0; i < this.ischoiced.length; i++) {
            if (this.ischoiced[i]) {
                kcSelectedList.add(this.ccList.get(i));
            }
        }
        return kcSelectedList;
    }
}
