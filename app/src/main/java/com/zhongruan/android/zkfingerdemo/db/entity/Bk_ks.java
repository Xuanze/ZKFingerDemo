package com.zhongruan.android.zkfingerdemo.db.entity;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;


/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "bk_ks")
public class Bk_ks implements Parcelable {
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
    @Property(nameInDb = "ks_ccno")
    private String ks_ccno;
    @Property(nameInDb = "ks_ccmc")
    private String ks_ccmc;
    @Property(nameInDb = "ks_kcno")
    private String ks_kcno;
    @Property(nameInDb = "ks_kcmc")
    private String ks_kcmc;
    @Property(nameInDb = "ks_zwh")
    private String ks_zwh;
    @Property(nameInDb = "ks_xp")
    private String ks_xp;
    @Property(nameInDb = "isRZ")
    private String isRZ;
    @Property(nameInDb = "rzTime")
    private String rzTime;


    protected Bk_ks(Parcel in) {
        if (in.readByte() == 0) {
            ksid = null;
        } else {
            ksid = in.readLong();
        }
        ks_ksno = in.readString();
        ks_xm = in.readString();
        ks_zjno = in.readString();
        ks_xb = in.readString();
        ks_ccno = in.readString();
        ks_ccmc = in.readString();
        ks_kcno = in.readString();
        ks_kcmc = in.readString();
        ks_zwh = in.readString();
        ks_xp = in.readString();
        isRZ = in.readString();
        rzTime = in.readString();
    }

    @Generated(hash = 1941532446)
    public Bk_ks(Long ksid, String ks_ksno, String ks_xm, String ks_zjno,
            String ks_xb, String ks_ccno, String ks_ccmc, String ks_kcno,
            String ks_kcmc, String ks_zwh, String ks_xp, String isRZ,
            String rzTime) {
        this.ksid = ksid;
        this.ks_ksno = ks_ksno;
        this.ks_xm = ks_xm;
        this.ks_zjno = ks_zjno;
        this.ks_xb = ks_xb;
        this.ks_ccno = ks_ccno;
        this.ks_ccmc = ks_ccmc;
        this.ks_kcno = ks_kcno;
        this.ks_kcmc = ks_kcmc;
        this.ks_zwh = ks_zwh;
        this.ks_xp = ks_xp;
        this.isRZ = isRZ;
        this.rzTime = rzTime;
    }

    @Generated(hash = 1002290122)
    public Bk_ks() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ksid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ksid);
        }
        dest.writeString(ks_ksno);
        dest.writeString(ks_xm);
        dest.writeString(ks_zjno);
        dest.writeString(ks_xb);
        dest.writeString(ks_ccno);
        dest.writeString(ks_ccmc);
        dest.writeString(ks_kcno);
        dest.writeString(ks_kcmc);
        dest.writeString(ks_zwh);
        dest.writeString(ks_xp);
        dest.writeString(isRZ);
        dest.writeString(rzTime);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getKs_ccno() {
        return this.ks_ccno;
    }

    public void setKs_ccno(String ks_ccno) {
        this.ks_ccno = ks_ccno;
    }

    public String getKs_ccmc() {
        return this.ks_ccmc;
    }

    public void setKs_ccmc(String ks_ccmc) {
        this.ks_ccmc = ks_ccmc;
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

    public String getIsRZ() {
        return this.isRZ;
    }

    public void setIsRZ(String isRZ) {
        this.isRZ = isRZ;
    }

    public String getRzTime() {
        return this.rzTime;
    }

    public void setRzTime(String rzTime) {
        this.rzTime = rzTime;
    }

    public static final Creator<Bk_ks> CREATOR = new Creator<Bk_ks>() {
        @Override
        public Bk_ks createFromParcel(Parcel in) {
            return new Bk_ks(in);
        }

        @Override
        public Bk_ks[] newArray(int size) {
            return new Bk_ks[size];
        }
    };
}
