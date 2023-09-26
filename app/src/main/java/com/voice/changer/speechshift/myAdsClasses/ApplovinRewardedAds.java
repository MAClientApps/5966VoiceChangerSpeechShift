package com.voice.changer.speechshift.myAdsClasses;

import android.app.Activity;
import android.os.Handler;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;

import java.util.concurrent.TimeUnit;

public class ApplovinRewardedAds {

    public static ApplovinRewardedAds instance;
    private MaxRewardedAd rewardedAd;
    private int retryAttempt = 0;

    public static String REWARDED = "a739afc1ca8298f5";

    public static ApplovinRewardedAds getInstance() {
        if (instance == null) {
            instance = new ApplovinRewardedAds();
        }
        return instance;
    }

    public void loadRewardedAd(Activity activity) {
        rewardedAd = MaxRewardedAd.getInstance(REWARDED, activity);
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
            }

            @Override
            public void onRewardedVideoStarted(MaxAd maxAd) {
            }

            @Override
            public void onRewardedVideoCompleted(MaxAd maxAd) {
            }

            @Override
            public void onAdLoaded(MaxAd maxAd) {
                retryAttempt = 0;
            }

            @Override
            public void onAdDisplayed(MaxAd maxAd) {
            }

            @Override
            public void onAdHidden(MaxAd maxAd) {
                // rewarded ad is hidden. Pre-load the next ad
                rewardedAd.loadAd();
            }

            @Override
            public void onAdClicked(MaxAd maxAd) {
            }

            @Override
            public void onAdLoadFailed(String s, MaxError maxError) {
                // Rewarded ad failed to load
                // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)
                retryAttempt++;
                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rewardedAd.loadAd();
                    }
                }, delayMillis);
            }

            @Override
            public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                // Rewarded ad failed to display. AppLovin recommends that you load the next ad.
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
    }

    public void showRewardedIfReady() {
        if (rewardedAd.isReady()) {
            rewardedAd.showAd();
        }
    }

}
