package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.UUID;

/**
 * Created by Administrator on 2017/9/25.
 */
@Entity(nameInDb = "sfrz_rzjl")
public class Sfrz_rzjl {
    @Id(autoincrement = true)
    @Property(nameInDb = "jlid")
    private Long jlid;
    @Property(nameInDb = "rzjlid")
    private String rzjlid;
    @Property(nameInDb = "rzjl_rzfsno")
    private String rzjl_rzfsno;
    @Property(nameInDb = "rzjl_ksno")
    private String rzjl_ksno;
    @Property(nameInDb = "rzjl_kmbh")
    private String rzjl_kmbh;
    @Property(nameInDb = "rzjl_kdbh")
    private String rzjl_kdbh;
    @Property(nameInDb = "rzjl_kcbh")
    private String rzjl_kcbh;
    @Property(nameInDb = "rzjl_zwh")
    private String rzjl_zwh;
    @Property(nameInDb = "rzjl_device")
    private String rzjl_device;
    @Property(nameInDb = "rzjl_verify_result")
    private String rzjl_verify_result;
    @Property(nameInDb = "rzjl_time")
    private String rzjl_time;
    @Property(nameInDb = "rzjl_Features")
    private String rzjl_Features;
    @Property(nameInDb = "rzjl_pith")
    private String rzjl_pith;
    @Property(nameInDb = "rzjl_a")
    private String rzjl_a;
    @Property(nameInDb = "rzjl_b")
    private String rzjl_b;
    @Property(nameInDb = "rzjl_c")
    private String rzjl_c;
    @Property(nameInDb = "rzjl_d")
    private String rzjl_d;
    @Property(nameInDb = "rzjl_rzjgid")
    private String rzjl_rzjgid;
    @Property(nameInDb = "rzjl_e")
    private String rzjl_e;
    @Property(nameInDb = "rzjl_sb")
    private String rzjl_sb;

    public Sfrz_rzjl() {
        this.rzjlid = UUID.randomUUID().toString();
    }

    @Generated(hash = 1079779803)
    public Sfrz_rzjl(Long jlid, String rzjlid, String rzjl_rzfsno, String rzjl_ksno, String rzjl_kmbh, String rzjl_kdbh, String rzjl_kcbh, String rzjl_zwh, String rzjl_device, String rzjl_verify_result, String rzjl_time, String rzjl_Features, String rzjl_pith, String rzjl_a, String rzjl_b, String rzjl_c, String rzjl_d, String rzjl_rzjgid,
                     String rzjl_e, String rzjl_sb) {
        this.jlid = jlid;
        this.rzjlid = rzjlid;
        this.rzjl_rzfsno = rzjl_rzfsno;
        this.rzjl_ksno = rzjl_ksno;
        this.rzjl_kmbh = rzjl_kmbh;
        this.rzjl_kdbh = rzjl_kdbh;
        this.rzjl_kcbh = rzjl_kcbh;
        this.rzjl_zwh = rzjl_zwh;
        this.rzjl_device = rzjl_device;
        this.rzjl_verify_result = rzjl_verify_result;
        this.rzjl_time = rzjl_time;
        this.rzjl_Features = rzjl_Features;
        this.rzjl_pith = rzjl_pith;
        this.rzjl_a = rzjl_a;
        this.rzjl_b = rzjl_b;
        this.rzjl_c = rzjl_c;
        this.rzjl_d = rzjl_d;
        this.rzjl_rzjgid = rzjl_rzjgid;
        this.rzjl_e = rzjl_e;
        this.rzjl_sb = rzjl_sb;
    }

    @Override
    public String toString() {
        return rzjl_rzfsno + "," +
                rzjl_ksno + "," +
                rzjl_kmbh + "," +
                rzjl_kdbh + "," +
                rzjl_kcbh + "," +
                rzjl_zwh + "," +
                rzjl_device + "," +
                rzjl_verify_result + "," +
                rzjl_time + "," +
                rzjl_Features + "," +
                rzjl_pith.replaceAll("/", "\\\\") + "," +
                rzjl_a + "," +
                rzjl_b + "," +
                rzjl_c + "," +
                rzjl_d + "," +
                rzjl_rzjgid + "," +
                rzjl_e;
    }

