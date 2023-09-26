package com.voice.changer.speechshift.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.Observable;
import androidx.databinding.ObservableInt;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.myAdsClasses.ApplovinBannerAds;
import com.voice.changer.speechshift.myAdsClasses.ApplovinInterAds;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.allDialogs.NotRecordedDialog;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.custUi.LiveEvents;
import com.voice.changer.speechshift.custUi.MobileState;
import com.voice.changer.speechshift.custUi.RecordAudioType;
import com.voice.changer.speechshift.custUi.SetLanguage;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.ActivityRecordingBinding;
import com.voice.changer.speechshift.getApiData.allModel.RecordingModel;
import com.voice.changer.speechshift.recordingServices.ServiceRecordingVoice;
import com.voice.changer.speechshift.viewModel.RecordingViewModel;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;


public final class RecordingActivity extends BaseActivity<RecordingViewModel, ActivityRecordingBinding> {
    private int recorderSecondsElapsed = -1;
    private int playerSecondsElapsed;
    private Timer timer;
    private boolean isRecording;

    public FrameLayout ads;
    public RelativeLayout llyBanner;

    public boolean isFirstCallBack = true;
    public boolean isFirst = true;
    public Observable.OnPropertyChangedCallback callback;
    public RecordAudioType stateAudio = RecordAudioType.STATE_PREPARE;
    private RecordingActivity recordingActivity;

    public Class<RecordingViewModel> createViewModel() {
        return RecordingViewModel.class;
    }

    public int getContent() {
        recordingActivity = this;
        return R.layout.activity_recording;
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
        Context context = this;
        ApplovinInterAds.getInstance().loadInterstitialAd(RecordingActivity.this);
        ApplovinInterAds.getInstance().showInterstitialAd();
        SetLanguage.setLocale(context);
        getBindingData().toolbar.tvTitle.setText(R.string.record_voice);
        showHideExRecord();
        getMViewModel().connectService(ServiceRecordingVoice.makeIntent(context, true));
        this.isFirst = true;


        ads = findViewById(R.id.ads);
        llyBanner = findViewById(R.id.ll_banner);

        ApplovinBannerAds.getInstance().showBannerAds(llyBanner, RecordingActivity.this);

    }

