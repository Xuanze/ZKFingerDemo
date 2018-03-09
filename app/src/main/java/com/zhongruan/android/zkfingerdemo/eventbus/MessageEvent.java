package com.zhongruan.android.zkfingerdemo.eventbus;

/**
 * Created by LHJ on 2018/2/6.
 */

public class MessageEvent {
    private String kcmc;
    private String ccmc;

    public MessageEvent(String kcmc, String ccmc) {
        this.kcmc = kcmc;
        this.ccmc = ccmc;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getCcmc() {
        return ccmc;
    }

    public void setCcmc(String ccmc) {
        this.ccmc = ccmc;
    }
}