    public Long getJlid() {
        return this.jlid;
    }

    public void setJlid(Long jlid) {
        this.jlid = jlid;
    }

    public String getRzjlid() {
        return this.rzjlid;
    }

    public void setRzjlid(String rzjlid) {
        this.rzjlid = rzjlid;
    }

    public String getRzjl_rzfsno() {
        return this.rzjl_rzfsno;
    }

    public void setRzjl_rzfsno(String rzjl_rzfsno) {
        this.rzjl_rzfsno = rzjl_rzfsno;
    }

    public String getRzjl_ksno() {
        return this.rzjl_ksno;
    }

    public void setRzjl_ksno(String rzjl_ksno) {
        this.rzjl_ksno = rzjl_ksno;
    }

    public String getRzjl_kmbh() {
        return this.rzjl_kmbh;
    }

    public void setRzjl_kmbh(String rzjl_kmbh) {
        this.rzjl_kmbh = rzjl_kmbh;
    }

    public String getRzjl_kdbh() {
        return this.rzjl_kdbh;
    }

    public void setRzjl_kdbh(String rzjl_kdbh) {
        this.rzjl_kdbh = rzjl_kdbh;
    }

    public String getRzjl_kcbh() {
        return this.rzjl_kcbh;
    }

    public void setRzjl_kcbh(String rzjl_kcbh) {
        this.rzjl_kcbh = rzjl_kcbh;
    }

    public String getRzjl_zwh() {
        return this.rzjl_zwh;
    }

    public void setRzjl_zwh(String rzjl_zwh) {
        this.rzjl_zwh = rzjl_zwh;
    }

    public String getRzjl_device() {
        return this.rzjl_device;
    }

    public void setRzjl_device(String rzjl_device) {
        this.rzjl_device = rzjl_device;
    }

    public String getRzjl_verify_result() {
        return this.rzjl_verify_result;
    }

    public void setRzjl_verify_result(String rzjl_verify_result) {
        this.rzjl_verify_result = rzjl_verify_result;
    }

    public String getRzjl_time() {
        return this.rzjl_time;
    }

    public void setRzjl_time(String rzjl_time) {
        this.rzjl_time = rzjl_time;
    }

    public String getRzjl_Features() {
        return this.rzjl_Features;
    }

    public void setRzjl_Features(String rzjl_Features) {
        this.rzjl_Features = rzjl_Features;
    }

    public String getRzjl_pith() {
        return this.rzjl_pith;
    }

    public void setRzjl_pith(String rzjl_pith) {
        this.rzjl_pith = rzjl_pith;
    }

    public String getRzjl_a() {
        return this.rzjl_a;
    }

    public void setRzjl_a(String rzjl_a) {
        this.rzjl_a = rzjl_a;
    }

    public String getRzjl_b() {
        return this.rzjl_b;
    }

    public void setRzjl_b(String rzjl_b) {
        this.rzjl_b = rzjl_b;
    }

    public String getRzjl_c() {
        return this.rzjl_c;
    }

    public void setRzjl_c(String rzjl_c) {
        this.rzjl_c = rzjl_c;
    }

    public String getRzjl_d() {
        return this.rzjl_d;
    }

    public void setRzjl_d(String rzjl_d) {
        this.rzjl_d = rzjl_d;
    }

    public String getRzjl_rzjgid() {
        return this.rzjl_rzjgid;
    }

    public void setRzjl_rzjgid(String rzjl_rzjgid) {
        this.rzjl_rzjgid = rzjl_rzjgid;
    }

    public String getRzjl_e() {
        return this.rzjl_e;
    }

    public void setRzjl_e(String rzjl_e) {
        this.rzjl_e = rzjl_e;
    }

    public String getRzjl_sb() {
        return this.rzjl_sb;
    }

    public void setRzjl_sb(String rzjl_sb) {
        this.rzjl_sb = rzjl_sb;
    }
}
