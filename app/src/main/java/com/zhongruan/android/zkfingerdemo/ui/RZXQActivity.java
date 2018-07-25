package com.zhongruan.android.zkfingerdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.ViewPagerAdapter;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.fragments.DailyFragment;
import com.zhongruan.android.zkfingerdemo.fragments.RZXQFragment;

public class RZXQActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLlDjjlBack;
    private TabLayout mTabsRz;
    private ViewPager mVpRzView;
    private String kc, cc;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_rzxq);
    }

    @Override
    public void initViews() {
        mLlDjjlBack = findViewById(R.id.ll_djjl_back);
        mTabsRz = findViewById(R.id.tabs_rz);
        mVpRzView = findViewById(R.id.vp_rz_view);
        Intent getIntent = getIntent();
        kc = getIntent.getStringExtra("rzjlkc");
        cc = getIntent.getStringExtra("rzjlcc");
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 0), "全部(" + DbServices.getInstance(this).queryBKKSList(kc, cc).size() + ")");
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 1), "通过(" + DbServices.getInstance(this).queryBKKSLists(kc, cc, "1").size() + ")");
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 2), "不通过(" + DbServices.getInstance(this).queryBKKSLists(kc, cc, "3").size() + ")");
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 3), "缺考(" + DbServices.getInstance(this).queryBKKSLists(kc, cc, "2").size() + ")");
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 4), "未认证(" + DbServices.getInstance(this).queryBKKSLists(kc, cc, "0").size() + ")");
        mVpRzView.setAdapter(vpAdapter);
        mVpRzView.setOffscreenPageLimit(3);  //设置3页缓存
        mTabsRz.setupWithViewPager(mVpRzView);
        mLlDjjlBack.setOnClickListener(this);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_djjl_back:
                finish();
                break;
        }
    }
}
