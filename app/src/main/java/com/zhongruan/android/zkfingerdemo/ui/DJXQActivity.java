package com.zhongruan.android.zkfingerdemo.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.ViewPagerAdapter;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.fragments.RZXQFragment;

public class DJXQActivity extends BaseActivity implements View.OnClickListener {
    private String kc, cc;
    private LinearLayout mLlDjjlBack;
    private TabLayout mTabsKw;
    private ViewPager mVpKwView;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_kwxq);
    }

    @Override
    public void initViews() {
        mLlDjjlBack = findViewById(R.id.ll_djjl_back);
        mTabsKw = findViewById(R.id.tabs_kw);
        mVpKwView = findViewById(R.id.vp_kw_view);

        Intent getIntent = getIntent();
        kc = getIntent.getStringExtra("djjlkc");
        cc = getIntent.getStringExtra("djjlcc");
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 0), "全部(" + DbServices.getInstance(this).queryBKKSList(kc, cc).size() + ")");
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 1), "缺考(" + DbServices.getInstance(this).queryBKKSLists(kc, cc, "1").size() + ")");
        vpAdapter.addFragment(new RZXQFragment().newInstance(kc, cc, 3), "未认证(" + DbServices.getInstance(this).queryBKKSLists(kc, cc, "0").size() + ")");
        mVpKwView.setAdapter(vpAdapter);
        mVpKwView.setOffscreenPageLimit(3);  //设置3页缓存
        mTabsKw.setupWithViewPager(mVpKwView);
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
