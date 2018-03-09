package com.zhongruan.android.zkfingerdemo.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;

import java.util.List;

import rx.android.BuildConfig;

import static android.content.pm.PackageManager.GET_PROVIDERS;

public class APPUtil {
    private static String AUTHORITY;

    public static void createShortcut(Context context, Class class1, String name, int icon) {
        Intent intent = new Intent();
        intent.setClass(context, class1);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcut.putExtra("duplicate", false);
        shortcut.putExtra("android.intent.extra.shortcut.NAME", name);
        shortcut.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(context, icon));
        shortcut.putExtra("android.intent.extra.shortcut.INTENT", intent);
        context.sendBroadcast(shortcut);
    }

    public static boolean isShortCutExist(Context context, String title) {
        boolean isInstallShortcut = false;
        if (TextUtils.isEmpty(AUTHORITY)) {
            AUTHORITY = getAuthorityFromPermission(context);
        }
        ContentResolver cr = context.getContentResolver();
        if (!TextUtils.isEmpty(AUTHORITY)) {
            try {
                Cursor c = cr.query(Uri.parse(AUTHORITY), new String[]{"title", "iconResource"}, "title=?", new String[]{title}, null);
                if (c != null && c.getCount() > 0) {
                    isInstallShortcut = true;
                }
                if (!(c == null || c.isClosed())) {
                    c.close();
                }
            } catch (Exception e) {
                LogUtil.e("isShortCutExist", e.getMessage());
            }
        }
        return isInstallShortcut;
    }

    public static String getCurrentLauncherPackageName(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if (res == null || res.activityInfo == null) {
            return BuildConfig.VERSION_NAME;
        }
        if (res.activityInfo.packageName.equals("android")) {
            return BuildConfig.VERSION_NAME;
        }
        return res.activityInfo.packageName;
    }

    public static String getAuthorityFromPermissionDefault(Context context) {
        return getThirdAuthorityFromPermission(context, "com.android.launcher.permission.READ_SETTINGS");
    }

    public static String getThirdAuthorityFromPermission(Context context, String permission) {
        if (TextUtils.isEmpty(permission)) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(GET_PROVIDERS);
            if (packs == null) {
                return BuildConfig.VERSION_NAME;
            }
            for (PackageInfo pack : packs) {
                ProviderInfo[] providers = pack.providers;
                if (providers != null) {
                    for (ProviderInfo provider : providers) {
                        if (permission.equals(provider.readPermission) || permission.equals(provider.writePermission)) {
                            String authority = provider.authority;
                            if (!TextUtils.isEmpty(authority) && (authority.contains(".launcher.settings") || authority.contains(".twlauncher.settings") || authority.contains(".launcher2.settings"))) {
                                return authority;
                            }
                        }
                    }
                    continue;
                }
            }
            return BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BuildConfig.VERSION_NAME;
    }

    public static String getAuthorityFromPermission(Context context) {
        String authority = getAuthorityFromPermissionDefault(context);
        if (authority == null || authority.trim().equals(BuildConfig.VERSION_NAME)) {
            authority = getThirdAuthorityFromPermission(context, getCurrentLauncherPackageName(context) + ".permission.READ_SETTINGS");
        }
        if (TextUtils.isEmpty(authority)) {
            int sdkInt = VERSION.SDK_INT;
            if (sdkInt < 8) {
                authority = "com.android.launcher.settings";
            } else if (sdkInt < 19) {
                authority = "com.android.launcher2.settings";
            } else {
                authority = "com.android.launcher3.settings";
            }
        }
        return "content://" + authority + "/favorites?notify=true";
    }
}
