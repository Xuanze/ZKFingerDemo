package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "ks_kd")
public class Ks_kd {


    @Id(autoincrement = true)
    @Property(nameInDb = "kdid")
    private Long kdid;
    @Property(nameInDb = "kd_no")
    private String kd_no;
    @Property(nameInDb = "kd_name")
    private String kd_name;
    @Generated(hash = 653892138)
    public Ks_kd(Long kdid, String kd_no, String kd_name) {
        this.kdid = kdid;
        this.kd_no = kd_no;
        this.kd_name = kd_name;
    }
    @Generated(hash = 1240681687)
    public Ks_kd() {
    }
    public Long getKdid() {
        return this.kdid;
    }
    public void setKdid(Long kdid) {
        this.kdid = kdid;
    }
    public String getKd_no() {
        return this.kd_no;
    }
    public void setKd_no(String kd_no) {
        this.kd_no = kd_no;
    }
    public String getKd_name() {
        return this.kd_name;
    }
    public void setKd_name(String kd_name) {
        this.kd_name = kd_name;
    }


}
