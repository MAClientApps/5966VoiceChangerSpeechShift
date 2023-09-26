package com.voice.changer.speechshift.recordingServices;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.custUi.AppConstant;

import kotlin.jvm.internal.Intrinsics;

public final class SaveFileService extends Service {

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent strIntent, int i, int i2) {
        String str;
        if (strIntent == null) {
            str = null;
        } else {
            str = strIntent.getStringExtra(AppConstant.actionService);
        }
        if (str == null) {
            return Service.START_NOT_STICKY;
        }
        handleActions(str);
        return Service.START_NOT_STICKY;
    }

    private final void notificationSend() {
        Notification notification = new NotificationCompat.Builder((Context) this, AppConstant.channelId).setSmallIcon((int) R.drawable.ic_menu_set_as_ring).setContentTitle(getString(R.string.saving_audio)).setAutoCancel(true).build();
        Intrinsics.checkNotNullExpressionValue(notification, "Builder(this, Constants.…rue)\n            .build()");
        startForeground(101, notification);
    }

    private final void handleActions(String strAction) {
        if (Intrinsics.areEqual((Object) strAction, (Object) AppConstant.actionStart)) {
            notificationSend();
        } else if (Intrinsics.areEqual((Object) strAction, (Object) AppConstant.actionStop)) {
            stopSelf();
        }
    }
}
