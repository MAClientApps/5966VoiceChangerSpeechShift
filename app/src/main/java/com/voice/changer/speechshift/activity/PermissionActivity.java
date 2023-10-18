package com.voice.changer.speechshift.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.myAdsClasses.ApplovinOpenAppAds;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.databinding.ActivityPermissionBinding;
import com.voice.changer.speechshift.myAdsClasses.ApplovinRewardedAds;
import com.voice.changer.speechshift.viewModel.PermissionViewModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public class PermissionActivity extends BaseActivity<PermissionViewModel, ActivityPermissionBinding> {

    TextView permissionBtn;
    String[] strArr;
    String[] strArr33;
    boolean ifPer = false;
    private int requestCode = -1;
    private ActivityResultLauncher<Intent> resultHandler = null;
    Dialog settingDialog;
    Dialog permission_dialog;
    private Dialog dialog;
    public boolean aBoolean = false;

    private void permissionCheck() {
        if (AlreadyGranted()) {
            nextAct();
            return;
        }
        requestPermission();
    }

    private void nextAct() {
        ApplovinRewardedAds.getInstance().showRewardedIfReady();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean AlreadyGranted() {
        int result;
        int result1;
        int result2;
        int result3;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            result2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO);
            result3 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS);
            return result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;
        } else {
            result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            result1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
            }
            ActivityCompat.requestPermissions(this, strArr, 1);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            boolean allPermissionsGranted = true;
            if (grantResults.length > 0) {
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        allPermissionsGranted = false;
                        break;
                    }
                }
            }
            if (allPermissionsGranted) {
                if (!ifPer) {
                    ifPer = true;
                    Toast.makeText(this, getString(R.string.grantPermission), Toast.LENGTH_SHORT).show();
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
                    showRationale = shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_AUDIO);
                    showRationale2 = shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO);

                    if (!showRationale && !showRationale2) {
                        openSettingsDialog();
                    } else {
                        permissionDialogShow();
                    }
                } else {
                    showRationale = shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    showRationale1 = shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    showRationale2 = shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO);
                    if (!showRationale && !showRationale1 && !showRationale2) {
                        openSettingsDialog();
                    } else {
                        permissionDialogShow();
                    }
                }
            }
        }

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionCheck();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS)) {
                    requestForSpecificPermission();
                } else {
                    permissionNotAllowed();
                }

            }
        }
    }

    @SuppressLint("InflateParams")
    public void permissionDialogShow() {
        LayoutInflater dialogLayout = LayoutInflater.from(this);
        View DialogView = dialogLayout.inflate(R.layout.dialog_permission, null);
        permission_dialog = new Dialog(this);
        permission_dialog.setContentView(DialogView);
        Objects.requireNonNull(permission_dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
        permission_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        permission_dialog.getWindow().setLayout(-1, -2);
        permission_dialog.setCancelable(false);
        permission_dialog.setCanceledOnTouchOutside(false);


        permission_dialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            permission_dialog.dismiss();
            nextAct();
        });
        permission_dialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            permission_dialog.dismiss();
            new Handler().postDelayed(() -> ApplovinOpenAppAds.isScreenOnOff = true, 500);
            if (AlreadyGranted()) {
                return;
            }
            requestPermission();
        });

        permission_dialog.show();
    }

    @SuppressLint("InflateParams")
    private void openSettingsDialog() {
        LayoutInflater dialogLayout = LayoutInflater.from(this);
        View DialogView = dialogLayout.inflate(R.layout.dialog_permission, null);
        settingDialog = new Dialog(this);
        settingDialog.setContentView(DialogView);
        Objects.requireNonNull(settingDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
        settingDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        settingDialog.getWindow().setLayout(-1, -2);
        settingDialog.setCancelable(false);
        settingDialog.setCanceledOnTouchOutside(false);

        settingDialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            settingDialog.dismiss();
            nextAct();
        });
        settingDialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            settingDialog.dismiss();
            new Handler().postDelayed(() -> ApplovinOpenAppAds.isScreenOnOff = true, 500);
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResults(intent, 100);
        });

        settingDialog.show();

    }

    private void registerForActivityResult() {
        this.resultHandler = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {

            public void onActivityResult(Object var1) {
                this.onActivityResult((ActivityResult) var1);
            }

            public void onActivityResult(ActivityResult result) {
                PermissionActivity.this.onActivityResult(requestCode, result.getResultCode(), result.getData());
                requestCode = -1;
            }
        });
    }

    public void startActivityForResults(Intent intent, int requestCode) {
        this.requestCode = requestCode;
        if (resultHandler != null) {
            resultHandler.launch(intent);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);

        if (i == 100) {
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

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
    }

    public void sendNotification() {
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.S_V2) {
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    private boolean checkIfAlreadyHavePermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void permissionNotAllowed() {
        Dialog dialog = new Dialog(PermissionActivity.this);
        TextView cancel_btn;
        TextView permission_btn;

        this.dialog = dialog;
        this.dialog.setContentView(R.layout.dialog_permission);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.setCancelable(false);
        Objects.requireNonNull(this.dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!this.dialog.isShowing()) {
            this.dialog.show();
        }

        cancel_btn = this.dialog.findViewById(R.id.cancle_btn);
        permission_btn = this.dialog.findViewById(R.id.permission_btn);

        permission_btn.setOnClickListener(view -> {

            PermissionActivity.this.dialog.dismiss();

            new Handler(Looper.getMainLooper()).postDelayed(() -> ApplovinOpenAppAds.isScreenOnOff = true, 500);

            String calendar = Manifest.permission.WRITE_CALENDAR;
            if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, calendar)) {
                ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
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
        cancel_btn.setOnClickListener(view -> nextAct());

    }

    @Override
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
            nextAct();
        }

    }

    @Override
    public void onBackPressed() {
        nextAct();
    }

    @Override
    public void initViews() {
        permissionBtn = findViewById(R.id.txtAllow);

        strArr = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO};
        strArr33 = new String[]{
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_MEDIA_AUDIO};

        registerForActivityResult();

        permissionBtn.setOnClickListener(v -> {
            permissionBtn.setEnabled(false);
            new Handler().postDelayed(() -> permissionBtn.setEnabled(true), 1000);
            permissionCheck();
        });

        ifPer = false;
    }

    @Override
    public Class<PermissionViewModel> createViewModel() {
        return PermissionViewModel.class;
    }

    @Override
    public int getContent() {
        return R.layout.activity_permission;
    }

    @Override
    public void mainView() {
        ApplovinRewardedAds.getInstance().loadRewardedAd(this);
        if (AlreadyGranted()) {
            nextAct();
        }


    }

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {

    }

    @Override
    public void navigateUp() {

    }

    @Override
    public void onFragmentResumed(BaseFragment<?, ?> baseFragment) {

    }

    @Override
    public void switchFragment(KClass<?> kClass, Bundle bundle, boolean z) {

    }

    private void copySimpleToDevice(int ii, String s) throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = getResources().openRawResource(ii);
            Intrinsics.checkNotNullExpressionValue(inputStream, "resources.openRawResource(id)");
            outputStream = new FileOutputStream(s);
            byte[] bytes = new byte[1024];
            while (true) {
                int readInt = inputStream.read(bytes);
                if (readInt > 0) {
                    outputStream.write(bytes, 0, readInt);
                } else {
                    inputStream.close();
                    outputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            inputStream.close();
            outputStream.close();
            throw th;
        }
    }


}