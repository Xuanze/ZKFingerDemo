package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.zhongruan.android.zkfingerdemo.R;

import java.util.HashMap;


public class MultiProgressDialog extends Dialog {
    private ListView listView;
    private Context mContext;
    private MultiImportAdapter multiImportAdapter;

    static class MultiImportAdapter extends BaseAdapter {
        private HashMap<Integer, Boolean> finishMap;
        private Context mContext;
        private HashMap<Integer, String> progressMap;

        public MultiImportAdapter(Context mContext) {
            this.progressMap = new HashMap();
            this.finishMap = new HashMap();
            this.mContext = mContext;
        }

        public void setProgressMap(HashMap<Integer, String> progressMap) {
            this.progressMap = progressMap;
        }

        public void setFinishMap(HashMap<Integer, Boolean> finishMap) {
            this.finishMap = finishMap;
        }

        public int getCount() {
            if (this.progressMap != null) {
                return this.progressMap.size();
            }
            return 0;
        }

        public Object getItem(int position) {
            if (this.progressMap != null) {
                return this.progressMap.get(Integer.valueOf(position));
            }
            return null;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(this.mContext, R.layout.pad_dialog_view_listitem, null);
                holder = new ViewHolder();
                holder.dialog_task_tv = convertView.findViewById(R.id.dialog_task_tv);
                holder.dialog_progressbar = convertView.findViewById(R.id.dialog_progressbar);
                holder.dialog_complete_iv = convertView.findViewById(R.id.dialog_complete_iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (this.progressMap != null && this.progressMap.size() > 0) {
                holder.dialog_task_tv.setText((this.progressMap.get(Integer.valueOf(position))));
            }
            if (this.finishMap == null || this.finishMap.size() <= 0) {
                holder.dialog_complete_iv.setVisibility(View.GONE);
                holder.dialog_progressbar.setVisibility(View.VISIBLE);
            } else if (this.finishMap.get(Integer.valueOf(position)) == null || !((Boolean) this.finishMap.get(Integer.valueOf(position))).booleanValue()) {
                holder.dialog_complete_iv.setVisibility(View.GONE);
                holder.dialog_progressbar.setVisibility(View.VISIBLE);
            } else {
                holder.dialog_complete_iv.setVisibility(View.VISIBLE);
                holder.dialog_progressbar.setVisibility(View.GONE);
            }
            return convertView;
        }

        public void clear() {
            if (this.progressMap != null) {
                this.progressMap.clear();
            }
            if (this.finishMap != null) {
                this.finishMap.clear();
            }
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        ImageView dialog_complete_iv;
        ProgressBar dialog_progressbar;
        TextView dialog_task_tv;

        ViewHolder() {
        }
    }

    public MultiProgressDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
        this.multiImportAdapter = null;
        this.mContext = context;
    }

    public MultiProgressDialog createDialog() {
        setContentView(R.layout.pad_view_multiprogress_dialog);
        getWindow().getAttributes().gravity = 17;
        this.listView = findViewById(R.id.multi_progress_lv);
        this.multiImportAdapter = new MultiImportAdapter(this.mContext);
        this.listView.setAdapter(this.multiImportAdapter);
        return this;
    }

    public MultiProgressDialog setTitile(String strTitle) {
        ((TextView) findViewById(R.id.multi_progress_title)).setText(strTitle);
        return this;
    }

    public MultiProgressDialog setProgressMap(HashMap<Integer, String> progressMap) {
        this.multiImportAdapter.setProgressMap(progressMap);
        this.multiImportAdapter.notifyDataSetChanged();
        return this;
    }

    public MultiProgressDialog setFinishMap(HashMap<Integer, Boolean> finishMap) {
        this.multiImportAdapter.setFinishMap(finishMap);
        this.multiImportAdapter.notifyDataSetChanged();
        return this;
    }

    public void setLastPosition() {
        new Handler().post(new Runnable() {
            public void run() {
                MultiProgressDialog.this.listView.setSelection(MultiProgressDialog.this.multiImportAdapter.getCount() - 1);
            }
        });
    }

    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    public void dismiss() {
        if (this.multiImportAdapter != null) {
            this.multiImportAdapter.clear();
        }
        super.dismiss();
    }
}
