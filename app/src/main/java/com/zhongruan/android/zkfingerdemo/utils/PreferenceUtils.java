package com.zhongruan.android.zkfingerdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {
    private static final String DEFAULT_FILE_NAME = "default.file";
    private static PreferenceUtils INSTANCE = null;
    private static final String TAG = "ABLPreferenceUtils";
    private static Context mContext;

    static {
        INSTANCE = null;
    }

    public PreferenceUtils(Context context) {
        mContext = context;
    }

    public static synchronized PreferenceUtils getInstance(Context context) {
        PreferenceUtils aBLPreferenceUtils;
        synchronized (PreferenceUtils.class) {
            synchronized (TAG) {
                if (INSTANCE == null) {
                    INSTANCE = new PreferenceUtils(context);
                }
            }
            mContext = context;
            aBLPreferenceUtils = INSTANCE;
        }
        return aBLPreferenceUtils;
    }

    public SharedPreferences getPreferences() {
        return mContext.getSharedPreferences(DEFAULT_FILE_NAME, 0);
    }

    public void setPrefString(String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    public void setPrefBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    public void setPrefInt(String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    public void setPrefFloat(String key, float value) {
        getPreferences().edit().putFloat(key, value).commit();
    }

    public void setSettingLong(String key, long value) {
        getPreferences().edit().putLong(key, value).commit();
    }

    public void setPrefString(String key, String value, boolean commit) {
        SharedPreferences settings = getPreferences();
        if (commit) {
            settings.edit().putString(key, value).commit();
        } else {
            settings.edit().putString(key, value);
        }
    }

    public void setPrefBoolean(String key, boolean value, boolean commit) {
        SharedPreferences settings = getPreferences();
        if (commit) {
            settings.edit().putBoolean(key, value).commit();
        } else {
            settings.edit().putBoolean(key, value);
        }
    }

    public void setPrefInt(String key, int value, boolean commit) {
        SharedPreferences settings = getPreferences();
        if (commit) {
            settings.edit().putInt(key, value).commit();
        } else {
            settings.edit().putInt(key, value);
        }
    }

    public void setPrefFloat(String key, float value, boolean commit) {
        getPreferences().edit().putFloat(key, value).commit();
    }

    public void setSettingLong(String key, long value, boolean commit) {
        SharedPreferences settings = getPreferences();
        if (commit) {
            settings.edit().putLong(key, value).commit();
        } else {
            settings.edit().putLong(key, value);
        }
    }

    public String getPrefString(String key, String defaultValue) {
        try {
            defaultValue = getPreferences().getString(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }

    public boolean getPrefBoolean(String key, boolean defaultValue) {
        try {
            defaultValue = getPreferences().getBoolean(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }

    public boolean hasKey(String key) {
        return getPreferences().contains(key);
    }

    public int getPrefInt(String key, int defaultValue) {
        try {
            defaultValue = getPreferences().getInt(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }

    public float getPrefFloat(String key, float defaultValue) {
        try {
            defaultValue = getPreferences().getFloat(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }

    public long getPrefLong(String key, long defaultValue) {
        try {
            defaultValue = getPreferences().getLong(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }

    public void clearPreference(SharedPreferences p) {
        Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public void commit() {
        getPreferences().edit().commit();
    }
}
