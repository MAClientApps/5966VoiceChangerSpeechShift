package com.voice.changer.speechshift.myAdsClasses;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;

public class ApplovinBannerAds {

    public static ApplovinBannerAds instance;

    public static String BANNER = "6739135fe1228996";

    public static ApplovinBannerAds getInstance() {
        if (instance == null) {
            instance = new ApplovinBannerAds();
        }
        return instance;
    }

    public void showBannerAds(ViewGroup mAdViewContainer, Activity activity) {
        MaxAdView adView = new MaxAdView(BANNER, activity);
        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        // Get the adaptive banner height.
        int heightDp = MaxAdFormat.BANNER.getAdaptiveSize(activity).getHeight();
        int heightPx = AppLovinSdkUtils.dpToPx(activity, heightDp);

        adView.setLayoutParams(new LinearLayout.LayoutParams(width, heightPx));
        adView.setExtraParameter("adaptive_banner", "true");

        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {
            }

            @Override
            public void onAdCollapsed(MaxAd ad) {
            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                mAdViewContainer.removeAllViews();
                mAdViewContainer.addView(adView);
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
            }

            @Override
            public void onAdHidden(MaxAd ad) {
            }

            @Override
            public void onAdClicked(MaxAd ad) {
            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
            }
        });
        adView.loadAd();
    }

}
