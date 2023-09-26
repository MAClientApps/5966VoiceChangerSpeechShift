package com.reactlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.reactlibrary.basseffect.DBMediaListener;
import com.reactlibrary.basseffect.MediaPlayerDb;
import com.reactlibrary.constants.InterfaceVoiceChangerListener;
import com.reactlibrary.dataMng.ParsingJsonObjects;
import com.reactlibrary.object.ModelEffects;
import com.reactlibrary.task.DatabaseTask;
import com.reactlibrary.task.InterFaceCallBack;
import com.reactlibrary.task.TaskListener;
import com.un4seen.bass.BASS;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseCallback;

import java.io.File;
import java.util.ArrayList;

public class ChangeEffectsModule {
    public static final String logTag = "VoiceChangerModule";
    private final Context context;

    public ArrayList<ModelEffects> modelEffects = new ArrayList<>();
    private boolean isInitIs;
    private MediaPlayerDb mediaPlayerDb;
    private String strChangeVoiceName;
    private String strAudioPath = null;

    public File fileOutputDirectory = null;

    public Integer indexPLaying = null;

    public ChangeEffectsModule(Context ctx) {
        this.context = ctx;
        setDBMedia(this.mediaPlayerDb);
        onInitAudioDevice();
    }

    public Integer getIndexPLaying() {
        return this.indexPLaying;
    }

    public void insertEffect(String str) {
        this.modelEffects.add(ParsingJsonObjects.jsonToObjectEffects(str));
    }

    public MediaPlayerDb getDBMedia() {
        return this.mediaPlayerDb;
    }

    public void setDBMedia(MediaPlayerDb dBMediaPlayerDb) {
        this.mediaPlayerDb = dBMediaPlayerDb;
    }

    public void show(String str, int i) {
        Toast.makeText(this.context, str, i).show();
    }

    public void setPath(String str) {
        this.strAudioPath = str;
    }

    public void setIndexPLaying(Integer num) {
        if (num != null) {
            this.indexPLaying = num;
        }
    }

    public void saveTheEffects(int i, String str, BaseCallback baseCallback) {
        onSaveEffect(this.modelEffects.get(i), str, baseCallback);
    }

    public void createOutputDir(Activity activity) {
        this.fileOutputDirectory = getDirectory(activity);
    }

    public void createDBMedia() {
        onCreateDBMedia();
    }

    public void effectPlay(int i) {
        String str = logTag;
        Log.d(str, "audioPath: " + this.strAudioPath);
        if (strAudioPath != null || !strAudioPath.equals("")) {
            File file = new File(this.strAudioPath);
            if (!file.exists() || !file.isFile()) {
                toastShow("File not found exception");
            }
        }
        try {
            setIndexPLaying(Integer.valueOf(i));
            Log.e(str, "playEffect: " + this.modelEffects.get(i).getStrName());
            onEffectPlay(this.modelEffects.get(i));
        } catch (Exception unused) {
        }
    }

    private void onEffectPlay(ModelEffects modelEffects) {
        if (modelEffects.isPlayingBool()) {
            modelEffects.setPlayingBool(false);
            MediaPlayerDb dBMediaPlayerDb = this.mediaPlayerDb;
            if (dBMediaPlayerDb != null) {
                dBMediaPlayerDb.audioPause();
                return;
            }
            return;
        }
        onStateReset();
        modelEffects.setPlayingBool(true);
        MediaPlayerDb dBMediaPlayerDb2 = this.mediaPlayerDb;
        if (dBMediaPlayerDb2 != null) {
            dBMediaPlayerDb2.setPathMix(modelEffects.getStrPathMix());
            this.mediaPlayerDb.setMixNeed(modelEffects.isMixBool());
            this.mediaPlayerDb.audioPrepare();
            this.mediaPlayerDb.setThisReverse(modelEffects.isReverseBool());
            this.mediaPlayerDb.setPitchAudio(modelEffects.getIntPitch());
            this.mediaPlayerDb.compressorSet(modelEffects.getFloatCompressor());
            this.mediaPlayerDb.setRateAudio(modelEffects.getFloatRate());
            this.mediaPlayerDb.setEQ1Audio(modelEffects.getFloatyEcho1());
            this.mediaPlayerDb.setEQ2Audio(modelEffects.getFloatEq2());
            this.mediaPlayerDb.setEQ3Audio(modelEffects.getFloatEq3());
            this.mediaPlayerDb.phrserSet(modelEffects.getFloatPhaser());
            this.mediaPlayerDb.setWahAuto(modelEffects.getFloatAutoWah());
            this.mediaPlayerDb.setReverbAudio(modelEffects.getFloatReverb());
            this.mediaPlayerDb.setEffectEcho4(modelEffects.getFloatEcho4());
            this.mediaPlayerDb.setEchoAudio(modelEffects.getFloatEcho());
            this.mediaPlayerDb.setFilterQuad(modelEffects.getFloatFilter());
            this.mediaPlayerDb.setEffectFlang(modelEffects.getFloatFlange());
            this.mediaPlayerDb.chorusSet(modelEffects.getFloatChorus());
            this.mediaPlayerDb.setAmpli(modelEffects.getFloatAmplify());
            this.mediaPlayerDb.disortSet(modelEffects.getFloatDistort());
            this.mediaPlayerDb.rotateSet(modelEffects.getFloatRotate());
            this.mediaPlayerDb.audioStart();
        }
    }

