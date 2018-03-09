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
@Entity(nameInDb = "bk_ks_cjxx")
public class Bk_ks_cjxx implements Parcelable {

    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    @Property(nameInDb = "sfz_cardNo")
    private String sfz_cardNo;//编号
    @Property(nameInDb = "sfz_xm")
    private String sfz_xm;//姓名
    @Property(nameInDb = "sfz_xb")
    private String sfz_xb;//性别
    @Property(nameInDb = "sfz_mz")
    private String sfz_mz;//民族
    @Property(nameInDb = "sfz_birth")
    private String sfz_birth;//生日
    @Property(nameInDb = "sfz_address")
    private String sfz_address;//地址
    @Property(nameInDb = "sfz_sfzh")
    private String sfz_sfzh;//身份证号码
    @Property(nameInDb = "sfz_picpath")
    private String sfz_picpath;//照片路径
    @Property(nameInDb = "sfz_qfjg")
    private String sfz_qfjg;//签发机关
    @Property(nameInDb = "sfz_yxjsrq")
    private String sfz_yxjsrq;//有效结束日期
    @Property(nameInDb = "sfz_yxksrq")
    private String sfz_yxksrq;//有效开始日期
    @Property(nameInDb = "zw_picpath")
    private String zw_picpath;//指纹照片路径
    @Property(nameInDb = "zw_features")
    private String zw_features;//指纹特征
    @Property(nameInDb = "zw_quality")
    private int zw_quality;//指纹图像质量数值
    @Property(nameInDb = "rl_picpath")
    private String rl_picpath;//人脸照片路径
    @Property(nameInDb = "cjTime")
    private String cjTime;//采集时间
    @Property(nameInDb = "isSbzt")
    private int isSbzt;//是否上报成功

    @Generated(hash = 1815239595)
    public Bk_ks_cjxx(Long id, String sfz_cardNo, String sfz_xm, String sfz_xb,
                      String sfz_mz, String sfz_birth, String sfz_address, String sfz_sfzh,
                      String sfz_picpath, String sfz_qfjg, String sfz_yxjsrq,
                      String sfz_yxksrq, String zw_picpath, String zw_features,
                      int zw_quality, String rl_picpath, String cjTime, int isSbzt) {
        this.id = id;
        this.sfz_cardNo = sfz_cardNo;
        this.sfz_xm = sfz_xm;
        this.sfz_xb = sfz_xb;
        this.sfz_mz = sfz_mz;
        this.sfz_birth = sfz_birth;
        this.sfz_address = sfz_address;
        this.sfz_sfzh = sfz_sfzh;
        this.sfz_picpath = sfz_picpath;
        this.sfz_qfjg = sfz_qfjg;
        this.sfz_yxjsrq = sfz_yxjsrq;
        this.sfz_yxksrq = sfz_yxksrq;
        this.zw_picpath = zw_picpath;
        this.zw_features = zw_features;
        this.zw_quality = zw_quality;
        this.rl_picpath = rl_picpath;
        this.cjTime = cjTime;
        this.isSbzt = isSbzt;
    }

    @Generated(hash = 1925650841)
    public Bk_ks_cjxx() {
    }


    @Override
    public String toString() {
        return sfz_cardNo + "," +
                sfz_xm + "," +
                sfz_xb + "," +
                sfz_mz + "," +
                sfz_birth + "," +
                sfz_address + "," +
                sfz_sfzh + "," +
                sfz_picpath + "," +
                sfz_qfjg + "," +
                sfz_yxjsrq + "," +
                sfz_yxksrq + "," +
                zw_picpath + "," +
                zw_features + "," +
                zw_quality +
                rl_picpath + "," +
                cjTime;
    }

    protected Bk_ks_cjxx(Parcel in) {
        sfz_cardNo = in.readString();
        sfz_xm = in.readString();
        sfz_xb = in.readString();
        sfz_mz = in.readString();
        sfz_birth = in.readString();
        sfz_address = in.readString();
        sfz_sfzh = in.readString();
        sfz_picpath = in.readString();
        sfz_qfjg = in.readString();
        sfz_yxjsrq = in.readString();
        sfz_yxksrq = in.readString();
        zw_picpath = in.readString();
        zw_features = in.readString();
        zw_quality = in.readInt();
        rl_picpath = in.readString();
        cjTime = in.readString();
        isSbzt = in.readInt();
    }

