package com.zhongruan.android.zkfingerdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.QuickAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.RzjlxqAdapter;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks_cjxx;
import com.zhongruan.android.zkfingerdemo.dialog.KsrzjlDialog;

import java.util.List;

public class RZXQFragment extends RxFragment {

    private View rootView;
    private List<Bk_ks> datas;
    public static final String TYPE = "TYPE";
    public static String kcStr = "kcStr";
    public static String ccStr = "ccStr";
    private int mytype;
    private RzjlxqAdapter rzjlxqAdapter;

    private String kcmc, ccmc;

    private ImageView mListItemPiechartCcIv;
    private TextView mVbKslistCc;
    private View mViewLine;
    private TextView mVbKslistKc;
    private RecyclerView mLvKs;

    public RZXQFragment newInstance(String kcmc, String ccmc, int type) {
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        args.putString(kcStr, kcmc);
        args.putString(ccStr, ccmc);
        RZXQFragment rzxqFragment = new RZXQFragment();
        rzxqFragment.setArguments(args);
        return rzxqFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mytype = getArguments().getInt(TYPE);
        kcmc = getArguments().getString(kcStr);
        ccmc = getArguments().getString(ccStr);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.pad_view_kslist_statistic, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListItemPiechartCcIv = rootView.findViewById(R.id.list_item_piechart_cc_iv);
        mVbKslistCc = rootView.findViewById(R.id.vb_kslist_cc);
        mViewLine = rootView.findViewById(R.id.view_line);
        mVbKslistKc = rootView.findViewById(R.id.vb_kslist_kc);
        mLvKs = rootView.findViewById(R.id.lv_ks);
        mLvKs.setLayoutManager(new LinearLayoutManager(getContext()));

        mVbKslistCc.setText(ccmc);
        mVbKslistKc.setText(kcmc + " " + DbServices.getInstance(getContext()).getKMmc(ccmc));
        //查询数据库
        if (mytype == 0) {
            datas = DbServices.getInstance(getContext()).queryBKKSList(kcmc, ccmc);
        } else if (mytype == 1) {
            datas = DbServices.getInstance(getContext()).queryBKKSLists(kcmc, ccmc, "1");
        } else if (mytype == 2) {
            datas = DbServices.getInstance(getContext()).queryBKKSLists(kcmc, ccmc, "3");
        } else if (mytype == 3) {
            datas = DbServices.getInstance(getContext()).queryBKKSLists(kcmc, ccmc, "2");
        } else if (mytype == 4) {
            datas = DbServices.getInstance(getContext()).queryBKKSLists(kcmc, ccmc, "0");
        }
        //实例化Adapter
        rzjlxqAdapter = new RzjlxqAdapter(getContext(), datas);
        rzjlxqAdapter.setOnItemClickListener(new RzjlxqAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, String ksno) {
                new KsrzjlDialog(getActivity(), R.style.dialog, datas.get(position).getKs_xm(), kcmc, ksno, DbServices.getInstance(getContext()).getKMno(ccmc)).show();
            }
        });
        mLvKs.setAdapter(rzjlxqAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
