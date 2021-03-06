package com.zhongruan.android.zkfingerdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        Bk_ksDao.createTable(db, ifNotExists);
        Bk_ksxpDao.createTable(db, ifNotExists);
        Bk_ks_cjxxDao.createTable(db, ifNotExists);
        Bk_ks_tempDao.createTable(db, ifNotExists);
        Kstz_zwDao.createTable(db, ifNotExists);
        Ks_ccDao.createTable(db, ifNotExists);
        Ks_kcDao.createTable(db, ifNotExists);
        Ks_kdDao.createTable(db, ifNotExists);
        Ks_kmDao.createTable(db, ifNotExists);
        Rz_ks_zwDao.createTable(db, ifNotExists);
        Sb_settingDao.createTable(db, ifNotExists);
        Sfrz_rzfsDao.createTable(db, ifNotExists);
        Sfrz_rzjgDao.createTable(db, ifNotExists);
        Sfrz_rzjlDao.createTable(db, ifNotExists);
        Sfrz_rzztDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        Bk_ksDao.dropTable(db, ifExists);
        Bk_ksxpDao.dropTable(db, ifExists);
        Bk_ks_cjxxDao.dropTable(db, ifExists);
        Bk_ks_tempDao.dropTable(db, ifExists);
        Kstz_zwDao.dropTable(db, ifExists);
        Ks_ccDao.dropTable(db, ifExists);
        Ks_kcDao.dropTable(db, ifExists);
        Ks_kdDao.dropTable(db, ifExists);
        Ks_kmDao.dropTable(db, ifExists);
        Rz_ks_zwDao.dropTable(db, ifExists);
        Sb_settingDao.dropTable(db, ifExists);
        Sfrz_rzfsDao.dropTable(db, ifExists);
        Sfrz_rzjgDao.dropTable(db, ifExists);
        Sfrz_rzjlDao.dropTable(db, ifExists);
        Sfrz_rzztDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(Bk_ksDao.class);
        registerDaoClass(Bk_ksxpDao.class);
        registerDaoClass(Bk_ks_cjxxDao.class);
        registerDaoClass(Bk_ks_tempDao.class);
        registerDaoClass(Kstz_zwDao.class);
        registerDaoClass(Ks_ccDao.class);
        registerDaoClass(Ks_kcDao.class);
        registerDaoClass(Ks_kdDao.class);
        registerDaoClass(Ks_kmDao.class);
        registerDaoClass(Rz_ks_zwDao.class);
        registerDaoClass(Sb_settingDao.class);
        registerDaoClass(Sfrz_rzfsDao.class);
        registerDaoClass(Sfrz_rzjgDao.class);
        registerDaoClass(Sfrz_rzjlDao.class);
        registerDaoClass(Sfrz_rzztDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
