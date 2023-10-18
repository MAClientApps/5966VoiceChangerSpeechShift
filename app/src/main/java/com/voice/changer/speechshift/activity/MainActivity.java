package com.voice.changer.speechshift.activity;

import static com.voice.changer.speechshift.activity.SplashActivity.IntentFromSetting;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import com.voice.changer.speechshift.BroadcastReceiver;
import com.voice.changer.speechshift.BuildConfig;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.ActivityMainBinding;
import com.voice.changer.speechshift.myAdsClasses.ApplovinBannerAds;
import com.voice.changer.speechshift.myAdsClasses.ApplovinRewardedAds;
import com.voice.changer.speechshift.myAdsClasses.ApplovinOpenAppAds;
import com.voice.changer.speechshift.viewModel.MainActViewModel;

import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public final class MainActivity extends BaseActivity<MainActViewModel, ActivityMainBinding> {


    public int MY_REQUEST_CODE = 111;

    String[] strArr;
    String[] strArr33;
    boolean ifPer = false;
    Dialog settingDialog;
    Dialog permission_dialog;
    private Dialog dialog;
    private int requestCode = -1;
    public boolean aBoolean = false;
    private ActivityResultLauncher<Intent> resultHandler = null;

    public FrameLayout ads;
    public RelativeLayout llyBanner;

    private AlertDialog alertDialog;
    public boolean isResumeApp;
    Dialog exitDialog;

    ApplovinOpenAppAds applovinOpenAppAds;

    OnBackPressedCallback onBackPressedCallback;

    public Class<MainActViewModel> createViewModel() {
        return MainActViewModel.class;
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public int getContent() {

        strArr = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO};
        strArr33 = new String[]{Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_MEDIA_AUDIO};


        BroadcastReceiver broadcastReceiver = new BroadcastReceiver();
        registerReceiver(broadcastReceiver, broadcastReceiver.getDataFilter());

        permissionCheck();
        registerForActivityResult();


        return R.layout.activity_main;
    }

    public void navigate(int i, Bundle bundle2, boolean z) {
    }

    public void navigateUp() {
    }

    public void onFragmentResumed(BaseFragment<?, ?> baseFragment) {
        Intrinsics.checkNotNullParameter(baseFragment, "fragment");
    }

    public void switchFragment(KClass<?> kClass, Bundle bundle2, boolean z) {
        Intrinsics.checkNotNullParameter(kClass, "fragment");
    }

    public void mainView() {
        applovinOpenAppAds = new ApplovinOpenAppAds(this);
        applovinOpenAppAds.onStart();

        onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getBindingData().drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    getBindingData().drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    exitDialog.show();
                }
            }
        };

        // Add the callback to the dispatcher.
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        showExitDialog();

        AlertDialog dialog1 = new AlertDialog.Builder(this, R.style.AlertDialogCustom).create();
        this.alertDialog = dialog1;
        AlertDialog alertDialog2 = null;
        dialog1.setTitle(getString(R.string.Grant_Permission));
        AlertDialog alertDialog1 = this.alertDialog;
        if (alertDialog1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
            alertDialog1 = null;
        }
        alertDialog1.setCancelable(false);
        AlertDialog alertDialog4 = this.alertDialog;
        if (alertDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
            alertDialog4 = null;
        }
        alertDialog4.setMessage(getString(R.string.Please_grant_all_permissions));
        AlertDialog dialog2 = this.alertDialog;
        if (dialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
        } else {
            alertDialog2 = dialog2;
        }

        alertDialog2.setButton(-1, getString(R.string.Go_to_setting), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppConstant.APP_CONSTANT.setCheckResumePermissionMain(true);
                AlertDialog alertDialog2 = alertDialog;
                if (alertDialog2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
                    alertDialog2 = null;
                }
                alertDialog2.dismiss();
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO}, 1112);
                Intent strIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                strIntent.setData(Uri.fromParts("package", getApplicationContext().getPackageName(), (String) null));
                startActivity(strIntent);
            }
        });


        ads = findViewById(R.id.ads);
        llyBanner = findViewById(R.id.ll_banner);

        ApplovinRewardedAds.getInstance().loadRewardedAd(MainActivity.this);
        ApplovinBannerAds.getInstance().showBannerAds(llyBanner, MainActivity.this);

    }

    private void showExitDialog() {
        exitDialog = new Dialog(MainActivity.this, R.style.BottomSheetDialogTheme);
        exitDialog.setContentView(R.layout.dialog_exit);
        exitDialog.setCancelable(true);
        exitDialog.setCanceledOnTouchOutside(false);
        Objects.requireNonNull(exitDialog.getWindow()).setLayout(-1, -2);
        exitDialog.getWindow().setGravity(Gravity.CENTER);
        exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView btnExit = exitDialog.findViewById(R.id.btnExit);
        ImageView imgClose = exitDialog.findViewById(R.id.imgClose);

        imgClose.setOnClickListener(v -> exitDialog.dismiss());

        btnExit.setOnClickListener(v -> {
            exitDialog.dismiss();
            finishAffinity();
        });
    }

    public void initViews() {
        toolBar();
        mainEvents();
    }

    private void toolBar() {
        TapClick.tap(getBindingData().toolbar.ivMenu, (Function1<View, Unit>) view -> {
            getBindingData().drawerLayout.openDrawer(GravityCompat.START);
            return null;
        });

        TapClick.tap(getBindingData().layoutContent.llLanguage, (Function1<View, Unit>) view -> {
            IntentFromSetting = true;
            closeDrawer();
            ApplovinRewardedAds.getInstance().showRewardedIfReady();
            startActivity(new Intent(MainActivity.this, LanguageActivity.class));
            return null;
        });

        getBindingData().layoutContent.txtCancel.setOnClickListener(v -> {
            if (getBindingData().drawerLayout.isDrawerOpen(GravityCompat.START)) {
                getBindingData().drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        TapClick.tap(getBindingData().layoutContent.llRateUs, (Function1<View, Unit>) view -> {
            closeDrawer();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            return null;
        });

        TapClick.tap(getBindingData().layoutContent.llShare, (Function1<View, Unit>) view -> {
            AppConstant.APP_CONSTANT.setCheckResumeShare(true);
            closeDrawer();
            appShare();
            return null;
        });

        TapClick.tap(getBindingData().layoutContent.llPolicy, (Function1<View, Unit>) view -> {
            closeDrawer();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://doc-hosting.flycricket.io/voice-changer-speech-shift-privacy-policy/44991b96-f44d-4f6a-8ffa-637dab4d46bd/privacy"));
            startActivity(intent);
            return null;
        });
    }

    private void mainEvents() {

        TapClick.tap(getBindingData().llRecord, (Function1<View, Unit>) view -> {
            ApplovinRewardedAds.getInstance().showRewardedIfReady();
            nextActivity(RecordingActivity.class, null);
            return null;
        });

        TapClick.tap(getBindingData().llOpenFile, (Function1<View, Unit>) view -> {
            ApplovinRewardedAds.getInstance().showRewardedIfReady();
            nextActivity(OpenFileActivity.class, null);
            return null;
        });

        TapClick.tap(getBindingData().llTextAudio, (Function1<View, Unit>) view -> {
            ApplovinRewardedAds.getInstance().showRewardedIfReady();
            nextActivity(TxtToAudioActivity.class, null);
            return null;
        });

        TapClick.tap(getBindingData().llMyVoice, (Function1<View, Unit>) view -> {
            ApplovinRewardedAds.getInstance().showRewardedIfReady();
            nextActivity(CreationActivity.class, null);
            return null;
        });
    }


    public void onResume() {
        super.onResume();

        if (aBoolean) {
            aBoolean = false;
            sendNotification();
        }

        if (AlreadyGranted()) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            } else if (permission_dialog != null && permission_dialog.isShowing()) {
                permission_dialog.dismiss();
            } else if (settingDialog != null && settingDialog.isShowing()) {
                settingDialog.dismiss();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (exitDialog != null && exitDialog.isShowing()) {
            new Handler().postDelayed(() -> ApplovinOpenAppAds.isScreenOnOff = true, 500);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public void appShare() {
        try {
            this.isResumeApp = true;
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            String msgShare;
            msgShare = getResources().getString(R.string.appShare) + "\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            intentShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            intentShare.putExtra(Intent.EXTRA_TEXT, msgShare);
            new Handler().postDelayed(() ->
                    startActivity(Intent.createChooser(intentShare, getString(R.string.share_to))), 250);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeDrawer() {
        if (getBindingData().drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getBindingData().drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (getBindingData().drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getBindingData().drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            exitDialog.show();
        }
    }

    @SuppressLint("InflateParams")
    public void permissionDialog() {
        LayoutInflater dialogLayout = LayoutInflater.from(MainActivity.this);
        View DialogView = dialogLayout.inflate(R.layout.dialog_permission, null);
        permission_dialog = new Dialog(MainActivity.this);
        permission_dialog.setContentView(DialogView);
        Objects.requireNonNull(permission_dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
        permission_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        permission_dialog.getWindow().setLayout(-1, -2);
        permission_dialog.setCancelable(false);
        permission_dialog.setCanceledOnTouchOutside(false);

        permission_dialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            permission_dialog.dismiss();
            if (AlreadyGranted()) {
                return;
            }
            requestPermission();
        });

        permission_dialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            permission_dialog.dismiss();
            finishAffinity();
        });
        permission_dialog.show();
    }

    @SuppressLint("InflateParams")
    private void openSettingsDialog() {
        LayoutInflater dialogLayout = LayoutInflater.from(MainActivity.this);
        View DialogView = dialogLayout.inflate(R.layout.dialog_permission, null);
        settingDialog = new Dialog(MainActivity.this);
        settingDialog.setContentView(DialogView);
        Objects.requireNonNull(settingDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
        settingDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        settingDialog.getWindow().setLayout(-1, -2);

        settingDialog.setCancelable(false);
        settingDialog.setCanceledOnTouchOutside(false);
        settingDialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            settingDialog.dismiss();
            finishAffinity();
        });

        settingDialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            MainActivity.this.settingDialog.dismiss();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult1(intent, 100);
        });
        settingDialog.show();

    }

    private void registerForActivityResult() {
        this.resultHandler = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            public void onActivityResult(Object var1) {
                this.onActivityResult((ActivityResult) var1);
            }

            public void onActivityResult(ActivityResult result) {
                MainActivity.this.onActivityResult(requestCode, result.getResultCode(), result.getData());
                requestCode = -1;
            }
        });
    }

    private boolean AlreadyGranted() {
        int result;
        int result1;
        int result2;
        int result3;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO);
            result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            result3 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS);
            return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;
        } else {
            result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_AUDIO)) {
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
            }
            ActivityCompat.requestPermissions(this, strArr33, 1);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE);
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
            }
            ActivityCompat.requestPermissions(this, strArr, 1);
        }

    }

    public void startActivityForResult1(Intent intent, int requestCode) {
        this.requestCode = requestCode;
        if (resultHandler != null) {
            resultHandler.launch(intent);
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
    }

    public void sendNotification() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private boolean checkIfAlreadyHavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void permissionCheck() {
        if (AlreadyGranted()) {
            return;
        }
        requestPermission();
    }

    private void permissionNotAllowed() {

        Dialog dialog = new Dialog(MainActivity.this);
        TextView yes;
        TextView cancel;

        this.dialog = dialog;
        this.dialog.setContentView(R.layout.dialog_permission);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.setCancelable(false);
        Objects.requireNonNull(this.dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!this.dialog.isShowing()) {
            this.dialog.show();
        }

        yes = this.dialog.findViewById(R.id.permission_btn);
        cancel = this.dialog.findViewById(R.id.cancle_btn);

        yes.setOnClickListener(view -> {
            MainActivity.this.dialog.dismiss();
            String calendar = Manifest.permission.WRITE_CALENDAR;
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, calendar)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
                return;
            }
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            aBoolean = true;
            startActivity(intent);
        });

        cancel.setOnClickListener(view -> {
            dialog.dismiss();
            finishAffinity();
        });

    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);

        if (i == 1) {
            boolean allPermissionsGranted = true;
            if (iArr.length > 0) {
                for (int grantResult : iArr) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        allPermissionsGranted = false;
                        break;
                    }
                }
            }
            if (allPermissionsGranted) {
                if (!ifPer) {
                    ifPer = true;
                    sendNotification();
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2) {
                    permissionCheck();
                }
            } else {
                boolean showRationale;
                boolean showRationale1;
                boolean showRationale2;
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
                    showRationale1 = shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_AUDIO);
                    showRationale2 = shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO);
                    if (!showRationale1 && !showRationale2) {
                        openSettingsDialog();
                    } else {
                        permissionDialog();
                    }
                } else {
                    showRationale = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    showRationale1 = shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE);
                    showRationale2 = shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO);
                    if (!showRationale && !showRationale1 && !showRationale2) {
                        openSettingsDialog();
                    } else {
                        permissionDialog();
                    }
                }
            }
        }

        if (i == 101) {
            if (iArr[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.grantPermission), Toast.LENGTH_SHORT).show();
                permissionCheck();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
                    requestForSpecificPermission();
                } else {
                    permissionNotAllowed();
                }
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // For Update Dialog
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
            }
        }


        if (requestCode == 100) {
            if (AlreadyGranted()) {
                Toast.makeText(this, getString(R.string.grantPermission), Toast.LENGTH_SHORT).show();
                sendNotification();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2) {
                    permissionCheck();
                }
                return;
            }
            requestPermission();
        }
    }


}
