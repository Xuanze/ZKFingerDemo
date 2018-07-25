package com.zhongruan.android.zkfingerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjl;
import com.zhongruan.android.zkfingerdemo.utils.DateUtil;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RzjlxqAdapter extends RecyclerView.Adapter<RzjlxqAdapter.MyViewHolder> {
    private Context context;
    private List<Bk_ks> ksList;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public RzjlxqAdapter(Context context, List<Bk_ks> ksList) {
        this.context = context;
        this.ksList = ksList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(layoutInflater.inflate(R.layout.pad_adapter_listitem_kslist, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if ((this.ksList != null) && (this.ksList.size() > 0)) {
            Picasso.with(context).load(new File(FileUtils.getAppSavePath() + "/" + ksList.get(position).getKs_xp())).into(holder.mAuthItemXp);
            holder.mAuthItemXm.setText(ksList.get(position).getKs_xm());
            holder.mAuthItemXb.setText(ksList.get(position).getKs_xb().equals("1") ? "男" : "女");
            holder.mAuthItemKc.setText(ksList.get(position).getKs_kcno());
            holder.mAuthItemZjh.setText(ksList.get(position).getKs_zjno());
            holder.mAuthItemZwh.setText(ksList.get(position).getKs_zwh());
            holder.mAuthItemKsid.setText(ksList.get(position).getKs_ksno());
            holder.mAuthItemRzTime.setText(ksList.get(position).getRzTime());
            if (ksList.get(position).getIsRZ().equals("0")) {
                holder.mAuthItemRzjg.setText("现场等待认证");
                holder.mAuthItemRzbjIv.setVisibility(View.INVISIBLE);
            } else if (ksList.get(position).getIsRZ().equals("1")) {
                holder.mAuthItemRzjg.setText("现场认证通过");
                holder.mAuthItemRzbjIv.setBackgroundResource(R.drawable.img_base_check);
                holder.mAuthItemRzbjIv.setVisibility(View.VISIBLE);
            } else if (ksList.get(position).getIsRZ().equals("3")) {
                holder.mAuthItemRzjg.setText("现场认证不通过");
                holder.mAuthItemRzbjIv.setBackgroundResource(R.drawable.img_base_close);
                holder.mAuthItemRzbjIv.setVisibility(View.VISIBLE);
            } else if (ksList.get(position).getIsRZ().equals("2")) {
                holder.mAuthItemRzjg.setText("现场认证缺考");
                holder.mAuthItemRzbjIv.setBackgroundResource(R.drawable.img_base_close);
                holder.mAuthItemRzbjIv.setVisibility(View.VISIBLE);
            }
            if (!ksList.get(position).getIsRZ().equals("0") && !ksList.get(position).getIsRZ().equals("2")) {
                holder.mRlV1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(position, ksList.get(position).getKs_ksno());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return ksList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mAuthItemXp, mAuthItemRzbjIv;
        TextView mAuthItemXm, mAuthItemXb, mAuthItemKc, mAuthItemZjh, mAuthItemZwh, mAuthItemKsid, mAuthItemRzjg, mAuthItemRzTime;
        LinearLayout mRlV1;

        public MyViewHolder(View itemView) {
            super(itemView);
            mAuthItemXp = itemView.findViewById(R.id.auth_item_xp);
            mAuthItemXm = itemView.findViewById(R.id.auth_item_xm);
            mAuthItemXb = itemView.findViewById(R.id.auth_item_xb);
            mAuthItemKc = itemView.findViewById(R.id.auth_item_kc);
            mAuthItemZjh = itemView.findViewById(R.id.auth_item_zjh);
            mAuthItemZwh = itemView.findViewById(R.id.auth_item_zwh);
            mAuthItemKsid = itemView.findViewById(R.id.auth_item_ksid);
            mAuthItemRzbjIv = itemView.findViewById(R.id.auth_item_rzbj_iv);
            mAuthItemRzjg = itemView.findViewById(R.id.auth_item_rzjg);
            mAuthItemRzTime = itemView.findViewById(R.id.auth_item_rz_time);
            mRlV1 = itemView.findViewById(R.id.rl_v1);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position, String ksno);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
