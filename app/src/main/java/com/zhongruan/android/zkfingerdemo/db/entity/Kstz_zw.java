package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "kstz_zw")
public class Kstz_zw {
    @Property(nameInDb = "ksno")
    private String ksno;
    @Property(nameInDb = "xm")
    private String xm;
    @Property(nameInDb = "zjno")
    private String zjno;
    @Property(nameInDb = "zw_position")
    private String zw_position;
    @Property(nameInDb = "zw_feature")
    private String zw_feature;
    @Property(nameInDb = "a")
    private String a;
    @Property(nameInDb = "b")
    private String b;

    @Generated(hash = 431453780)
    public Kstz_zw(String ksno, String xm, String zjno, String zw_position,
                   String zw_feature, String a, String b) {
        this.ksno = ksno;
        this.xm = xm;
        this.zjno = zjno;
        this.zw_position = zw_position;
        this.zw_feature = zw_feature;
        this.a = a;
        this.b = b;
    }

    @Generated(hash = 1493218490)
    public Kstz_zw() {
    }

    public String getKsno() {
        return this.ksno;
    }

    public void setKsno(String ksno) {
        this.ksno = ksno;
    }

    public String getXm() {
        return this.xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZjno() {
        return this.zjno;
    }

    public void setZjno(String zjno) {
        this.zjno = zjno;
    }

    public String getZw_position() {
        return this.zw_position;
    }

    public void setZw_position(String zw_position) {
        this.zw_position = zw_position;
    }

    public String getZw_feature() {
        return this.zw_feature;
    }

    public void setZw_feature(String zw_feature) {
        this.zw_feature = zw_feature;
    }

    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return this.b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
