package com.zhongruan.android.zkfingerdemo.ui;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.ViewPagerAdapter;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.fragments.DailyFragment;


/**
 * Created by Administrator on 2017/8/11.
 */

public class CJJLActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llCollectStatisticBack, llCollectStatisticSearch;
    private TabLayout tabsCj;
    private ViewPager vpViewCj;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_cjjl);
    }

    @Override
    public void initViews() {
        llCollectStatisticBack = findViewById(R.id.ll_collect_statistic_back);
        tabsCj = findViewById(R.id.tabs_cj);
        llCollectStatisticSearch = findViewById(R.id.ll_collect_statistic_search);
        vpViewCj = findViewById(R.id.vp_view_cj);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new DailyFragment().newInstance(2), "全部");
        vpAdapter.addFragment(new DailyFragment().newInstance(1), "已上报");
        vpAdapter.addFragment(new DailyFragment().newInstance(0), "未上报");
        vpViewCj.setAdapter(vpAdapter);
        vpViewCj.setOffscreenPageLimit(3);  //设置3页缓存
        tabsCj.setupWithViewPager(vpViewCj);
    }

    @Override
    public void initListeners() {
        llCollectStatisticBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_collect_statistic_back:
                finish();
                break;
        }
    }
}