    @SuppressLint("SetTextI18n")
    public void initViews() {
        TapClick.tap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            onBackPressed();
            return null;
        });
        TapClick.tap(getBindingData().ivReset, (Function1<View, Unit>) view -> {
            String string = recordingActivity.getResources().getString(R.string.audio_has_not_been_saved_reset);
            String string2 = getResources().getString(R.string.reset);
            showDialogNotSaved(true, false, string, string2);
            return null;
        });

        getBindingData().icStart.setOnClickListener(v -> {
            getBindingData().txtStartRecord.setText("Recording...");
            getBindingData().txtTitle.setVisibility(View.VISIBLE);
            getBindingData().txtTitle.setText("Recording...");
            startRecordAudio();
            getBindingData().icStart.setClickable(false);
            getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
            getBindingData().rlyBottom.setVisibility(View.VISIBLE);
            getBindingData().ads.setVisibility(View.VISIBLE);
            getBindingData().imgRecord.setVisibility(View.VISIBLE);
            getBindingData().txtExtra.setVisibility(View.GONE);

        });

        TapClick.tap(getBindingData().imgRecord, (Function1<View, Unit>) view -> {

            int[] iArr = new int[RecordAudioType.values().length];
            iArr[RecordAudioType.STATE_PREPARE.ordinal()] = 1;
            iArr[RecordAudioType.STATE_START.ordinal()] = 2;
            iArr[RecordAudioType.STATE_PAUSE.ordinal()] = 3;
            iArr[RecordAudioType.STATE_STOP.ordinal()] = 4;
            int[] TimerArray = iArr;

            int i = TimerArray[stateAudio.ordinal()];
            if (i == 1) {
                startRecordAudio();
                getBindingData().rlyBottom.setVisibility(View.VISIBLE);
                getBindingData().ads.setVisibility(View.VISIBLE);
                getBindingData().imgRecord.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                pauseRecordAudio();
                startStopRecording();
                getBindingData().rlyBottom.setVisibility(View.VISIBLE);
                getBindingData().ads.setVisibility(View.VISIBLE);
                getBindingData().imgRecord.setVisibility(View.VISIBLE);
            } else {
                startRecordAudio();
                getBindingData().rlyBottom.setVisibility(View.VISIBLE);
                getBindingData().ads.setVisibility(View.VISIBLE);
                getBindingData().imgRecord.setVisibility(View.VISIBLE);
            }
            return null;
        });

        TapClick.tap(getBindingData().imgStop, (Function1<View, Unit>) view -> {
            isFirst = false;
            getMViewModel().recStop();
            stopAnim();
            stopTimer();
            ObservableInt secondsElapsed = getMViewModel().getObservableInt();
            Observable.OnPropertyChangedCallback access$getSecsCallback$p = callback;
            Intrinsics.checkNotNull(access$getSecsCallback$p);
            secondsElapsed.removeOnPropertyChangedCallback(access$getSecsCallback$p);
            getWindow().clearFlags(128);
            Observable.OnPropertyChangedCallback secsCallback1 = callback;
            if (secsCallback1 != null) {
                getMViewModel().getObservableInt().removeOnPropertyChangedCallback(secsCallback1);
            }
            LiveEvents<RecordingModel> recording = getMViewModel().getRecording();
            recording.observe(recordingActivity, recordingModel -> {
                if (recordingModel != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), recordingModel.getPath());
                    bundle.putString(AppConstant.APP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS(), "RecordActivity");
                    nextActivity(ChangeEffectActivity.class, bundle);
                    getBindingData().icStart.setClickable(true);
                    getBindingData().txtTitle.setVisibility(View.GONE);
                    getBindingData().txtStartRecord.setText("Start Record");
                    getBindingData().txtExtra.setVisibility(View.VISIBLE);
                    getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
                }
            });
            stateAudio = RecordAudioType.STATE_PREPARE;
            showHideExRecord();
            return null;
        });
    }

    public void startRecordAudio() {
        this.stateAudio = RecordAudioType.STATE_START;
        getBindingData().imgRecord.setImageResource(R.drawable.ic_recording_pause);
        getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
        startStopRecording();
    }

    public void pauseRecordAudio() {
        this.stateAudio = RecordAudioType.STATE_PAUSE;
        getBindingData().imgRecord.setImageResource(R.drawable.ic_recording_play);
    }

    private void stopRecordAudio() {
        this.stateAudio = RecordAudioType.STATE_STOP;
        getBindingData().imgRecord.setImageResource(R.drawable.ic_start_record);
        getBindingData().txtStartRecord.setText(R.string.start_record);
        getBindingData().txtTitle.setVisibility(View.GONE);

    }

    public void showHideExRecord() {
        int[] iArr = new int[MobileState.values().length];
        iArr[MobileState.STATE_RINGTONE.ordinal()] = 1;
        iArr[MobileState.STATE_ALARM.ordinal()] = 2;
        iArr[MobileState.STATE_NOTIFICATION.ordinal()] = 3;
        int[] enumSwitchMapping = iArr;

        int i = enumSwitchMapping[this.stateAudio.ordinal()];
        if (i == 1) {
            recorderSecondsElapsed = 0;
            playerSecondsElapsed = 0;

            getBindingData().imgRecord.setClickable(true);
            getBindingData().imgRecord.setImageResource(R.drawable.ic_start_record);
            getBindingData().rlyBottom.setVisibility(View.GONE);
            getBindingData().ads.setVisibility(View.GONE);
            getBindingData().imgRecord.setVisibility(View.GONE);
            getBindingData().txtTitle.setVisibility(View.GONE);
            getBindingData().txtStartRecord.setText(R.string.start_record);

        }
    }

    public void onResume() {
        super.onResume();
        this.callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Intrinsics.checkNotNullParameter(sender, "sender");
                if (isFirstCallBack) {
                    isFirstCallBack = false;
                }
            }
        };
        ObservableInt elapsed = getMViewModel().getObservableInt();
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.callback;
        Objects.requireNonNull(onPropertyChangedCallback, "null cannot be cast to non-null type androidx.databinding.Observable.OnPropertyChangedCallback");
        elapsed.addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    public void onPause() {
        super.onPause();
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.callback;
        if (onPropertyChangedCallback != null) {
            getMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }
    }

    public void showDialogNotSaved(boolean z, boolean z2, String str, String str2) {
        if (z) {
            new NotRecordedDialog(str, str2, this, true, () -> {
                stateAudio = RecordAudioType.STATE_PREPARE;
                resetFileRecord();
                showHideExRecord();
                if (z2) {
                    finish();
                }
                return null;
            }).show();
            return;
        }
        stopRecordAudio();
        finish();
    }


    public void startStopRecording() {
        this.isFirstCallBack = false;
        if (!getMViewModel().getServiceRecording().get()) {
            this.isFirst = false;
            isRecording = true;
            getMViewModel().recStart();
            getWindow().addFlags(128);
            startAnim();
            startTimer();
            return;
        }
        pauseRecord();
    }

    private void pauseRecord() {
        if (!getMViewModel().getServiceRecordResume().get()) {
            getMViewModel().recResume();
            startAnim();
            startTimer();
            if (this.isFirst) {
                this.isFirst = false;
                this.isFirstCallBack = true;
            }
            this.callback = new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int propertyId) {
                    Intrinsics.checkNotNullParameter(observable, "sender");
                    if (isFirstCallBack) {
                        isFirstCallBack = false;
                    }
                }
            };
            ObservableInt elapsed = getMViewModel().getObservableInt();
            Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.callback;
            Objects.requireNonNull(onPropertyChangedCallback, "null cannot be cast to non-null type androidx.databinding.Observable.OnPropertyChangedCallback");
            elapsed.addOnPropertyChangedCallback(onPropertyChangedCallback);
            return;
        }
        this.isFirst = false;
        getMViewModel().recPause();
        pauseAnim();
        stopTimer();
        Observable.OnPropertyChangedCallback onPropertyChangedCallback2 = this.callback;
        if (onPropertyChangedCallback2 != null) {
            getMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback2);
        }
        isRecording = false;
    }


    @SuppressLint("SetTextI18n")
    public void resetFileRecord() {
        this.isFirst = false;
        getMViewModel().recSkipFile();
        stopAnim();
        stopTimer();
        getWindow().clearFlags(128);
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.callback;
        getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
        getBindingData().txtExtra.setVisibility(View.VISIBLE);
        getBindingData().icStart.setClickable(true);
        getBindingData().txtTitle.setVisibility(View.GONE);
        getBindingData().txtStartRecord.setText("Start Record");
        if (onPropertyChangedCallback != null) {
            getMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }

        recorderSecondsElapsed = 0;
        playerSecondsElapsed = 0;
        getBindingData().txtStartRecord.setText(R.string.start_record);
    }

    public void onDestroy() {
        super.onDestroy();
        Context context = this;
        getMViewModel().serviceStartStop(new Intent(context, ServiceRecordingVoice.class));
        stopService(new Intent(context, ServiceRecordingVoice.class));
        stopAnim();
        stopTimer();
    }

    public void onBackPressed() {
        if (getBindingData().icStart.isClickable()) {
            ApplovinInterAds.getInstance().showInterstitialAd();
            super.onBackPressed();
            return;
        }

        boolean isShown = getBindingData().ivReset.isShown();
        String string = getResources().getString(R.string.audio_has_not_been_saved);
        String string2 = getResources().getString(R.string.exit);
        showDialogNotSaved(isShown, true, string, string2);
    }

    public void startAnim() {
        getBindingData().recordLottie.setVisibility(View.VISIBLE);
        getBindingData().recordLottie.playAnimation();
    }

    public void pauseAnim() {
        getBindingData().recordLottie.pauseAnimation();
    }

    public void stopAnim() {
        getBindingData().recordLottie.setVisibility(View.INVISIBLE);
        getBindingData().recordLottie.cancelAnimation();
    }


    private void startTimer() {
        stopTimer();
        Timer timer = new Timer();
        this.timer = timer;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimer();
            }
        }, 800L, 1000L);
    }

    private void stopTimer() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.purge();
            this.timer.cancel();
            this.timer = null;
        }
    }

    public void updateTimer() {
        runOnUiThread(() -> {
            TextView textView;
            int i;
            if (!isRecording) {
                playerSecondsElapsed++;
                textView = getBindingData().txtStartRecord;
                i = playerSecondsElapsed;
            } else {
                recorderSecondsElapsed++;
                textView = getBindingData().txtStartRecord;
                i = recorderSecondsElapsed;
            }
            textView.setText(formatSeconds(i));
            playerSecondsElapsed = i;
        });
    }


    public static String formatSeconds(int i) {
        return getTwoDecimalsValue(i / 86400) + ":" + getTwoDecimalsValue(i / 3600) + ":" + getTwoDecimalsValue(i / 60) + ":" + getTwoDecimalsValue(i % 60);
    }

    private static String getTwoDecimalsValue(int i) {
        StringBuilder sb;
        if (i < 0 || i > 9) {
            sb = new StringBuilder();
            sb.append(i);
            sb.append("");
        } else {
            sb = new StringBuilder();
            sb.append("0");
            sb.append(i);
        }
        return sb.toString();
    }


}
