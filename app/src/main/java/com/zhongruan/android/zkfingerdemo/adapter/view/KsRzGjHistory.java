package com.zhongruan.android.zkfingerdemo.adapter.view;

import java.util.List;

public class KsRzGjHistory {

    private String ks_rzzt;
    private String ks_rztime;
    private List<KsRzjlView> viewList;

    public String getKs_rzzt() {
        return ks_rzzt;
    }

    public void setKs_rzzt(String ks_rzzt) {
        this.ks_rzzt = ks_rzzt;
    }

    public String getKs_rztime() {
        return ks_rztime;
    }

    public void setKs_rztime(String ks_rztime) {
        this.ks_rztime = ks_rztime;
    }

    public List<KsRzjlView> getViewList() {
        return viewList;
    }

    public void setViewList(List<KsRzjlView> viewList) {
        this.viewList = viewList;
    }
}
