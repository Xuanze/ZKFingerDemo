package com.zhongruan.android.zkfingerdemo.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.ViewPagerAdapter;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.fragments.DJJLFragment;
import com.zhongruan.android.zkfingerdemo.fragments.RZJLFragment;

public class RZDJActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLlRzjlBack;
    private TabLayout mTabsRzDj;
    private ViewPager mVpViewRzdj;
    private RZJLFragment RZJLFragment;
    private DJJLFragment djjlFragment;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_rzdjjl);
    }

    @Override
    public void initViews() {
        mLlRzjlBack = findViewById(R.id.ll_rzjl_back);
        mTabsRzDj = findViewById(R.id.tabs_rz_dj);
        mVpViewRzdj = findViewById(R.id.vp_view_rzdj);
        RZJLFragment = new RZJLFragment();
        djjlFragment = new DJJLFragment();
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(RZJLFragment, "认证记录");
        vpAdapter.addFragment(djjlFragment, "登记记录");
        mVpViewRzdj.setAdapter(vpAdapter);
        mVpViewRzdj.setOffscreenPageLimit(2);  //设置3页缓存
        mTabsRzDj.setupWithViewPager(mVpViewRzdj);
    }

    @Override
    public void initListeners() {
        mLlRzjlBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_rzjl_back:
                finish();
                break;
        }
    }
}
