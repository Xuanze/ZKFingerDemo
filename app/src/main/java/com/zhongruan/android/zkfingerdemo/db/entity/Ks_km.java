package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity(nameInDb = "ks_km")
public class Ks_km {
    @Id(autoincrement = true)
    @Property(nameInDb = "kmid")
    private Long kmid;
    @Property(nameInDb = "km_no")
    private String km_no;
    @Property(nameInDb = "km_name")
    private String km_name;

    @Generated(hash = 791388477)
    public Ks_km(Long kmid, String km_no, String km_name) {
        this.kmid = kmid;
        this.km_no = km_no;
        this.km_name = km_name;
    }

    @Generated(hash = 1253281935)
    public Ks_km() {
    }

    public Long getKmid() {
        return this.kmid;
    }

    public void setKmid(Long kmid) {
        this.kmid = kmid;
    }

    public String getKm_no() {
        return this.km_no;
    }

    public void setKm_no(String km_no) {
        this.km_no = km_no;
    }

    public String getKm_name() {
        return this.km_name;
    }

    public void setKm_name(String km_name) {
        this.km_name = km_name;
    }
}
