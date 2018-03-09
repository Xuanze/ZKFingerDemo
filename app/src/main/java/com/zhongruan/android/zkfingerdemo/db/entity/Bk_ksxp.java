package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "bk_ksxp")
public class Bk_ksxp {
    @Property(nameInDb = "ksno")
    private String ksno;
    @Property(nameInDb = "xm")
    private String xm;
    @Property(nameInDb = "zjno")
    private String zjno;
    @Property(nameInDb = "xp_jym")
    private String xp_jym;
    @Property(nameInDb = "xp_pic")
    private String xp_pic;

    @Generated(hash = 1601054551)
    public Bk_ksxp(String ksno, String xm, String zjno, String xp_jym,
                   String xp_pic) {
        this.ksno = ksno;
        this.xm = xm;
        this.zjno = zjno;
        this.xp_jym = xp_jym;
        this.xp_pic = xp_pic;
    }

    @Generated(hash = 1937525692)
    public Bk_ksxp() {
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

    public String getXp_jym() {
        return this.xp_jym;
    }

    public void setXp_jym(String xp_jym) {
        this.xp_jym = xp_jym;
    }

    public String getXp_pic() {
        return this.xp_pic;
    }

    public void setXp_pic(String xp_pic) {
        this.xp_pic = xp_pic;
    }
}
