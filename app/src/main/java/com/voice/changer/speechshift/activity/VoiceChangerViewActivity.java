package com.voice.changer.speechshift.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.adjust.sdk.webbridge.AdjustBridge;
import com.voice.changer.speechshift.MainApplication;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.myAdsClasses.ApplovinRewardedAds;

public class VoiceChangerViewActivity extends AppCompatActivity {
    private WebView voiceChangerView;
    LinearLayout containerCheckConnection;
    Button tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_changer_view);
        initView();
        ApplovinRewardedAds.getInstance().loadRewardedAd(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initView() {
        voiceChangerView = findViewById(R.id.voice_changer_view);
        containerCheckConnection = findViewById(R.id.container_check_connection);
        CookieManager.getInstance().setAcceptCookie(true);
        voiceChangerView.getSettings().setJavaScriptEnabled(true);
        voiceChangerView.getSettings().setUseWideViewPort(true);
        voiceChangerView.getSettings().setLoadWithOverviewMode(true);
        voiceChangerView.getSettings().setDomStorageEnabled(true);
        voiceChangerView.getSettings().setPluginState(WebSettings.PluginState.ON);
        voiceChangerView.setWebChromeClient(new WebChromeClient());
        voiceChangerView.setVisibility(View.VISIBLE);

        voiceChangerView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
                String url = request.getUrl().toString();
                if (!url.startsWith("http")) {
                    startHomePage();
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        finish();
                        return;
                    } catch (Exception e) {
                        ApplovinRewardedAds.getInstance().showRewardedIfReady();
                        finish();
                    }
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        loadVoiceChangerView();
    }

    public void checkInternetConnection() {
        containerCheckConnection.setVisibility(View.VISIBLE);
        tryAgain = findViewById(R.id.try_again);
        tryAgain.setOnClickListener(view -> {
            containerCheckConnection.setVisibility(View.GONE);
            loadVoiceChangerView();
        });
    }

    private void loadVoiceChangerView() {
        if (isInternetConnect(this)) {
            AdjustBridge.registerAndGetInstance(getApplication(), voiceChangerView);
            String url = MainApplication.getPrefManager().getEndPKey();
            if (url != null) {
                voiceChangerView.loadUrl(url);
            }
        } else {
            checkInternetConnection();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        voiceChangerView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        voiceChangerView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AdjustBridge.unregister();
        voiceChangerView.loadUrl("about:blank");
    }

    @Override
    public void onBackPressed() {
    }


    void startHomePage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean LANGUAGE_SELECTED_VAR = preferences.getBoolean("langKey", false);
        if (!LANGUAGE_SELECTED_VAR) {
            startActivity(new Intent(this, LanguageActivity.class));
        } else {
            if (hasPermissions(this)) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, PermissionActivity.class));
            }
        }
    }

    public static boolean isInternetConnect(Context mCtx) {
        ConnectivityManager manager;
        NetworkInfo networkInfo = null;
        try {
            manager = (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = manager.getActiveNetworkInfo();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static boolean hasPermissions(Activity activity) {
        int result;
        int result1;
        int result2;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            result1 = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_AUDIO);
            result2 = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
            return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
        } else {
            result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
            result1 = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            result2 = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
        }
    }
}