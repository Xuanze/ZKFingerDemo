package com.zhongruan.android.zkfingerdemo.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zhongruan.android.zkfingerdemo.R;

import net.lingala.zip4j.util.InternalZipConstants;

import java.util.List;

public class FileBrowserAdapter extends BaseAdapter {
    List<String> filenames;
    Context mContext;
    int selectedPosition;

    static class ViewHolder {
        ImageView ivFile;
        LinearLayout llFileBrowser;
        TextView tvName;

        ViewHolder() {
        }
    }

    public FileBrowserAdapter(Context context, List<String> filenames) {
        this.filenames = filenames;
        this.mContext = context;
    }

    public int getCount() {
        return this.filenames.size();
    }

    public Object getItem(int position) {
        return this.filenames.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(this.mContext, R.layout.pad_adapter_usb_listview, null);
            holder = new ViewHolder();
            holder.ivFile = convertView.findViewById(R.id.ivFile);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.llFileBrowser = convertView.findViewById(R.id.llFileBrowser);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == this.selectedPosition) {
            holder.ivFile.setBackgroundResource(R.drawable.img_module_tab_inport_usb_zip_selected);
            holder.llFileBrowser.setBackgroundResource(R.color.holo_yellow_light);
        } else {
            holder.ivFile.setBackgroundResource(R.drawable.img_module_tab_inport_usb_zip);
            holder.llFileBrowser.setBackgroundResource(R.color.white);
        }
        holder.tvName.setText((this.filenames.get(position)).substring((this.filenames.get(position)).lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1));
        return convertView;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
