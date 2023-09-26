package com.voice.changer.speechshift.myAdsClasses;

import android.app.Activity;
import android.os.Handler;


import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;

import java.util.concurrent.TimeUnit;

public class ApplovinInterAds {

    public static String INTER = "83dafd9f12f92a8f";
    public static MaxInterstitialAd sMaxInterstitialAd;
    public static int ADS_COUNTER = 0;

    private static int tryAdAttempt;

    public static ApplovinInterAds instance;


    public static ApplovinInterAds getInstance() {
        if (instance == null) {
            instance = new ApplovinInterAds();
        }
        return instance;
    }

    public void loadInterstitialAd(Activity activity) {
        try {
            if (sMaxInterstitialAd == null) {
                sMaxInterstitialAd = new MaxInterstitialAd(INTER, activity);
                sMaxInterstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd maxAd) {
                        tryAdAttempt = 0;
                    }

                    @Override
                    public void onAdDisplayed(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdHidden(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdClicked(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdLoadFailed(String s, MaxError maxError) {
                        tryAdAttempt++;
                        long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, tryAdAttempt)));
                        new Handler().postDelayed(() -> sMaxInterstitialAd.loadAd(), delayMillis);
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                        sMaxInterstitialAd.loadAd();
                    }
                });
                sMaxInterstitialAd.loadAd();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInterstitialAd() {
        try {
            ADS_COUNTER++;
            if (ADS_COUNTER == 3) {
                if (sMaxInterstitialAd != null && sMaxInterstitialAd.isReady()) {
                    sMaxInterstitialAd.showAd();
                }
                ADS_COUNTER = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}