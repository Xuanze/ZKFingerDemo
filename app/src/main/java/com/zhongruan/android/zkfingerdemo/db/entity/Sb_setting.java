package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/11/20.
 */
@Entity(nameInDb = "sb_setting")
public class Sb_setting {
    @Id(autoincrement = true)
    @Property(nameInDb = "settingid")
    private Long settingid;
    @Property(nameInDb = "sb_ip")
    private String sb_ip;
    @Property(nameInDb = "sb_sn")
    private String sb_sn;
    @Property(nameInDb = "sb_ms")
    private String sb_ms;
    @Property(nameInDb = "sb_hyfs")
    private String sb_hyfs;
    @Property(nameInDb = "sb_finger_fz")
    private String sb_finger_fz;
    @Property(nameInDb = "sb_finger_cfcs")
    private String sb_finger_cfcs;
    @Property(nameInDb = "sb_face_xsd")
    private String sb_face_xsd;
    @Property(nameInDb = "sb_face_cfcs")
    private String sb_face_cfcs;

    @Generated(hash = 87840042)
    public Sb_setting(Long settingid, String sb_ip, String sb_sn, String sb_ms,
                      String sb_hyfs, String sb_finger_fz, String sb_finger_cfcs,
                      String sb_face_xsd, String sb_face_cfcs) {
        this.settingid = settingid;
        this.sb_ip = sb_ip;
        this.sb_sn = sb_sn;
        this.sb_ms = sb_ms;
        this.sb_hyfs = sb_hyfs;
        this.sb_finger_fz = sb_finger_fz;
        this.sb_finger_cfcs = sb_finger_cfcs;
        this.sb_face_xsd = sb_face_xsd;
        this.sb_face_cfcs = sb_face_cfcs;
    }

    @Generated(hash = 455004070)
    public Sb_setting() {
    }

    public Long getSettingid() {
        return this.settingid;
    }

    public void setSettingid(Long settingid) {
        this.settingid = settingid;
    }

    public String getSb_ip() {
        return this.sb_ip;
    }

    public void setSb_ip(String sb_ip) {
        this.sb_ip = sb_ip;
    }

    public String getSb_sn() {
        return this.sb_sn;
    }

    public void setSb_sn(String sb_sn) {
        this.sb_sn = sb_sn;
    }

    public String getSb_ms() {
        return this.sb_ms;
    }

    public void setSb_ms(String sb_ms) {
        this.sb_ms = sb_ms;
    }

    public String getSb_hyfs() {
        return this.sb_hyfs;
    }

    public void setSb_hyfs(String sb_hyfs) {
        this.sb_hyfs = sb_hyfs;
    }

    public String getSb_finger_fz() {
        return this.sb_finger_fz;
    }

    public void setSb_finger_fz(String sb_finger_fz) {
        this.sb_finger_fz = sb_finger_fz;
    }

    public String getSb_finger_cfcs() {
        return this.sb_finger_cfcs;
    }

    public void setSb_finger_cfcs(String sb_finger_cfcs) {
        this.sb_finger_cfcs = sb_finger_cfcs;
    }

    public String getSb_face_xsd() {
        return this.sb_face_xsd;
    }

    public void setSb_face_xsd(String sb_face_xsd) {
        this.sb_face_xsd = sb_face_xsd;
    }

    public String getSb_face_cfcs() {
        return this.sb_face_cfcs;
    }

    public void setSb_face_cfcs(String sb_face_cfcs) {
        this.sb_face_cfcs = sb_face_cfcs;
    }
}
