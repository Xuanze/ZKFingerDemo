package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "bk_ks_temp")
public class Bk_ks_temp {
    @Property(nameInDb = "ksno")
    private String ksno;
    @Property(nameInDb = "xm")
    private String xm;
    @Property(nameInDb = "bmxh")
    private String bmxh;
    @Property(nameInDb = "zkzh")
    private String zkzh;
    @Property(nameInDb = "zjno")
    private String zjno;
    @Property(nameInDb = "xb")
    private String xb;
    @Property(nameInDb = "lxdh")
    private String lxdh;
    @Property(nameInDb = "xjh")
    private String xjh;
    @Property(nameInDb = "sfks")
    private String sfks;
    @Property(nameInDb = "zw")
    private String zw;
    @Property(nameInDb = "kmno")
    private String kmno;
    @Property(nameInDb = "kmmc")
    private String kmmc;
    @Property(nameInDb = "ccno")
    private String ccno;
    @Property(nameInDb = "ccmc")
    private String ccmc;
    @Property(nameInDb = "kssj")
    private String kssj;
    @Property(nameInDb = "jssj")
    private String jssj;
    //    @Property(nameInDb = "zskssj")
//    private String zskssj;
//    @Property(nameInDb = "zsjssj")
//    private String zsjssj;
    @Property(nameInDb = "kcno")
    private String kcno;
    @Property(nameInDb = "kcmc")
    private String kcmc;
    @Property(nameInDb = "kcsx")
    private String kcsx;
    @Property(nameInDb = "kdno")
    private String kdno;
    @Property(nameInDb = "kdmc")
    private String kdmc;
    @Property(nameInDb = "bmd")
    private String bmd;
    @Property(nameInDb = "bmdmc")
    private String bmdmc;
    @Property(nameInDb = "cjbj")
    private String cjbj;



    @Generated(hash = 1785975116)
    public Bk_ks_temp(String ksno, String xm, String bmxh, String zkzh, String zjno,
            String xb, String lxdh, String xjh, String sfks, String zw, String kmno,
            String kmmc, String ccno, String ccmc, String kssj, String jssj,
            String kcno, String kcmc, String kcsx, String kdno, String kdmc,
            String bmd, String bmdmc, String cjbj) {
        this.ksno = ksno;
        this.xm = xm;
        this.bmxh = bmxh;
        this.zkzh = zkzh;
        this.zjno = zjno;
        this.xb = xb;
        this.lxdh = lxdh;
        this.xjh = xjh;
        this.sfks = sfks;
        this.zw = zw;
        this.kmno = kmno;
        this.kmmc = kmmc;
        this.ccno = ccno;
        this.ccmc = ccmc;
        this.kssj = kssj;
        this.jssj = jssj;
        this.kcno = kcno;
        this.kcmc = kcmc;
        this.kcsx = kcsx;
        this.kdno = kdno;
        this.kdmc = kdmc;
        this.bmd = bmd;
        this.bmdmc = bmdmc;
        this.cjbj = cjbj;
    }


    @Generated(hash = 1829043109)
    public Bk_ks_temp() {
    }



    @Override
    public String toString() {
        return ksno + "," +
                xm + ", " +
                lxdh + ", " +
                bmxh + ", " +
                zjno + ", " +
                xb + ", " +
                bmd + ", " +
                bmdmc + ", " +
                cjbj + ", " +
                zw + ", " +
                kmno + ", " +
                kmmc + "," +
                ccno + "," +
                ccmc + "," +
                kssj + "," +
                jssj + "," +
//                zskssj + "," +
//                zsjssj + "," +
                kcno + "," +
                kcmc + "," +
                kdno + "," +
                kdmc + "," +
                bmd + "," +
                bmdmc + "," +
                cjbj;
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


    public String getBmxh() {
        return this.bmxh;
    }


    public void setBmxh(String bmxh) {
        this.bmxh = bmxh;
    }


    public String getZkzh() {
        return this.zkzh;
    }


    public void setZkzh(String zkzh) {
        this.zkzh = zkzh;
    }


    public String getZjno() {
        return this.zjno;
    }


    public void setZjno(String zjno) {
        this.zjno = zjno;
    }


    public String getXb() {
        return this.xb;
    }


    public void setXb(String xb) {
        this.xb = xb;
    }


    public String getLxdh() {
        return this.lxdh;
    }


    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }


    public String getXjh() {
        return this.xjh;
    }


    public void setXjh(String xjh) {
        this.xjh = xjh;
    }


    public String getSfks() {
        return this.sfks;
    }


    public void setSfks(String sfks) {
        this.sfks = sfks;
    }


    public String getZw() {
        return this.zw;
    }


    public void setZw(String zw) {
        this.zw = zw;
    }


    public String getKmno() {
        return this.kmno;
    }


    public void setKmno(String kmno) {
        this.kmno = kmno;
    }


    public String getKmmc() {
        return this.kmmc;
    }


    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }


    public String getCcno() {
        return this.ccno;
    }


    public void setCcno(String ccno) {
        this.ccno = ccno;
    }


    public String getCcmc() {
        return this.ccmc;
    }


    public void setCcmc(String ccmc) {
        this.ccmc = ccmc;
    }


    public String getKssj() {
        return this.kssj;
    }


    public void setKssj(String kssj) {
        this.kssj = kssj;
    }


    public String getJssj() {
        return this.jssj;
    }


    public void setJssj(String jssj) {
        this.jssj = jssj;
    }


//    public String getZskssj() {
//        return this.zskssj;
//    }
//
//
//    public void setZskssj(String zskssj) {
//        this.zskssj = zskssj;
//    }
//
//
//    public String getZsjssj() {
//        return this.zsjssj;
//    }
//
//
//    public void setZsjssj(String zsjssj) {
//        this.zsjssj = zsjssj;
//    }


    public String getKcno() {
        return this.kcno;
    }


    public void setKcno(String kcno) {
        this.kcno = kcno;
    }


    public String getKcmc() {
        return this.kcmc;
    }


    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }


    public String getKcsx() {
        return this.kcsx;
    }


    public void setKcsx(String kcsx) {
        this.kcsx = kcsx;
    }


    public String getKdno() {
        return this.kdno;
    }


    public void setKdno(String kdno) {
        this.kdno = kdno;
    }


    public String getKdmc() {
        return this.kdmc;
    }


    public void setKdmc(String kdmc) {
        this.kdmc = kdmc;
    }


    public String getBmd() {
        return this.bmd;
    }


    public void setBmd(String bmd) {
        this.bmd = bmd;
    }


    public String getBmdmc() {
        return this.bmdmc;
    }


    public void setBmdmc(String bmdmc) {
        this.bmdmc = bmdmc;
    }


    public String getCjbj() {
        return this.cjbj;
    }


    public void setCjbj(String cjbj) {
        this.cjbj = cjbj;
    }


}
