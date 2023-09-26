package com.voice.changer.speechshift.custUi.constatnt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.voice.changer.speechshift.allDialogs.RingtonesPermissionDialog;


public final class RingtonePermission {
    public static boolean checkSystemWritePermission(Context context) {
        return Settings.System.canWrite(context);
    }

    public static void openAndroidPermissionsMenu(Activity activity) {
        new RingtonesPermissionDialog(activity, true, () -> {
            Intent settingIntent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            settingIntent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivity(settingIntent);
            return null;
        }).show();
    }
}
