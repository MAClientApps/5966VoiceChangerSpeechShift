package com.voice.changer.speechshift.custUi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.voice.changer.speechshift.MainApplication;

import java.util.Locale;

public class SetLanguage {

    public static void setLocale(Context mCtx) {
        String defLang = MainApplication.getPrefManager().getDefaultLanguage();
        if (defLang.length() != 0) {
            setLanguagesString((Activity) mCtx, defLang);
        }
    }

    public static void setLanguagesString(Activity act, String strLangCode) {
        Locale locales = new Locale(strLangCode);
        Locale.setDefault(locales);
        Resources actResources = act.getResources();
        Configuration actResourcesConfiguration = actResources.getConfiguration();
        actResourcesConfiguration.setLocale(locales);
        actResources.updateConfiguration(actResourcesConfiguration, actResources.getDisplayMetrics());
    }
}
