package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/9/25.
 */
@Entity(nameInDb = "sfrz_rzzt")
public class Sfrz_rzzt {
    @Id(autoincrement = true)
    @Property(nameInDb = "rzztid")
    private Long rzztid;
    @Property(nameInDb = "rzzt_no")
    private String rzzt_no;
    @Property(nameInDb = "rzzt_name")
    private String rzzt_name;

    @Generated(hash = 314482364)
    public Sfrz_rzzt(Long rzztid, String rzzt_no, String rzzt_name) {
        this.rzztid = rzztid;
        this.rzzt_no = rzzt_no;
        this.rzzt_name = rzzt_name;
    }

    @Generated(hash = 306746952)
    public Sfrz_rzzt() {
    }

    public Long getRzztid() {
        return this.rzztid;
    }

    public void setRzztid(Long rzztid) {
        this.rzztid = rzztid;
    }

    public String getRzzt_no() {
        return this.rzzt_no;
    }

    public void setRzzt_no(String rzzt_no) {
        this.rzzt_no = rzzt_no;
    }

    public String getRzzt_name() {
        return this.rzzt_name;
    }

    public void setRzzt_name(String rzzt_name) {
        this.rzzt_name = rzzt_name;
    }

}
