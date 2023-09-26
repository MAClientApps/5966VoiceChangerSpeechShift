package com.voice.changer.speechshift.custUi;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import com.voice.changer.speechshift.R;

import java.io.File;

public class FileMethods {
    public static String getMainDirPath(Context mContext) {
        try {
            File voiceEffectAudioFilePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +"/"+ mContext.getResources().getString(R.string.app_name), "VoiceEffectAudio");
            if (!voiceEffectAudioFilePath.exists()) {
                voiceEffectAudioFilePath.mkdirs();
            }
            return voiceEffectAudioFilePath.getAbsolutePath();
        } catch (Exception unused) {
            return mContext.getFilesDir().getAbsolutePath();
        }
    }

    public static File getDirectory(Activity activity) {
        File voiceEffectDirPath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + activity.getResources().getString(R.string.app_name)+"/"+ "VoiceEffects");
        if (!voiceEffectDirPath.exists()) {
            voiceEffectDirPath.mkdirs();
        }
        return voiceEffectDirPath;
    }

    public static String milliSecFormat(long mill) {
        String str1;
        String str22;
        String str33;
        int ii = (int) (mill / 3600000);
        long j2 = mill % 3600000;
        int i2 = ((int) j2) / 60000;
        int roundData = Math.round((float) ((j2 % 60000) / 1000));
        if (ii > 0) {
            str1 = ii + ":";
        } else {
            str1 = "";
        }
        if (i2 < 10) {
            str22 = "0" + i2;
        } else {
            str22 = "" + i2;
        }
        if (roundData < 10) {
            str33 = "0" + roundData;
        } else {
            str33 = "" + roundData;
        }
        return str1 + str22 + ":" + str33;
    }
}