    public static final Creator<Bk_ks_cjxx> CREATOR = new Creator<Bk_ks_cjxx>() {
        @Override
        public Bk_ks_cjxx createFromParcel(Parcel in) {
            return new Bk_ks_cjxx(in);
        }

        @Override
        public Bk_ks_cjxx[] newArray(int size) {
            return new Bk_ks_cjxx[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sfz_cardNo);
        parcel.writeString(sfz_xm);
        parcel.writeString(sfz_xb);
        parcel.writeString(sfz_mz);
        parcel.writeString(sfz_birth);
        parcel.writeString(sfz_address);
        parcel.writeString(sfz_sfzh);
        parcel.writeString(sfz_picpath);
        parcel.writeString(sfz_qfjg);
        parcel.writeString(sfz_yxjsrq);
        parcel.writeString(sfz_yxksrq);
        parcel.writeString(zw_picpath);
        parcel.writeString(zw_features);
        parcel.writeInt(zw_quality);
        parcel.writeString(rl_picpath);
        parcel.writeString(cjTime);
        parcel.writeInt(isSbzt);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSfz_cardNo() {
        return this.sfz_cardNo;
    }

    public void setSfz_cardNo(String sfz_cardNo) {
        this.sfz_cardNo = sfz_cardNo;
    }

    public String getSfz_xm() {
        return this.sfz_xm;
    }

    public void setSfz_xm(String sfz_xm) {
        this.sfz_xm = sfz_xm;
    }

    public String getSfz_xb() {
        return this.sfz_xb;
    }

    public void setSfz_xb(String sfz_xb) {
        this.sfz_xb = sfz_xb;
    }

    public String getSfz_mz() {
        return this.sfz_mz;
    }

    public void setSfz_mz(String sfz_mz) {
        this.sfz_mz = sfz_mz;
    }

    public String getSfz_birth() {
        return this.sfz_birth;
    }

    public void setSfz_birth(String sfz_birth) {
        this.sfz_birth = sfz_birth;
    }

    public String getSfz_address() {
        return this.sfz_address;
    }

    public void setSfz_address(String sfz_address) {
        this.sfz_address = sfz_address;
    }

    public String getSfz_sfzh() {
        return this.sfz_sfzh;
    }

    public void setSfz_sfzh(String sfz_sfzh) {
        this.sfz_sfzh = sfz_sfzh;
    }

    public String getSfz_picpath() {
        return this.sfz_picpath;
    }

    public void setSfz_picpath(String sfz_picpath) {
        this.sfz_picpath = sfz_picpath;
    }

    public String getSfz_qfjg() {
        return this.sfz_qfjg;
    }

    public void setSfz_qfjg(String sfz_qfjg) {
        this.sfz_qfjg = sfz_qfjg;
    }

    public String getSfz_yxjsrq() {
        return this.sfz_yxjsrq;
    }

    public void setSfz_yxjsrq(String sfz_yxjsrq) {
        this.sfz_yxjsrq = sfz_yxjsrq;
    }

    public String getSfz_yxksrq() {
        return this.sfz_yxksrq;
    }

    public void setSfz_yxksrq(String sfz_yxksrq) {
        this.sfz_yxksrq = sfz_yxksrq;
    }

    public String getZw_picpath() {
        return this.zw_picpath;
    }

    public void setZw_picpath(String zw_picpath) {
        this.zw_picpath = zw_picpath;
    }

    public String getZw_features() {
        return this.zw_features;
    }

    public void setZw_features(String zw_features) {
        this.zw_features = zw_features;
    }

    public int getZw_quality() {
        return this.zw_quality;
    }

    public void setZw_quality(int zw_quality) {
        this.zw_quality = zw_quality;
    }

    public String getRl_picpath() {
        return this.rl_picpath;
    }

    public void setRl_picpath(String rl_picpath) {
        this.rl_picpath = rl_picpath;
    }

    public String getCjTime() {
        return this.cjTime;
    }

    public void setCjTime(String cjTime) {
        this.cjTime = cjTime;
    }

    public int getIsSbzt() {
        return this.isSbzt;
    }

    public void setIsSbzt(int isSbzt) {
        this.isSbzt = isSbzt;
    }
}
