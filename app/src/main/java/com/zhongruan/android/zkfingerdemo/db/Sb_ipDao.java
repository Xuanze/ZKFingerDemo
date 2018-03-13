package com.zhongruan.android.zkfingerdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhongruan.android.zkfingerdemo.db.entity.Sb_ip;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "sb_ip".
*/
public class Sb_ipDao extends AbstractDao<Sb_ip, Long> {

    public static final String TABLENAME = "sb_ip";

    /**
     * Properties of entity Sb_ip.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Ipid = new Property(0, Long.class, "ipid", true, "ipid");
        public final static Property Sb_ip = new Property(1, String.class, "sb_ip", false, "sb_ip");
    }


    public Sb_ipDao(DaoConfig config) {
        super(config);
    }
    
    public Sb_ipDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"sb_ip\" (" + //
                "\"ipid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: ipid
                "\"sb_ip\" TEXT);"); // 1: sb_ip
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"sb_ip\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Sb_ip entity) {
        stmt.clearBindings();
 
        Long ipid = entity.getIpid();
        if (ipid != null) {
            stmt.bindLong(1, ipid);
        }
 
        String sb_ip = entity.getSb_ip();
        if (sb_ip != null) {
            stmt.bindString(2, sb_ip);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Sb_ip entity) {
        stmt.clearBindings();
 
        Long ipid = entity.getIpid();
        if (ipid != null) {
            stmt.bindLong(1, ipid);
        }
 
        String sb_ip = entity.getSb_ip();
        if (sb_ip != null) {
            stmt.bindString(2, sb_ip);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Sb_ip readEntity(Cursor cursor, int offset) {
        Sb_ip entity = new Sb_ip( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ipid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // sb_ip
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Sb_ip entity, int offset) {
        entity.setIpid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSb_ip(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Sb_ip entity, long rowId) {
        entity.setIpid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Sb_ip entity) {
        if(entity != null) {
            return entity.getIpid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Sb_ip entity) {
        return entity.getIpid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}