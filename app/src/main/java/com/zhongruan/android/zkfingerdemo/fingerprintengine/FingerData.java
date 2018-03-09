package com.zhongruan.android.zkfingerdemo.fingerprintengine;


import android.os.Parcel;
import android.os.Parcelable;

public class FingerData implements Parcelable {
    public static final Creator<FingerData> CREATOR;
    private byte[] fingerFeatures;//指纹图片特征
    private byte[] fingerImage;//指纹图像转化为BMP图像字节序列
    private int height;
    private int quality;//指纹图像质量数值
    private int width;

    public byte[] getFingerImage() {
        return this.fingerImage;
    }

    public void setFingerImage(byte[] fingerImage) {
        this.fingerImage = fingerImage;
    }

    public byte[] getFingerFeatures() {
        return this.fingerFeatures;
    }

    public void setFingerFeatures(byte[] fingerFeatures) {
        this.fingerFeatures = fingerFeatures;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quality);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeByteArray(this.fingerImage);
        dest.writeByteArray(this.fingerFeatures);
    }

    static {
        CREATOR = new Creator<FingerData>() {
            public FingerData createFromParcel(Parcel source) {
                return new FingerData(null);
            }

            public FingerData[] newArray(int size) {
                return new FingerData[size];
            }
        };
    }

    public FingerData() {
    }

    private FingerData(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.quality = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        this.fingerImage = in.createByteArray();
        this.fingerFeatures = in.createByteArray();
    }
}
