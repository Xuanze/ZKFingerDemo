package com.zhongruan.android.zkfingerdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.QuickAdapter;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks_cjxx;

import java.util.List;

public class DailyFragment extends RxFragment {


    private RecyclerView recyclerView;
    private QuickAdapter qadapter;
    private View rootView;
    private List<Bk_ks_cjxx> datas;
    public static final String TYPE = "TYPE";
    private int mytype;

    public DailyFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        DailyFragment dailyFragment = new DailyFragment();
        dailyFragment.setArguments(args);
        return dailyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mytype = getArguments().getInt(TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_dailyduty, null);
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
        recyclerView = rootView.findViewById(R.id.rv_duty);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //查询数据库
        if (mytype == 2) {
            datas = DbServices.getInstance(getContext()).loadAllNote();
        } else {
            datas = DbServices.getInstance(getContext()).querySbzt(mytype);
        }
        //实例化Adapter
        qadapter = new QuickAdapter(getContext(), datas);
        recyclerView.setAdapter(qadapter);
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
