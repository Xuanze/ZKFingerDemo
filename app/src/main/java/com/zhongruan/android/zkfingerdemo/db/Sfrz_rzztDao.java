package com.zhongruan.android.zkfingerdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzzt;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "sfrz_rzzt".
*/
public class Sfrz_rzztDao extends AbstractDao<Sfrz_rzzt, Long> {

    public static final String TABLENAME = "sfrz_rzzt";

    /**
     * Properties of entity Sfrz_rzzt.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Rzztid = new Property(0, Long.class, "rzztid", true, "rzztid");
        public final static Property Rzzt_no = new Property(1, String.class, "rzzt_no", false, "rzzt_no");
        public final static Property Rzzt_name = new Property(2, String.class, "rzzt_name", false, "rzzt_name");
    }


    public Sfrz_rzztDao(DaoConfig config) {
        super(config);
    }
    
    public Sfrz_rzztDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"sfrz_rzzt\" (" + //
                "\"rzztid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: rzztid
                "\"rzzt_no\" TEXT," + // 1: rzzt_no
                "\"rzzt_name\" TEXT);"); // 2: rzzt_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"sfrz_rzzt\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Sfrz_rzzt entity) {
        stmt.clearBindings();
 
        Long rzztid = entity.getRzztid();
        if (rzztid != null) {
            stmt.bindLong(1, rzztid);
        }
 
        String rzzt_no = entity.getRzzt_no();
        if (rzzt_no != null) {
            stmt.bindString(2, rzzt_no);
        }
 
        String rzzt_name = entity.getRzzt_name();
        if (rzzt_name != null) {
            stmt.bindString(3, rzzt_name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Sfrz_rzzt entity) {
        stmt.clearBindings();
 
        Long rzztid = entity.getRzztid();
        if (rzztid != null) {
            stmt.bindLong(1, rzztid);
        }
 
        String rzzt_no = entity.getRzzt_no();
        if (rzzt_no != null) {
            stmt.bindString(2, rzzt_no);
        }
 
        String rzzt_name = entity.getRzzt_name();
        if (rzzt_name != null) {
            stmt.bindString(3, rzzt_name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Sfrz_rzzt readEntity(Cursor cursor, int offset) {
        Sfrz_rzzt entity = new Sfrz_rzzt( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // rzztid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // rzzt_no
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // rzzt_name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Sfrz_rzzt entity, int offset) {
        entity.setRzztid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRzzt_no(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRzzt_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Sfrz_rzzt entity, long rowId) {
        entity.setRzztid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Sfrz_rzzt entity) {
        if(entity != null) {
            return entity.getRzztid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Sfrz_rzzt entity) {
        return entity.getRzztid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
