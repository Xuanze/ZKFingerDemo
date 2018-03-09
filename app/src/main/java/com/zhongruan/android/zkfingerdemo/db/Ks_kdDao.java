package com.zhongruan.android.zkfingerdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhongruan.android.zkfingerdemo.db.entity.Ks_kd;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ks_kd".
*/
public class Ks_kdDao extends AbstractDao<Ks_kd, Long> {

    public static final String TABLENAME = "ks_kd";

    /**
     * Properties of entity Ks_kd.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Kdid = new Property(0, Long.class, "kdid", true, "kdid");
        public final static Property Kd_no = new Property(1, String.class, "kd_no", false, "kd_no");
        public final static Property Kd_name = new Property(2, String.class, "kd_name", false, "kd_name");
    }


    public Ks_kdDao(DaoConfig config) {
        super(config);
    }
    
    public Ks_kdDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ks_kd\" (" + //
                "\"kdid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: kdid
                "\"kd_no\" TEXT," + // 1: kd_no
                "\"kd_name\" TEXT);"); // 2: kd_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ks_kd\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Ks_kd entity) {
        stmt.clearBindings();
 
        Long kdid = entity.getKdid();
        if (kdid != null) {
            stmt.bindLong(1, kdid);
        }
 
        String kd_no = entity.getKd_no();
        if (kd_no != null) {
            stmt.bindString(2, kd_no);
        }
 
        String kd_name = entity.getKd_name();
        if (kd_name != null) {
            stmt.bindString(3, kd_name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Ks_kd entity) {
        stmt.clearBindings();
 
        Long kdid = entity.getKdid();
        if (kdid != null) {
            stmt.bindLong(1, kdid);
        }
 
        String kd_no = entity.getKd_no();
        if (kd_no != null) {
            stmt.bindString(2, kd_no);
        }
 
        String kd_name = entity.getKd_name();
        if (kd_name != null) {
            stmt.bindString(3, kd_name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Ks_kd readEntity(Cursor cursor, int offset) {
        Ks_kd entity = new Ks_kd( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // kdid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // kd_no
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // kd_name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Ks_kd entity, int offset) {
        entity.setKdid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKd_no(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKd_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Ks_kd entity, long rowId) {
        entity.setKdid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Ks_kd entity) {
        if(entity != null) {
            return entity.getKdid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Ks_kd entity) {
        return entity.getKdid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