    private void onInitAudioDevice() {
        if (this.isInitIs) {
            return;
        }
        this.isInitIs = true;
        if (!BASS.BASS_Init(-1, 44100, 0)) {
            new Exception(logTag + " Can't initialize device").printStackTrace();
            this.isInitIs = false;
            return;
        }
        String str = this.context.getApplicationInfo().nativeLibraryDir;
        try {
            BASS.BASS_PluginLoad(str + "/libbass_fx.so", 0);
            BASS.BASS_PluginLoad(str + "/libbassenc.so", 0);
            BASS.BASS_PluginLoad(str + "/libbassmix.so", 0);
            BASS.BASS_PluginLoad(str + "/libbasswv.so", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSaveEffect(ModelEffects modelEffects, final String str, final BaseCallback baseCallback) {
        if (this.mediaPlayerDb != null) {
            this.strChangeVoiceName = str + ".wav";
            startSaveEffect(modelEffects, new InterFaceCallBack() {
                public void onCallBackAction() {
                    File file = new File(ChangeEffectsModule.this.fileOutputDirectory, str);
                    if (file.exists()) {
                        ChangeEffectsModule.this.toastShow(String.format("Your voice path is %1$s", new Object[]{file.getAbsolutePath()}));
                    }
                    baseCallback.onSuccess();
                }
            });
        }
    }

    private void startSaveEffect(ModelEffects modelEffects, InterFaceCallBack iDBCallback) {
        final File file = new File(this.fileOutputDirectory, this.strChangeVoiceName);
        final MediaPlayerDb dBMediaPlayerDb = new MediaPlayerDb(this.strAudioPath);
        final ModelEffects modelEffects2 = modelEffects;
        final InterFaceCallBack iDBCallback2 = iDBCallback;
        new DatabaseTask(new TaskListener() {
            public void onPreExecuteTask() {
            }

            public void onDoInBackgroundTask() {
                if (dBMediaPlayerDb.initSolveToMedia()) {
                    dBMediaPlayerDb.setThisReverse(modelEffects2.isReverseBool());
                    dBMediaPlayerDb.setPitchAudio(modelEffects2.getIntPitch());
                    dBMediaPlayerDb.compressorSet(modelEffects2.getFloatCompressor());
                    dBMediaPlayerDb.setRateAudio(modelEffects2.getFloatRate());
                    dBMediaPlayerDb.setEQ1Audio(modelEffects2.getFloatyEcho1());
                    dBMediaPlayerDb.setEQ2Audio(modelEffects2.getFloatEq2());
                    dBMediaPlayerDb.setEQ3Audio(modelEffects2.getFloatEq3());
                    dBMediaPlayerDb.phrserSet(modelEffects2.getFloatPhaser());
                    dBMediaPlayerDb.setWahAuto(modelEffects2.getFloatAutoWah());
                    dBMediaPlayerDb.setReverbAudio(modelEffects2.getFloatReverb());
                    dBMediaPlayerDb.setEffectEcho4(modelEffects2.getFloatEcho4());
                    dBMediaPlayerDb.setEchoAudio(modelEffects2.getFloatEcho());
                    dBMediaPlayerDb.setFilterQuad(modelEffects2.getFloatFilter());
                    dBMediaPlayerDb.setEffectFlang(modelEffects2.getFloatFlange());
                    dBMediaPlayerDb.chorusSet(modelEffects2.getFloatChorus());
                    dBMediaPlayerDb.setAmpli(modelEffects2.getFloatAmplify());
                    dBMediaPlayerDb.disortSet(modelEffects2.getFloatDistort());
                    dBMediaPlayerDb.rotateSet(modelEffects2.getFloatRotate());
                    dBMediaPlayerDb.saveAsFile(file.getAbsolutePath());
                    dBMediaPlayerDb.audioRelease();
                }
            }

            public void onPostExecuteTask() {
                if (iDBCallback2 != null) {
                    iDBCallback2.onCallBackAction();
                }
            }
        }).execute();
    }

    private void onCreateDBMedia() {
        if (strAudioPath != null || !strAudioPath.equals("")) {
            MediaPlayerDb dBMediaPlayerDb = new MediaPlayerDb(this.strAudioPath);
            this.mediaPlayerDb = dBMediaPlayerDb;
            dBMediaPlayerDb.audioPrepare();
            this.mediaPlayerDb.setOnDBMediaListener(new DBMediaListener() {
                public void onMediaError() {
                }

                public void onMediaCompleteListener() {
                    ((ModelEffects) ChangeEffectsModule.this.modelEffects.get(ChangeEffectsModule.this.indexPLaying.intValue())).setPlayingBool(false);
                    ChangeEffectsModule.this.setIndexPLaying((Integer) null);
                }
            });
            return;
        }
        toastShow("Media file not found!");
    }

    public void toastShow(String msg) {
        Toast toast = Toast.makeText(this.context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(80, 0, 0);
        toast.show();
    }

    public void onStateReset() {
        ArrayList<ModelEffects> modelEffects1 = this.modelEffects;
        if (modelEffects1 != null && modelEffects1.size() > 0) {
            for (int i = 0; i < this.modelEffects.size(); i++) {
                if (this.modelEffects.get(i).isPlayingBool()) {
                    this.modelEffects.get(i).setPlayingBool(false);
                }
            }
        }
    }

    private File getDirectory(Activity activity) {
        File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + activity.getResources().getString(R.string.app_name) + "/" + InterfaceVoiceChangerListener.recordedFolderName);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        return file1;
    }
}
