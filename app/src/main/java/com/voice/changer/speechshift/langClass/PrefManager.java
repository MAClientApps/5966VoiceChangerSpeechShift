package com.voice.changer.speechshift.langClass;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String APP_LANGUAGE = "APP_LANGUAGE";
    private static final String IS_FIRST_INSTALL_APP = "IS_FIRST_INSTALL_APP";
    private static PrefManager instance;
    private final SharedPreferences.Editor editor;
    private final SharedPreferences pref;

    private PrefManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("voice_changer", 0);
        this.pref = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public static PrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new PrefManager(context);
        }
        return instance;
    }

    public String getDefaultLanguage() {
        return this.pref.getString(APP_LANGUAGE, "en");
    }

    public void setAppLanguage(String str) {
        this.editor.putString(APP_LANGUAGE, str);
        this.editor.commit();
    }

    public boolean isFirstInstallApp() {
        return this.pref.getBoolean(IS_FIRST_INSTALL_APP, true);
    }

    public void setFirstInstallApp(boolean z) {
        this.editor.putBoolean(IS_FIRST_INSTALL_APP, z);
        this.editor.commit();
    }
}
