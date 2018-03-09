package com.zhongruan.android.zkfingerdemo.idcardengine;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class IDCardData implements Parcelable {
    public static final Creator<IDCardData> CREATOR;
    private String address;//地址
    private String birth;//生日
    private String cardNo;//编号
    private Bitmap map;//照片
    private String mz;//民族
    private String qfjg;//签发机关
    private byte[] sfzLeftzw;
    private byte[] sfzRightzw;
    private String sfzh;//身份证号码
    private String xb;//性别
    private String xm;//姓名
    private String yxjsrq;//有效结束日期
    private String yxksrq;//有效开始日期

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getXm() {
        return this.xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return this.xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getMz() {
        return this.mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getBirth() {
        return this.birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSfzh() {
        return this.sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getQfjg() {
        return this.qfjg;
    }

    public void setQfjg(String qfjg) {
        this.qfjg = qfjg;
    }

    public String getYxksrq() {
        return this.yxksrq;
    }

    public void setYxksrq(String yxksrq) {
        this.yxksrq = yxksrq;
    }

    public String getYxjsrq() {
        return this.yxjsrq;
    }

    public void setYxjsrq(String yxjsrq) {
        this.yxjsrq = yxjsrq;
    }

    public Bitmap getMap() {
        return this.map;
    }

    public void setMap(Bitmap map) {
        this.map = map;
    }

    public byte[] getSfzLeftzw() {
        return this.sfzLeftzw;
    }

    public void setSfzLeftzw(byte[] sfzLeftzw) {
        this.sfzLeftzw = sfzLeftzw;
    }

    public byte[] getSfzRightzw() {
        return this.sfzRightzw;
    }

    public void setSfzRightzw(byte[] sfzRightzw) {
        this.sfzRightzw = sfzRightzw;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardNo);
        dest.writeString(this.xm);
        dest.writeString(this.xb);
        dest.writeString(this.mz);
        dest.writeString(this.birth);
        dest.writeString(this.address);
        dest.writeString(this.sfzh);
        dest.writeString(this.qfjg);
        dest.writeString(this.yxksrq);
        dest.writeString(this.yxjsrq);
        dest.writeParcelable(this.map, 1);
        dest.writeByteArray(this.sfzLeftzw);
        dest.writeByteArray(this.sfzRightzw);
    }

    static {
        CREATOR = new Creator<IDCardData>() {
            public IDCardData createFromParcel(Parcel source) {
                return IDCardData.IdCardDataBean(source);
            }

            public IDCardData[] newArray(int size) {
                return new IDCardData[size];
            }
        };
    }

    public static IDCardData IdCardDataBean(Parcel source) {
        IDCardData cardData = new IDCardData();
        cardData.setCardNo(source.readString());
        cardData.setXm(source.readString());
        cardData.setXb(source.readString());
        cardData.setMz(source.readString());
        cardData.setBirth(source.readString());
        cardData.setAddress(source.readString());
        cardData.setSfzh(source.readString());
        cardData.setQfjg(source.readString());
        cardData.setYxjsrq(source.readString());
        cardData.setYxksrq(source.readString());
        cardData.setMap((Bitmap) source.readParcelable(Bitmap.class.getClassLoader()));
        cardData.setSfzLeftzw(source.createByteArray());
        cardData.setSfzRightzw(source.createByteArray());
        return cardData;
    }
}
