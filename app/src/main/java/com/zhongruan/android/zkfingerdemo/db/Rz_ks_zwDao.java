package com.zhongruan.android.zkfingerdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhongruan.android.zkfingerdemo.db.entity.Rz_ks_zw;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "rz_ks_zw".
*/
public class Rz_ks_zwDao extends AbstractDao<Rz_ks_zw, Long> {

    public static final String TABLENAME = "rz_ks_zw";

    /**
     * Properties of entity Rz_ks_zw.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Ksid = new Property(0, Long.class, "ksid", true, "ksid");
        public final static Property Ks_ksno = new Property(1, String.class, "ks_ksno", false, "ks_ksno");
        public final static Property Ks_xm = new Property(2, String.class, "ks_xm", false, "ks_xm");
        public final static Property Ks_zjno = new Property(3, String.class, "ks_zjno", false, "ks_zjno");
        public final static Property Ks_xb = new Property(4, String.class, "ks_xb", false, "ks_xb");
        public final static Property Ks_kcno = new Property(5, String.class, "ks_kcno", false, "ks_kcno");
        public final static Property Ks_kcmc = new Property(6, String.class, "ks_kcmc", false, "ks_kcmc");
        public final static Property Ks_zwh = new Property(7, String.class, "ks_zwh", false, "ks_zwh");
        public final static Property Ks_xp = new Property(8, String.class, "ks_xp", false, "ks_xp");
        public final static Property Zw_feature = new Property(9, String.class, "zw_feature", false, "zw_feature");
        public final static Property Zw_bs = new Property(10, String.class, "zw_bs", false, "zw_bs");
    }


    public Rz_ks_zwDao(DaoConfig config) {
        super(config);
    }
    
    public Rz_ks_zwDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"rz_ks_zw\" (" + //
                "\"ksid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: ksid
                "\"ks_ksno\" TEXT," + // 1: ks_ksno
                "\"ks_xm\" TEXT," + // 2: ks_xm
                "\"ks_zjno\" TEXT," + // 3: ks_zjno
                "\"ks_xb\" TEXT," + // 4: ks_xb
                "\"ks_kcno\" TEXT," + // 5: ks_kcno
                "\"ks_kcmc\" TEXT," + // 6: ks_kcmc
                "\"ks_zwh\" TEXT," + // 7: ks_zwh
                "\"ks_xp\" TEXT," + // 8: ks_xp
                "\"zw_feature\" TEXT," + // 9: zw_feature
                "\"zw_bs\" TEXT);"); // 10: zw_bs
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"rz_ks_zw\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Rz_ks_zw entity) {
        stmt.clearBindings();
 
        Long ksid = entity.getKsid();
        if (ksid != null) {
            stmt.bindLong(1, ksid);
        }
 
        String ks_ksno = entity.getKs_ksno();
        if (ks_ksno != null) {
            stmt.bindString(2, ks_ksno);
        }
 
        String ks_xm = entity.getKs_xm();
        if (ks_xm != null) {
            stmt.bindString(3, ks_xm);
        }
 
        String ks_zjno = entity.getKs_zjno();
        if (ks_zjno != null) {
            stmt.bindString(4, ks_zjno);
        }
 
        String ks_xb = entity.getKs_xb();
        if (ks_xb != null) {
            stmt.bindString(5, ks_xb);
        }
 
        String ks_kcno = entity.getKs_kcno();
        if (ks_kcno != null) {
            stmt.bindString(6, ks_kcno);
        }
 
        String ks_kcmc = entity.getKs_kcmc();
        if (ks_kcmc != null) {
            stmt.bindString(7, ks_kcmc);
        }
 
        String ks_zwh = entity.getKs_zwh();
        if (ks_zwh != null) {
            stmt.bindString(8, ks_zwh);
        }
 
        String ks_xp = entity.getKs_xp();
        if (ks_xp != null) {
            stmt.bindString(9, ks_xp);
        }
 
        String zw_feature = entity.getZw_feature();
        if (zw_feature != null) {
            stmt.bindString(10, zw_feature);
        }
 
        String zw_bs = entity.getZw_bs();
        if (zw_bs != null) {
            stmt.bindString(11, zw_bs);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Rz_ks_zw entity) {
        stmt.clearBindings();
 
        Long ksid = entity.getKsid();
        if (ksid != null) {
            stmt.bindLong(1, ksid);
        }
 
        String ks_ksno = entity.getKs_ksno();
        if (ks_ksno != null) {
            stmt.bindString(2, ks_ksno);
        }
 
        String ks_xm = entity.getKs_xm();
        if (ks_xm != null) {
            stmt.bindString(3, ks_xm);
        }
 
        String ks_zjno = entity.getKs_zjno();
        if (ks_zjno != null) {
            stmt.bindString(4, ks_zjno);
        }
 
        String ks_xb = entity.getKs_xb();
        if (ks_xb != null) {
            stmt.bindString(5, ks_xb);
        }
 
        String ks_kcno = entity.getKs_kcno();
        if (ks_kcno != null) {
            stmt.bindString(6, ks_kcno);
        }
 
        String ks_kcmc = entity.getKs_kcmc();
        if (ks_kcmc != null) {
            stmt.bindString(7, ks_kcmc);
        }
 
        String ks_zwh = entity.getKs_zwh();
        if (ks_zwh != null) {
            stmt.bindString(8, ks_zwh);
        }
 
        String ks_xp = entity.getKs_xp();
        if (ks_xp != null) {
            stmt.bindString(9, ks_xp);
        }
 
        String zw_feature = entity.getZw_feature();
        if (zw_feature != null) {
            stmt.bindString(10, zw_feature);
        }
 
        String zw_bs = entity.getZw_bs();
        if (zw_bs != null) {
            stmt.bindString(11, zw_bs);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Rz_ks_zw readEntity(Cursor cursor, int offset) {
        Rz_ks_zw entity = new Rz_ks_zw( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ksid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ks_ksno
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ks_xm
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ks_zjno
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ks_xb
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ks_kcno
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // ks_kcmc
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ks_zwh
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // ks_xp
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // zw_feature
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // zw_bs
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Rz_ks_zw entity, int offset) {
        entity.setKsid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKs_ksno(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKs_xm(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setKs_zjno(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setKs_xb(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setKs_kcno(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setKs_kcmc(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setKs_zwh(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setKs_xp(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setZw_feature(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setZw_bs(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Rz_ks_zw entity, long rowId) {
        entity.setKsid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Rz_ks_zw entity) {
        if(entity != null) {
            return entity.getKsid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Rz_ks_zw entity) {
        return entity.getKsid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
