package com.zhongruan.android.zkfingerdemo.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/9/25.
 */
@Entity(nameInDb = "sfrz_rzfs")
public class Sfrz_rzfs {
    @Id(autoincrement = true)
    @Property(nameInDb = "rzfsid")
    private Long rzfsid;
    @Property(nameInDb = "rzfs_no")
    private String rzfs_no;
    @Property(nameInDb = "rzfs_name")
    private String rzfs_name;

    @Generated(hash = 1148625169)
    public Sfrz_rzfs(Long rzfsid, String rzfs_no, String rzfs_name) {
        this.rzfsid = rzfsid;
        this.rzfs_no = rzfs_no;
        this.rzfs_name = rzfs_name;
    }

    @Generated(hash = 438240106)
    public Sfrz_rzfs() {
    }

    public Long getRzfsid() {
        return this.rzfsid;
    }

    public void setRzfsid(Long rzfsid) {
        this.rzfsid = rzfsid;
    }

    public String getRzfs_no() {
        return this.rzfs_no;
    }

    public void setRzfs_no(String rzfs_no) {
        this.rzfs_no = rzfs_no;
    }

    public String getRzfs_name() {
        return this.rzfs_name;
    }

    public void setRzfs_name(String rzfs_name) {
        this.rzfs_name = rzfs_name;
    }
}
