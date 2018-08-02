package com.zhongruan.android.zkfingerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjl;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RzjlAdapter extends RecyclerView.Adapter<RzjlAdapter.MyViewHolder> {
    private Context context;
    private List<Sfrz_rzjl> rzjlList;
    private LayoutInflater layoutInflater;

    public RzjlAdapter(Context context, List<Sfrz_rzjl> rzjlList) {
        this.context = context;
        this.rzjlList = rzjlList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(layoutInflater.inflate(R.layout.pad_adapter_listitem_rzjl, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(new File(FileUtils.getAppSavePath() + "/" + rzjlList.get(position).getRzjl_pith())).into(holder.mListitemRzjlIv);
        holder.mListitemRzjlRzjlsj.setText(rzjlList.get(position).getRzjl_time());
        if (rzjlList.get(position).getRzjl_rzfsno().equals("8003")) {
            holder.mListitemRzjlRzfs.setText("指纹比对");
            holder.mListitemRzjlResult.setText("比对通过");
        } else if (rzjlList.get(position).getRzjl_rzfsno().equals("8006")) {
            holder.mListitemRzjlRzfs.setText("指纹比对");
            holder.mListitemRzjlResult.setText("比对不通过");
        } else if (rzjlList.get(position).getRzjl_rzfsno().equals("8007")) {
            holder.mListitemRzjlRzfs.setText("补充拍照");
            holder.mListitemRzjlResult.setText("");
            holder.mListitemDivider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return rzjlList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mListitemRzjlIv;
        TextView mListitemRzjlRzfs, mListitemRzjlResult, mListitemRzjlRzjlsj;
        View mListitemDivider;

        public MyViewHolder(View itemView) {
            super(itemView);
            mListitemRzjlIv = itemView.findViewById(R.id.listitem_rzjl_iv);
            mListitemRzjlRzfs = itemView.findViewById(R.id.listitem_rzjl_rzfs);
            mListitemDivider = itemView.findViewById(R.id.listitem_divider);
            mListitemRzjlResult = itemView.findViewById(R.id.listitem_rzjl_result);
            mListitemRzjlRzjlsj = itemView.findViewById(R.id.listitem_rzjl_rzjlsj);
        }
    }
}
