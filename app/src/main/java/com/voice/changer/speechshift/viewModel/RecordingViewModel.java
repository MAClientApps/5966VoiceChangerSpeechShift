package com.voice.changer.speechshift.viewModel;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.voice.changer.speechshift.allBaseAct.BaseViewModel;
import com.voice.changer.speechshift.custUi.LiveEvents;
import com.voice.changer.speechshift.getApiData.allModel.RecordingModel;
import com.voice.changer.speechshift.recordingServices.ServiceRecordingVoice;

import kotlin.jvm.internal.Intrinsics;

public final class RecordingViewModel extends BaseViewModel {
    public final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    public final LiveEvents<RecordingModel> recordingModelLiveEvents = new LiveEvents<>();
    public final ServiceRecordingVoice.OnRecordingStatusChangedListener onScheduledRecordingListener = new ServiceRecordingVoice.OnRecordingStatusChangedListener() {
        @Override
        public void onAmplitudeInfo(int i) {
            mutableLiveData.postValue(i);
        }

        @Override
        public void onPauseRecording() {
            getServiceRecordResume().set(false);

            getServiceRecordResume().set(false);
        }

        @Override
        public void onResumeRecording() {
            getServiceRecordResume().set(true);
        }

        @Override
        public void onSkipRecording() {
            getServiceRecordResume().set(false);
            getServiceRecording().set(false);
            getObservableInt().set(0);
        }

        @Override
        public void onStartedRecording() {
            getServiceRecording().set(true);
            getServiceRecordResume().set(true);
        }

        @Override
        public void onStopRecording(RecordingModel recordingModel) {
            getServiceRecordResume().set(false);
            getServiceRecording().set(false);
            getObservableInt().set(0);
            recordingModelLiveEvents.postValue(recordingModel);
        }

        @Override
        public void onTimerChanged(int i) {
            getObservableInt().set(i);
        }
    };

    @SuppressLint("StaticFieldLeak")
    public ServiceRecordingVoice recordingVoice;
    private final ObservableInt observableInt = new ObservableInt(0);
    private final ObservableBoolean observableBoolean = new ObservableBoolean(false);
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            recordingVoice = ((ServiceRecordingVoice.LocalBinder) iBinder).getService();
            getObservableBoolean().set(true);
            ServiceRecordingVoice recordingVoice1 = RecordingViewModel.this.recordingVoice;
            recordingVoice1.setOnRecordingStatusChangedListener(onScheduledRecordingListener);
            ObservableBoolean recording = getServiceRecording();
            ServiceRecordingVoice serviceRecordingVoice1 = RecordingViewModel.this.recordingVoice;
            recording.set(serviceRecordingVoice1.isRecording());
            ObservableBoolean recordResume = getServiceRecordResume();
            ServiceRecordingVoice serviceRecordingVoice2 = RecordingViewModel.this.recordingVoice;
            recordResume.set(serviceRecordingVoice2.isResumeRecording());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (recordingVoice != null) {
                ServiceRecordingVoice access$getService$pRecordingVoice = recordingVoice;
                Intrinsics.checkNotNull(access$getService$pRecordingVoice);
                access$getService$pRecordingVoice.setOnRecordingStatusChangedListener((ServiceRecordingVoice.OnRecordingStatusChangedListener) null);
                recordingVoice = null;
            }
            getObservableBoolean().set(false);
        }
    };
    private final ObservableBoolean serviceRecordResume = new ObservableBoolean(false);
    private final ObservableBoolean serviceRecording = new ObservableBoolean(false);
    private final LiveEvents<Integer> toastMsg = new LiveEvents<>();

    public final ObservableBoolean getObservableBoolean() {
        return this.observableBoolean;
    }

    public final ObservableBoolean getServiceRecording() {
        return this.serviceRecording;
    }

    public final ObservableBoolean getServiceRecordResume() {
        return this.serviceRecordResume;
    }

    public final ObservableInt getObservableInt() {
        return this.observableInt;
    }

    public final void connectService(Intent intent) {
        try {
            getContext().startService(intent);
            getContext().bindService(intent, this.connection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            System.out.println(Intrinsics.stringPlus("RecordViewModel.connectService e = ", e));
        }
    }

    public final void serviceStartStop(Intent intent) {
        try {
            if (this.observableBoolean.get()) {
                getContext().unbindService(this.connection);
                if (!this.serviceRecording.get()) {
                    getContext().stopService(intent);
                }
                ServiceRecordingVoice serviceRecordingVoice2 = this.recordingVoice;
                if (serviceRecordingVoice2 != null) {
                    serviceRecordingVoice2.setOnRecordingStatusChangedListener((ServiceRecordingVoice.OnRecordingStatusChangedListener) null);
                }
                this.recordingVoice = null;
                this.observableBoolean.set(false);
            }
        } catch (Exception e) {
            System.out.println(Intrinsics.stringPlus("RecordViewModel.disconnectAndStopService e = ", e));
        }
    }

    public void recStart() {
        ServiceRecordingVoice serviceRecordingVoice2 = this.recordingVoice;
        if (serviceRecordingVoice2 != null) {
            serviceRecordingVoice2.startRecording(0);
        }
        this.serviceRecording.set(true);
        this.serviceRecordResume.set(true);
    }

    public void recStop() {
        ServiceRecordingVoice serviceRecordingVoice2 = this.recordingVoice;
        if (serviceRecordingVoice2 != null) {
            serviceRecordingVoice2.recordingStop();
        }
    }

    public void recPause() {
        ServiceRecordingVoice serviceRecordingVoice2 = this.recordingVoice;
        if (serviceRecordingVoice2 != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                serviceRecordingVoice2.pauseRecording();
            }
        }
    }

    public void recResume() {
        ServiceRecordingVoice serviceRecordingVoice2 = this.recordingVoice;
        if (serviceRecordingVoice2 != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                serviceRecordingVoice2.resumeRecording();
            }
        }
    }

    public void recSkipFile() {
        ServiceRecordingVoice serviceRecordingVoice2 = this.recordingVoice;
        if (serviceRecordingVoice2 != null) {
            serviceRecordingVoice2.fileRecordSkip();
        }
    }

    public LiveEvents<RecordingModel> getRecording() {
        return this.recordingModelLiveEvents;
    }

}
