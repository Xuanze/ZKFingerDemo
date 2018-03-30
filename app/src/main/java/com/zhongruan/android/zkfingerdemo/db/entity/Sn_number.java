package com.zhongruan.android.zkfingerdemo.db.entity;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "sn_number")
public class Sn_number implements Parcelable {
    @Id(autoincrement = true)
    @Property(nameInDb = "snid")
    private Long snid;
    @Property(nameInDb = "sn")
    private String sn;

    @Generated(hash = 1730297861)
    public Sn_number(Long snid, String sn) {
        this.snid = snid;
        this.sn = sn;
    }

    @Generated(hash = 1850023535)
    public Sn_number() {
    }

    public Long getSnid() {
        return snid;
    }

    public void setSnid(Long snid) {
        this.snid = snid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
