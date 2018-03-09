package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "ks_cc")
public class Ks_cc {
    @Id(autoincrement = true)
    @Property(nameInDb = "ccid")
    private Long ccid;
    @Property(nameInDb = "cc_no")
    private String cc_no;
    @Property(nameInDb = "cc_name")
    private String cc_name;
    @Property(nameInDb = "km_no")
    private String km_no;
    @Property(nameInDb = "km_name")
    private String km_name;
    @Property(nameInDb = "cc_kssj")
    private String cc_kssj;
    @Property(nameInDb = "cc_jssj")
    private String cc_jssj;
    @Property(nameInDb = "cc_extract")
    private String cc_extract;


    @Generated(hash = 1715350612)
    public Ks_cc(Long ccid, String cc_no, String cc_name, String km_no,
                 String km_name, String cc_kssj, String cc_jssj, String cc_extract) {
        this.ccid = ccid;
        this.cc_no = cc_no;
        this.cc_name = cc_name;
        this.km_no = km_no;
        this.km_name = km_name;
        this.cc_kssj = cc_kssj;
        this.cc_jssj = cc_jssj;
        this.cc_extract = cc_extract;
    }

    @Generated(hash = 1694416469)
    public Ks_cc() {
    }


    public Long getCcid() {
        return this.ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public String getCc_no() {
        return this.cc_no;
    }

    public void setCc_no(String cc_no) {
        this.cc_no = cc_no;
    }

    public String getCc_name() {
        return this.cc_name;
    }

    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }

    public String getKm_no() {
        return this.km_no;
    }

    public void setKm_no(String km_no) {
        this.km_no = km_no;
    }

    public String getKm_name() {
        return this.km_name;
    }

    public void setKm_name(String km_name) {
        this.km_name = km_name;
    }

    public String getCc_kssj() {
        return this.cc_kssj;
    }

    public void setCc_kssj(String cc_kssj) {
        this.cc_kssj = cc_kssj;
    }

    public String getCc_jssj() {
        return this.cc_jssj;
    }

    public void setCc_jssj(String cc_jssj) {
        this.cc_jssj = cc_jssj;
    }

    public String getCc_extract() {
        return this.cc_extract;
    }

    public void setCc_extract(String cc_extract) {
        this.cc_extract = cc_extract;
    }
}
