package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "ks_kc")
public class Ks_kc {
    @Id(autoincrement = true)
    @Property(nameInDb = "kcid")
    private Long kcid;
    @Property(nameInDb = "kc_no")
    private String kc_no;
    @Property(nameInDb = "kc_name")
    private String kc_name;
    @Property(nameInDb = "kc_extract")
    private String kc_extract;

    @Generated(hash = 1510699016)
    public Ks_kc(Long kcid, String kc_no, String kc_name, String kc_extract) {
        this.kcid = kcid;
        this.kc_no = kc_no;
        this.kc_name = kc_name;
        this.kc_extract = kc_extract;
    }

    @Generated(hash = 923118429)
    public Ks_kc() {
    }

    public Long getKcid() {
        return this.kcid;
    }

    public void setKcid(Long kcid) {
        this.kcid = kcid;
    }

    public String getKc_no() {
        return this.kc_no;
    }

    public void setKc_no(String kc_no) {
        this.kc_no = kc_no;
    }

    public String getKc_name() {
        return this.kc_name;
    }

    public void setKc_name(String kc_name) {
        this.kc_name = kc_name;
    }

    public String getKc_extract() {
        return this.kc_extract;
    }

    public void setKc_extract(String kc_extract) {
        this.kc_extract = kc_extract;
    }
}
