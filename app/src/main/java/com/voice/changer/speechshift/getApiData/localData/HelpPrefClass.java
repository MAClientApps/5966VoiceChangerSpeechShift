package com.voice.changer.speechshift.getApiData.localData;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.inject.Inject;

public final class HelpPrefClass implements HelperPreference {
    public static final String guideKey = "guide";
    SharedPreferences preferences;

    @Inject
    public HelpPrefClass(Context ctx, String strPrefFileName) {
        String orCreate = null;
        try {
            orCreate = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SharedPreferences create = null;
        try {
            create = EncryptedSharedPreferences.create(strPrefFileName, orCreate, ctx, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.preferences = create;
    }

    public String getToken() {
        return this.preferences.getString("token", "");
    }

    public void setToken(String strData) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("token", strData);
        editor.apply();
    }

    public boolean getGuide() {
        return this.preferences.getBoolean(guideKey, false);
    }

    public void setGuide(boolean s) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putBoolean(guideKey, s);
        editor.apply();
    }
}
