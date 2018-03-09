package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.UUID;

/**
 * Created by Administrator on 2017/9/25.
 */
@Entity(nameInDb = "sfrz_rzjg")
public class Sfrz_rzjg {
    @Id(autoincrement = true)
    @Property(nameInDb = "jgid")
    private Long jgid;

    @Property(nameInDb = "rzjgid")
    private String rzjgid;

    @Property(nameInDb = "rzjg_ztid")
    private String rzjg_ztid;

    @Property(nameInDb = "rzjg_ksno")
    private String rzjg_ksno;

    @Property(nameInDb = "rzjg_kmno")
    private String rzjg_kmno;

    @Property(nameInDb = "rzjg_kdno")
    private String rzjg_kdno;

    @Property(nameInDb = "rzjg_kcno")
    private String rzjg_kcno;

    @Property(nameInDb = "rzjg_zwh")
    private String rzjg_zwh;

    @Property(nameInDb = "rzjg_device")
    private String rzjg_device;

    @Property(nameInDb = "rzjg_time")
    private String rzjg_time;

    @Property(nameInDb = "rzjg_a")
    private String rzjg_a;

    @Property(nameInDb = "rzjg_b")
    private String rzjg_b;

    @Property(nameInDb = "rzjg_sb")
    private String rzjg_sb;

    public Sfrz_rzjg() {
        this.rzjgid = UUID.randomUUID().toString();
    }

    @Generated(hash = 1144467222)
    public Sfrz_rzjg(Long jgid, String rzjgid, String rzjg_ztid, String rzjg_ksno, String rzjg_kmno, String rzjg_kdno, String rzjg_kcno, String rzjg_zwh, String rzjg_device, String rzjg_time,
                     String rzjg_a, String rzjg_b, String rzjg_sb) {
        this.jgid = jgid;
        this.rzjgid = rzjgid;
        this.rzjg_ztid = rzjg_ztid;
        this.rzjg_ksno = rzjg_ksno;
        this.rzjg_kmno = rzjg_kmno;
        this.rzjg_kdno = rzjg_kdno;
        this.rzjg_kcno = rzjg_kcno;
        this.rzjg_zwh = rzjg_zwh;
        this.rzjg_device = rzjg_device;
        this.rzjg_time = rzjg_time;
        this.rzjg_a = rzjg_a;
        this.rzjg_b = rzjg_b;
        this.rzjg_sb = rzjg_sb;
    }

    @Override
    public String toString() {
        return rzjg_ztid + "," +
                rzjg_ksno + "," +
                rzjg_kmno + "," +
                rzjg_kdno + "," +
                rzjg_kcno + "," +
                rzjg_zwh + "," +
                rzjg_device + "," +
                rzjg_time + "," +
                rzjgid + "," +
                rzjg_a + "," +
                rzjg_b;
    }

    public Long getJgid() {
        return this.jgid;
    }

    public void setJgid(Long jgid) {
        this.jgid = jgid;
    }

    public String getRzjgid() {
        return this.rzjgid;
    }

    public void setRzjgid(String rzjgid) {
        this.rzjgid = rzjgid;
    }

    public String getRzjg_ztid() {
        return this.rzjg_ztid;
    }

    public void setRzjg_ztid(String rzjg_ztid) {
        this.rzjg_ztid = rzjg_ztid;
    }

    public String getRzjg_ksno() {
        return this.rzjg_ksno;
    }

    public void setRzjg_ksno(String rzjg_ksno) {
        this.rzjg_ksno = rzjg_ksno;
    }

    public String getRzjg_kmno() {
        return this.rzjg_kmno;
    }

    public void setRzjg_kmno(String rzjg_kmno) {
        this.rzjg_kmno = rzjg_kmno;
    }

    public String getRzjg_kdno() {
        return this.rzjg_kdno;
    }

    public void setRzjg_kdno(String rzjg_kdno) {
        this.rzjg_kdno = rzjg_kdno;
    }

    public String getRzjg_kcno() {
        return this.rzjg_kcno;
    }

    public void setRzjg_kcno(String rzjg_kcno) {
        this.rzjg_kcno = rzjg_kcno;
    }

    public String getRzjg_zwh() {
        return this.rzjg_zwh;
    }

    public void setRzjg_zwh(String rzjg_zwh) {
        this.rzjg_zwh = rzjg_zwh;
    }

    public String getRzjg_device() {
        return this.rzjg_device;
    }

    public void setRzjg_device(String rzjg_device) {
        this.rzjg_device = rzjg_device;
    }

    public String getRzjg_time() {
        return this.rzjg_time;
    }

    public void setRzjg_time(String rzjg_time) {
        this.rzjg_time = rzjg_time;
    }

    public String getRzjg_a() {
        return this.rzjg_a;
    }

    public void setRzjg_a(String rzjg_a) {
        this.rzjg_a = rzjg_a;
    }

    public String getRzjg_b() {
        return this.rzjg_b;
    }

    public void setRzjg_b(String rzjg_b) {
        this.rzjg_b = rzjg_b;
    }

    public String getRzjg_sb() {
        return this.rzjg_sb;
    }

    public void setRzjg_sb(String rzjg_sb) {
        this.rzjg_sb = rzjg_sb;
    }
}
