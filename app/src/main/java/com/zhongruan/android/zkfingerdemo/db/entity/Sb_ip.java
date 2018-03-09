package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/11/20.
 */
@Entity(nameInDb = "sb_ip")

public class Sb_ip {
    @Id(autoincrement = true)
    @Property(nameInDb = "ipid")
    private Long ipid;
    @Property(nameInDb = "sb_ip")
    private String sb_ip;
    @Generated(hash = 490870068)
    public Sb_ip(Long ipid, String sb_ip) {
        this.ipid = ipid;
        this.sb_ip = sb_ip;
    }
    @Generated(hash = 1872840896)
    public Sb_ip() {
    }
    public Long getIpid() {
        return this.ipid;
    }
    public void setIpid(Long ipid) {
        this.ipid = ipid;
    }
    public String getSb_ip() {
        return this.sb_ip;
    }
    public void setSb_ip(String sb_ip) {
        this.sb_ip = sb_ip;
    }

}
