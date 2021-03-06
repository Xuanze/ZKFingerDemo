package com.zhongruan.android.zkfingerdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhongruan.android.zkfingerdemo.db.entity.Ks_cc;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ks_cc".
*/
public class Ks_ccDao extends AbstractDao<Ks_cc, Long> {

    public static final String TABLENAME = "ks_cc";

    /**
     * Properties of entity Ks_cc.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Ccid = new Property(0, Long.class, "ccid", true, "ccid");
        public final static Property Cc_no = new Property(1, String.class, "cc_no", false, "cc_no");
        public final static Property Cc_name = new Property(2, String.class, "cc_name", false, "cc_name");
        public final static Property Km_no = new Property(3, String.class, "km_no", false, "km_no");
        public final static Property Km_name = new Property(4, String.class, "km_name", false, "km_name");
        public final static Property Cc_kssj = new Property(5, String.class, "cc_kssj", false, "cc_kssj");
        public final static Property Cc_jssj = new Property(6, String.class, "cc_jssj", false, "cc_jssj");
        public final static Property Cc_extract = new Property(7, String.class, "cc_extract", false, "cc_extract");
    }


    public Ks_ccDao(DaoConfig config) {
        super(config);
    }
    
    public Ks_ccDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ks_cc\" (" + //
                "\"ccid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: ccid
                "\"cc_no\" TEXT," + // 1: cc_no
                "\"cc_name\" TEXT," + // 2: cc_name
                "\"km_no\" TEXT," + // 3: km_no
                "\"km_name\" TEXT," + // 4: km_name
                "\"cc_kssj\" TEXT," + // 5: cc_kssj
                "\"cc_jssj\" TEXT," + // 6: cc_jssj
                "\"cc_extract\" TEXT);"); // 7: cc_extract
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ks_cc\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Ks_cc entity) {
        stmt.clearBindings();
 
        Long ccid = entity.getCcid();
        if (ccid != null) {
            stmt.bindLong(1, ccid);
        }
 
        String cc_no = entity.getCc_no();
        if (cc_no != null) {
            stmt.bindString(2, cc_no);
        }
 
        String cc_name = entity.getCc_name();
        if (cc_name != null) {
            stmt.bindString(3, cc_name);
        }
 
        String km_no = entity.getKm_no();
        if (km_no != null) {
            stmt.bindString(4, km_no);
        }
 
        String km_name = entity.getKm_name();
        if (km_name != null) {
            stmt.bindString(5, km_name);
        }
 
        String cc_kssj = entity.getCc_kssj();
        if (cc_kssj != null) {
            stmt.bindString(6, cc_kssj);
        }
 
        String cc_jssj = entity.getCc_jssj();
        if (cc_jssj != null) {
            stmt.bindString(7, cc_jssj);
        }
 
        String cc_extract = entity.getCc_extract();
        if (cc_extract != null) {
            stmt.bindString(8, cc_extract);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Ks_cc entity) {
        stmt.clearBindings();
 
        Long ccid = entity.getCcid();
        if (ccid != null) {
            stmt.bindLong(1, ccid);
        }
 
        String cc_no = entity.getCc_no();
        if (cc_no != null) {
            stmt.bindString(2, cc_no);
        }
 
        String cc_name = entity.getCc_name();
        if (cc_name != null) {
            stmt.bindString(3, cc_name);
        }
 
        String km_no = entity.getKm_no();
        if (km_no != null) {
            stmt.bindString(4, km_no);
        }
 
        String km_name = entity.getKm_name();
        if (km_name != null) {
            stmt.bindString(5, km_name);
        }
 
        String cc_kssj = entity.getCc_kssj();
        if (cc_kssj != null) {
            stmt.bindString(6, cc_kssj);
        }
 
        String cc_jssj = entity.getCc_jssj();
        if (cc_jssj != null) {
            stmt.bindString(7, cc_jssj);
        }
 
        String cc_extract = entity.getCc_extract();
        if (cc_extract != null) {
            stmt.bindString(8, cc_extract);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Ks_cc readEntity(Cursor cursor, int offset) {
        Ks_cc entity = new Ks_cc( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ccid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // cc_no
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cc_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // km_no
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // km_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cc_kssj
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // cc_jssj
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // cc_extract
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Ks_cc entity, int offset) {
        entity.setCcid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCc_no(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCc_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setKm_no(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setKm_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCc_kssj(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCc_jssj(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCc_extract(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Ks_cc entity, long rowId) {
        entity.setCcid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Ks_cc entity) {
        if(entity != null) {
            return entity.getCcid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Ks_cc entity) {
        return entity.getCcid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
