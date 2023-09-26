package com.voice.changer.speechshift.getApiData;

import com.voice.changer.speechshift.getApiData.localData.HelperPreference;

import javax.inject.Inject;
import javax.inject.Singleton;

import kotlin.jvm.internal.Intrinsics;

@Singleton
public final class MyAppInterfaceDataManager implements InterfaceDataManager {
    private final HelperPreference mPreferencesHelper;

    @Inject
    public MyAppInterfaceDataManager(HelperPreference iPreferenceHelper) {
        Intrinsics.checkNotNullParameter(iPreferenceHelper, "mPreferencesHelper");
        this.mPreferencesHelper = iPreferenceHelper;
    }

    public String getToken() {
        return this.mPreferencesHelper.getToken();
    }

    public void setToken(String str) {
        this.mPreferencesHelper.setToken(str);
    }

    public boolean getGuide() {
        return this.mPreferencesHelper.getGuide();
    }

    public void setGuide(boolean z) {
        this.mPreferencesHelper.setGuide(z);
    }
}
