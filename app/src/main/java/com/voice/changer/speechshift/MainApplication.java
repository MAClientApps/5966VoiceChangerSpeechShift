package com.voice.changer.speechshift;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;


import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.ViewModelFactory;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.getApiData.InterfaceDataManager;
import com.voice.changer.speechshift.getApiData.MyAppInterfaceDataManager;
import com.voice.changer.speechshift.getApiData.appScheduler.AppProvider;
import com.voice.changer.speechshift.getApiData.appScheduler.SchedularProvider;
import com.voice.changer.speechshift.getApiData.localData.HelpPrefClass;
import com.voice.changer.speechshift.getApiData.localData.HelperPreference;
import com.voice.changer.speechshift.langClass.PrefManager;


import kotlin.jvm.internal.Intrinsics;

public final class MainApplication extends Application {
    public static MainApplication instance;
    private InterfaceDataManager interfaceDataManager;
    private ViewModelFactory modelFactory;
    private SchedularProvider schedularProvider;
    private static PrefManager prefManager;


    public void onCreate() {
        super.onCreate();
        instance = this;
        prefManager = PrefManager.getInstance(instance);


        AppLovinSdk.getInstance(this).setMediationProvider(AppLovinMediationProvider.MAX);
        AppLovinSdk.initializeSdk(this, appLovinSdkConfiguration -> {
        });

        HelperPreference preferencesHelper = new HelpPrefClass(this, "VoiceChangerSpeechShift");
        SchedularProvider schedularProvider = null;
        this.schedularProvider = new AppProvider();
        this.interfaceDataManager = new MyAppInterfaceDataManager(preferencesHelper);
        Context mContexts = getApplicationContext();
        InterfaceDataManager interfaceDataManager2 = this.interfaceDataManager;
        if (interfaceDataManager2 == null) {
            interfaceDataManager2 = null;
        }
        SchedularProvider provider1 = this.schedularProvider;
        if (provider1 == null) {
        } else {
            schedularProvider = provider1;
        }
        this.modelFactory = new ViewModelFactory(mContexts, interfaceDataManager2, schedularProvider);
        channelNotificationCreate();
    }

    public void requestInjectAct(BaseActivity<?, ?> baseActivity) {
        Intrinsics.checkNotNullParameter(baseActivity, "activity");
        ViewModelFactory modelFactory1 = this.modelFactory;
        SchedularProvider provider = null;
        if (modelFactory1 == null) {
            modelFactory1 = null;
        }
        InterfaceDataManager interfaceDataManager2 = this.interfaceDataManager;
        if (interfaceDataManager2 == null) {
            interfaceDataManager2 = null;
        }
        SchedularProvider provider1 = this.schedularProvider;
        if (provider1 == null) {
        } else {
            provider = provider1;
        }
        baseActivity.inject(modelFactory1, interfaceDataManager2, provider);
    }

    private void channelNotificationCreate() {
        if (Build.VERSION.SDK_INT > 26) {
            NotificationChannel channel = new NotificationChannel(AppConstant.channelId, AppConstant.channelName, NotificationManager.IMPORTANCE_LOW);
            channel.setSound(null, null);
            NotificationManager systemService = getSystemService(NotificationManager.class);
            if (systemService != null) {
                systemService.createNotificationChannel(channel);
            }
        }
    }

    public static PrefManager getPrefManager() {
        return prefManager;
    }

    public static synchronized MainApplication getInstance() {
        MainApplication mainMainApplication1;
        synchronized (MainApplication.class) {
            mainMainApplication1 = instance;
        }
        return mainMainApplication1;
    }


}
