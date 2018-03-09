package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "rz_ks_zw")
public class Rz_ks_zw {
    @Id(autoincrement = true)
    @Property(nameInDb = "ksid")
    private Long ksid;
    @Property(nameInDb = "ks_ksno")
    private String ks_ksno;
    @Property(nameInDb = "ks_xm")
    private String ks_xm;
    @Property(nameInDb = "ks_zjno")
    private String ks_zjno;
    @Property(nameInDb = "ks_xb")
    private String ks_xb;
    @Property(nameInDb = "ks_kcno")
    private String ks_kcno;
    @Property(nameInDb = "ks_kcmc")
    private String ks_kcmc;
    @Property(nameInDb = "ks_zwh")
    private String ks_zwh;
    @Property(nameInDb = "ks_xp")
    private String ks_xp;
    @Property(nameInDb = "zw_feature")
    private String zw_feature;
    @Property(nameInDb = "zw_bs")
    private String zw_bs;

    @Generated(hash = 1901178536)
    public Rz_ks_zw(Long ksid, String ks_ksno, String ks_xm, String ks_zjno,
                    String ks_xb, String ks_kcno, String ks_kcmc, String ks_zwh,
                    String ks_xp, String zw_feature, String zw_bs) {
        this.ksid = ksid;
        this.ks_ksno = ks_ksno;
        this.ks_xm = ks_xm;
        this.ks_zjno = ks_zjno;
        this.ks_xb = ks_xb;
        this.ks_kcno = ks_kcno;
        this.ks_kcmc = ks_kcmc;
        this.ks_zwh = ks_zwh;
        this.ks_xp = ks_xp;
        this.zw_feature = zw_feature;
        this.zw_bs = zw_bs;
    }

    @Generated(hash = 1668583310)
    public Rz_ks_zw() {
    }

    public Long getKsid() {
        return this.ksid;
    }

    public void setKsid(Long ksid) {
        this.ksid = ksid;
    }

    public String getKs_ksno() {
        return this.ks_ksno;
    }

    public void setKs_ksno(String ks_ksno) {
        this.ks_ksno = ks_ksno;
    }

    public String getKs_xm() {
        return this.ks_xm;
    }

    public void setKs_xm(String ks_xm) {
        this.ks_xm = ks_xm;
    }

    public String getKs_zjno() {
        return this.ks_zjno;
    }

    public void setKs_zjno(String ks_zjno) {
        this.ks_zjno = ks_zjno;
    }

    public String getKs_xb() {
        return this.ks_xb;
    }

    public void setKs_xb(String ks_xb) {
        this.ks_xb = ks_xb;
    }

    public String getKs_kcno() {
        return this.ks_kcno;
    }

    public void setKs_kcno(String ks_kcno) {
        this.ks_kcno = ks_kcno;
    }

    public String getKs_kcmc() {
        return this.ks_kcmc;
    }

    public void setKs_kcmc(String ks_kcmc) {
        this.ks_kcmc = ks_kcmc;
    }

    public String getKs_zwh() {
        return this.ks_zwh;
    }

    public void setKs_zwh(String ks_zwh) {
        this.ks_zwh = ks_zwh;
    }

    public String getKs_xp() {
        return this.ks_xp;
    }

    public void setKs_xp(String ks_xp) {
        this.ks_xp = ks_xp;
    }

    public String getZw_feature() {
        return this.zw_feature;
    }

    public void setZw_feature(String zw_feature) {
        this.zw_feature = zw_feature;
    }

    public String getZw_bs() {
        return this.zw_bs;
    }

    public void setZw_bs(String zw_bs) {
        this.zw_bs = zw_bs;
    }
}
