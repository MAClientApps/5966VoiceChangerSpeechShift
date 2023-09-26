package com.voice.changer.speechshift.recordingServices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.android.exoplayer2.audio.DtsUtil;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.custUi.FileMethods;
import com.voice.changer.speechshift.getApiData.allModel.RecordingModel;

import java.io.File;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceRecordingVoice extends Service {
    private static final String extraAcvtivityStarter = "com.vijay.voice.changer.EXTRA_ACTIVITY_STARTER";
    public static int onCallsCreate;
    public static int onCallsDestroy;
    public static int onCallsStartCommands;
    private boolean isRecording = false;
    private boolean isResumeRecording = false;

    public long mElapsedMillis = 0;
    private String strFileName = null;
    private String strFilePath = null;

    public TimerTask timerTask = null;

    public MediaRecorder mediaRecorder = null;
    private final IBinder myBinder = new LocalBinder();

    public OnRecordingStatusChangedListener onRecordingStatusChangedListener = null;

    public interface OnRecordingStatusChangedListener {
        void onAmplitudeInfo(int i);

        void onPauseRecording();

        void onResumeRecording();

        void onSkipRecording();

        void onStartedRecording();

        void onStopRecording(RecordingModel recordingModel);

        void onTimerChanged(int i);
    }

    static long cusMethod(ServiceRecordingVoice serviceRecordingVoice, long j) {
        long j2 = serviceRecordingVoice.mElapsedMillis + j;
        serviceRecordingVoice.mElapsedMillis = j2;
        return j2;
    }

    public static Intent makeIntent(Context context, boolean z) {
        Intent intent = new Intent(context.getApplicationContext(), ServiceRecordingVoice.class);
        intent.putExtra(extraAcvtivityStarter, z);
        return intent;
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public ServiceRecordingVoice getService() {
            return ServiceRecordingVoice.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.myBinder;
    }

    public void setOnRecordingStatusChangedListener(OnRecordingStatusChangedListener onRecordingStatusChangedListener2) {
        this.onRecordingStatusChangedListener = onRecordingStatusChangedListener2;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onCallsStartCommands++;
        intent.getBooleanExtra(extraAcvtivityStarter, false);
        return Service.START_NOT_STICKY;
    }

    public void onCreate() {
        onCallsCreate++;
        super.onCreate();
    }

    public void onDestroy() {
        onCallsDestroy++;
        super.onDestroy();
        if (this.mediaRecorder != null) {
            recordingStop();
        }
        if (this.onRecordingStatusChangedListener != null) {
            this.onRecordingStatusChangedListener = null;
        }
    }

    public void startRecording(int i) {
        try {
            startForeground(2, notificationCreate());
            setFileNamePath();
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.mediaRecorder = mediaRecorder;
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(this.strFilePath);
            mediaRecorder.setMaxDuration(i);
            mediaRecorder.setAudioChannels(1);
            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setAudioEncodingBitRate(DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND);
            mediaRecorder.setOnInfoListener((mediaRecorder1, i1, i2) -> {
                if (i1 == 800) {
                    recordingStop();
                }
            });
            this.mediaRecorder.prepare();
            this.mediaRecorder.start();
            this.isRecording = true;
            this.isResumeRecording = true;
            timerStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OnRecordingStatusChangedListener onRecordingStatusChangedListener2 = this.onRecordingStatusChangedListener;
        if (onRecordingStatusChangedListener2 != null) {
            onRecordingStatusChangedListener2.onStartedRecording();
        }
    }

    private void setFileNamePath() {
        this.strFileName = "Voice_effect_" + System.currentTimeMillis();
        this.strFilePath = FileMethods.getMainDirPath(this) + "/" + this.strFileName + ".mp3";
    }

    private void timerStart() {
        Timer timers = new Timer();
        this.mElapsedMillis = 0;
        TimerTask r1 = new TimerTask() {
            public void run() {
                if (ServiceRecordingVoice.this.timerTask == null) {
                    cancel();
                }
                ServiceRecordingVoice.cusMethod(ServiceRecordingVoice.this, 100);
                if (ServiceRecordingVoice.this.onRecordingStatusChangedListener != null) {
                    ServiceRecordingVoice.this.onRecordingStatusChangedListener.onTimerChanged(((int) ServiceRecordingVoice.this.mElapsedMillis) / 1000);
                }
                if (ServiceRecordingVoice.this.onRecordingStatusChangedListener != null && ServiceRecordingVoice.this.mediaRecorder != null) {
                    try {
                        ServiceRecordingVoice.this.onRecordingStatusChangedListener.onAmplitudeInfo(ServiceRecordingVoice.this.mediaRecorder.getMaxAmplitude());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.timerTask = r1;
        timers.scheduleAtFixedRate(r1, 100, 100);
    }

    public void recordingStop() {
        try {
            this.mediaRecorder.stop();
            this.mediaRecorder.release();
            this.isRecording = false;
            this.isResumeRecording = false;
            this.mediaRecorder = null;
            TimerTask task = this.timerTask;
            if (task != null) {
                task.cancel();
                this.timerTask = null;
            }

            RecordingModel model = new RecordingModel(this.strFileName, this.strFilePath, this.mElapsedMillis, System.currentTimeMillis(), 0);

            OnRecordingStatusChangedListener changedListener = this.onRecordingStatusChangedListener;
            if (changedListener != null) {
                changedListener.onStopRecording(model);
            }
            if (this.onRecordingStatusChangedListener == null) {
                stopSelf();
            }
            stopForeground(true);
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("RecordingService.stopRecording e = " + e);
        }
    }

    public void fileRecordSkip() {
        try {
            this.mediaRecorder.stop();
            this.mediaRecorder.release();
            this.isRecording = false;
            this.isResumeRecording = false;
            this.mediaRecorder = null;
            this.mElapsedMillis = 0;
            OnRecordingStatusChangedListener onRecordingStatusChangedListener2 = this.onRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener2 != null) {
                onRecordingStatusChangedListener2.onSkipRecording();
            }
            TimerTask task = this.timerTask;
            if (task != null) {
                task.cancel();
                this.timerTask = null;
            }
            if (this.strFilePath != null) {
                File files = new File(this.strFilePath);
                if (files.exists()) {
                    files.delete();
                }
            }
            if (this.onRecordingStatusChangedListener == null) {
                stopSelf();
            }
            stopForeground(true);
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("RecordingService.skipFileRecord e = " + e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pauseRecording() {
        try {
            this.mediaRecorder.pause();
            this.isResumeRecording = false;
            OnRecordingStatusChangedListener onRecordingStatusChangedListener2 = this.onRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener2 != null) {
                onRecordingStatusChangedListener2.onPauseRecording();
            }
            TimerTask task = this.timerTask;
            if (task != null) {
                task.cancel();
                this.timerTask = null;
            }
        } catch (Exception exception) {
            PrintStream printStream = System.out;
            printStream.println("RecordingService.pauseRecording e = " + exception);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void resumeRecording() {
        try {
            this.mediaRecorder.resume();
            this.isResumeRecording = true;
            OnRecordingStatusChangedListener onRecordingStatusChangedListener2 = this.onRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener2 != null) {
                onRecordingStatusChangedListener2.onResumeRecording();
            }
            Timer timers = new Timer();
            TimerTask r2 = new TimerTask() {
                public void run() {
                    if (ServiceRecordingVoice.this.timerTask == null) {
                        cancel();
                    }
                    ServiceRecordingVoice.cusMethod(ServiceRecordingVoice.this, 100);
                    if (ServiceRecordingVoice.this.onRecordingStatusChangedListener != null) {
                        ServiceRecordingVoice.this.onRecordingStatusChangedListener.onTimerChanged(((int) ServiceRecordingVoice.this.mElapsedMillis) / 1000);
                    }
                    if (ServiceRecordingVoice.this.onRecordingStatusChangedListener != null && ServiceRecordingVoice.this.mediaRecorder != null) {
                        try {
                            ServiceRecordingVoice.this.onRecordingStatusChangedListener.onAmplitudeInfo(ServiceRecordingVoice.this.mediaRecorder.getMaxAmplitude());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            this.timerTask = r2;
            timers.scheduleAtFixedRate(r2, 100, 100);
        } catch (Exception e) {
            PrintStream out = System.out;
            out.println("RecordingService.resumeRecording e = " + e);
        }
    }

    private Notification notificationCreate() {
        return new NotificationCompat.Builder(getApplicationContext(), Build.VERSION.SDK_INT >= 26 ? notiChannelCreate() : "").setSmallIcon((int) R.drawable.ic_mic_white).setContentTitle(getString(R.string.notification_recording)).setOngoing(true).build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String notiChannelCreate() {
        NotificationChannel channel = new NotificationChannel("recording_service", "Recording Service", NotificationManager.IMPORTANCE_LOW);
        channel.setLightColor(-16776961);
        channel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        return "recording_service";
    }

    public boolean isRecording() {
        return this.isRecording;
    }

    public boolean isResumeRecording() {
        return this.isResumeRecording;
    }

}
