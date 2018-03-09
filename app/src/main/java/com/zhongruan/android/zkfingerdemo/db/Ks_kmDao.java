package com.zhongruan.android.zkfingerdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhongruan.android.zkfingerdemo.db.entity.Ks_km;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ks_km".
*/
public class Ks_kmDao extends AbstractDao<Ks_km, Long> {

    public static final String TABLENAME = "ks_km";

    /**
     * Properties of entity Ks_km.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Kmid = new Property(0, Long.class, "kmid", true, "kmid");
        public final static Property Km_no = new Property(1, String.class, "km_no", false, "km_no");
        public final static Property Km_name = new Property(2, String.class, "km_name", false, "km_name");
    }


    public Ks_kmDao(DaoConfig config) {
        super(config);
    }
    
    public Ks_kmDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ks_km\" (" + //
                "\"kmid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: kmid
                "\"km_no\" TEXT," + // 1: km_no
                "\"km_name\" TEXT);"); // 2: km_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ks_km\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Ks_km entity) {
        stmt.clearBindings();
 
        Long kmid = entity.getKmid();
        if (kmid != null) {
            stmt.bindLong(1, kmid);
        }
 
        String km_no = entity.getKm_no();
        if (km_no != null) {
            stmt.bindString(2, km_no);
        }
 
        String km_name = entity.getKm_name();
        if (km_name != null) {
            stmt.bindString(3, km_name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Ks_km entity) {
        stmt.clearBindings();
 
        Long kmid = entity.getKmid();
        if (kmid != null) {
            stmt.bindLong(1, kmid);
        }
 
        String km_no = entity.getKm_no();
        if (km_no != null) {
            stmt.bindString(2, km_no);
        }
 
        String km_name = entity.getKm_name();
        if (km_name != null) {
            stmt.bindString(3, km_name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Ks_km readEntity(Cursor cursor, int offset) {
        Ks_km entity = new Ks_km( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // kmid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // km_no
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // km_name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Ks_km entity, int offset) {
        entity.setKmid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKm_no(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKm_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Ks_km entity, long rowId) {
        entity.setKmid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Ks_km entity) {
        if(entity != null) {
            return entity.getKmid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Ks_km entity) {
        return entity.getKmid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
