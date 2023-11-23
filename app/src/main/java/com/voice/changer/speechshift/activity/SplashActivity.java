package com.voice.changer.speechshift.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.airbnb.lottie.LottieAnimationView;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.databinding.ActivitySplashBinding;
import com.voice.changer.speechshift.viewModel.SplashActViewModel;


import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public final class SplashActivity extends BaseActivity<SplashActViewModel, ActivitySplashBinding> {
    public static boolean IntentFromSetting;

    public void initViews() {
    }

    public static SplashActivity splashActivity;

    public Class<SplashActViewModel> createViewModel() {
        return SplashActViewModel.class;
    }

    public int getContent() {
        return R.layout.activity_splash;
    }

    public void navigate(int i, Bundle bundle, boolean z) {
    }

    public void navigateUp() {
    }

    public void onFragmentResumed(BaseFragment<?, ?> baseFragment) {
        Intrinsics.checkNotNullParameter(baseFragment, "fragment");
    }

    public void switchFragment(KClass<?> kClass, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(kClass, "fragment");
    }

    public void mainView() {

        lottieAnimationView = findViewById(R.id.animation_view);

        isNoNetwork = false;
        actPauseRes = false;
        splashActivity = this;


        showNetworkDialog();

        if (isInternetConnect(SplashActivity.this)) {
            startIntentTimer(millSec);
        } else {
            dialogShow();
        }

        try {
            try {
                NetworkRequest request = null;
                request = new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build();
                ConnectivityManager systemService = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                systemService.requestNetwork(request, callback);
            } catch (Exception e) {
                e.printStackTrace();
                forActivityResults();

                Intent intent1 = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent1.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult1(intent1, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            forActivityResults();

            Intent intent22 = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent22.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult1(intent22, 100);
        }
    }


    Handler handler;
    Runnable mRunnable;
    public static boolean actPauseRes = false;
    public int millSec = 1000;
    public static boolean isNoNetwork = false;
    public static LottieAnimationView lottieAnimationView;
    private int requestCode = -1;
    private ActivityResultLauncher<Intent> resultLauncher = null;
    Dialog dialogNetwork;
    boolean isShowingDismissed;


    private final void forActivityResults() {
        this.resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {

            public void onActivityResult(Object result) {
                this.onActivityResult((ActivityResult) result);
            }

            public void onActivityResult(ActivityResult result) {
                SplashActivity.this.onActivityResult(requestCode, result.getResultCode(), result.getData());
                requestCode = -1;
            }
        });

    }

    ConnectivityManager.NetworkCallback callback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network net) {
            super.onAvailable(net);
            if (!isFinishing()) {
                runOnUiThread(() -> {
                    dismissDialog();
                    isShowingDismissed = false;
                });
            }
        }

        @Override
        public void onLost(@NonNull Network net) {
            super.onLost(net);
            if (!isFinishing()) {
                runOnUiThread(() -> dialogShow());
            }
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean hasCapability = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
        }
    };

    public void startActivityForResult1(Intent intent, int requestCode) {
        this.requestCode = requestCode;
        if (resultLauncher != null) {
            resultLauncher.launch(intent);
        }
    }

    public boolean checkConnection(Context context) {
        ConnectivityManager cm;
        NetworkInfo netInfo = null;
        try {
            cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = cm.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            try {
                try {
                    NetworkRequest request = null;
                    request = new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build();
                    ConnectivityManager systemService = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    systemService.requestNetwork(request, callback);
                } catch (Exception e) {
                    e.printStackTrace();

                    Intent intentA = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intentA.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResult1(intentA, 100);
                }
            } catch (Exception e) {
                e.printStackTrace();

                Intent intentV = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intentV.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult1(intentV, 100);
            }
        }
    }

    private void showNetworkDialog() {
        dialogNetwork = new Dialog(SplashActivity.this, R.style.BottomSheetDialogTheme);
        dialogNetwork.setContentView(R.layout.dialog_no_net);
        dialogNetwork.setCanceledOnTouchOutside(false);

        TextView wifi = dialogNetwork.findViewById(R.id.wifi);
        TextView data = dialogNetwork.findViewById(R.id.data);
        ImageView close = dialogNetwork.findViewById(R.id.imgClose);

        Objects.requireNonNull(dialogNetwork.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogNetwork.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogNetwork.getWindow().setGravity(Gravity.CENTER);

        wifi.setOnClickListener(v -> {
            dialogNetwork.dismiss();
            OnWIFI(SplashActivity.this);
        });

        data.setOnClickListener(v -> {
            dialogNetwork.dismiss();
            OnData(SplashActivity.this);
        });

        close.setOnClickListener(v -> {
            dismissDialog();
            isShowingDismissed = false;
        });

        dialogNetwork.setOnDismissListener(dialogInterface -> {

            if (!isShowingDismissed) {
                isShowingDismissed = false;
                if (checkConnection(SplashActivity.this)) {
                    startIntentTimer(millSec);
                } else {

                    if (handler == null || mRunnable == null) {
                        handler = new Handler();
                        mRunnable = () -> {
                            if (checkConnection(SplashActivity.this)) {
                                startIntentTimer(millSec);
                            } else {
                                if (!isFinishing()) {
                                    dialogShow();
                                }
                            }
                        };
                    }
                    handler.postDelayed(mRunnable, 3000);
                }
            }
        });

    }

    public void OnWIFI(Context ctx) {
        try {
            ctx.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(SplashActivity.this, R.string.it_van_not_open, Toast.LENGTH_LONG).show();
        }
    }

    public void OnData(Context ctx) {
        if (isSimSupport(this)) {
            try {
                ctx.startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(ctx, (int) R.string.it_van_not_open, Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(ctx, (int) R.string.sim_not_available, Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isSimSupport(SplashActivity splashActivity) {
        TelephonyManager telephonyManager = (TelephonyManager) splashActivity.getSystemService(Context.TELEPHONY_SERVICE);
        return !(telephonyManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT);
    }

    private void startIntentTimer(int milli) {
        lottieAnimationView.resumeAnimation();


        handler = new Handler();

        mRunnable = () -> {
            if (checkConnection(SplashActivity.this)) {


                startHomePage();

            } else {
                if (!isFinishing()) {

                    dialogShow();
                }
            }

        };

        handler.postDelayed(mRunnable, milli);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (actPauseRes) {
            actPauseRes = false;
            if (checkConnection(SplashActivity.this)) {
                dismissDialog();
                startIntentTimer(millSec);
            } else {
                isShowingDismissed = false;
                dialogShow();
            }
        }

    }

    public void dismissDialog() {
        if (dialogNetwork != null && dialogNetwork.isShowing()) {
            isNoNetwork = false;

            dialogNetwork.dismiss();
            isShowingDismissed = true;
        }

    }

    public void dialogShow() {
        if (dialogNetwork != null && !dialogNetwork.isShowing()) {
            isNoNetwork = true;
            dialogNetwork.show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        dismissDialog();

        actPauseRes = true;
        if (handler != null && mRunnable != null) {
            handler.removeCallbacks(mRunnable);
        }
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
        finish();
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
