package com.voice.changer.speechshift.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.reactlibrary.ChangeEffectsModule;
import com.reactlibrary.basseffect.MediaPlayerDb;
import com.voice.changer.speechshift.FilenameUtils;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.adapters.EffectAdapter;
import com.voice.changer.speechshift.adapters.EffectVoiceAdapter;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.allDialogs.DownloadDialog;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.custUi.FileMethods;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.ActivityEffectChangeBinding;
import com.voice.changer.speechshift.getApiData.allModel.EffectModel;
import com.voice.changer.speechshift.myAdsClasses.ApplovinBannerAds;
import com.voice.changer.speechshift.myAdsClasses.ApplovinInterAds;
import com.voice.changer.speechshift.viewModel.ChangeEffectViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

public final class ChangeEffectActivity extends BaseActivity<ChangeEffectViewModel, ActivityEffectChangeBinding> {


    public FrameLayout ads;
    public RelativeLayout llyBanner;

    public static final Companion Companion = new Companion(null);
    public static EffectModel effectModel;
    public static String path = "";
    int intCurrPos;
    private DownloadDialog downloadDialog;
    private Handler mHandler;
    private boolean initZView;
    public boolean isMutes;
    public boolean isPlayAudio;
    public String keyScreen = "";
    List<String> listEffectName = new ArrayList<>();
    private AudioManager audioManager;
    Runnable mRunnable;
    SharedPreferences preferences;
    public EffectAdapter effectAdapter;
    public ChangeEffectsModule changeEffectsModule;
    private ChangeEffectActivity effectActivity;

    public Class<ChangeEffectViewModel> createViewModel() {
        return ChangeEffectViewModel.class;
    }

    public int getContent() {
        effectActivity = this;
        return R.layout.activity_effect_change;
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

    public DownloadDialog getDownloadDialog() {
        return this.downloadDialog;
    }

    public void setDownloadDialog(DownloadDialog downloadDialog) {
        this.downloadDialog = downloadDialog;
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EffectModel getEffectModelSelected() {
            return ChangeEffectActivity.effectModel;
        }

        public final void setEffectModelSelected(EffectModel effectModel) {
            ChangeEffectActivity.effectModel = effectModel;
        }

        public final String getPath() {
            return ChangeEffectActivity.path;
        }

        public final void setPath(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            ChangeEffectActivity.path = str;
        }
    }

    public void mainView() {

        ads = findViewById(R.id.ads);
        llyBanner = findViewById(R.id.ll_banner);

        ApplovinInterAds.getInstance().loadInterstitialAd(ChangeEffectActivity.this);
        ApplovinBannerAds.getInstance().showBannerAds(llyBanner, ChangeEffectActivity.this);


        this.preferences = getSharedPreferences("MY_PRE", 0);
        boolean z = true;
        this.initZView = true;
        Objects.requireNonNull(getBindingData()).toolbar.ivDone.setVisibility(View.VISIBLE);
        getBindingData().toolbar.tvTitle.setText(getString(R.string.voice_effects));
        mHandler = new Handler();
        if (effectModel == null) {
            String string = getString(R.string.normal);
            effectModel = new EffectModel(0, string, "normal", 0, 0, 0, true);
        }
        RecyclerView rvAudioEffect = getBindingData().rvAudioEffect;
        rvAudioEffect.setHasFixedSize(true);
        Context context = this;
        EffectAdapter effectAdapter = new EffectAdapter(context, new ArrayList<>(), (typeEffectModel, i) -> {
            Intrinsics.checkNotNullParameter(typeEffectModel, "data");
            getBindingData().viewPager.setCurrentItem(i);
            return null;
        });


        this.effectAdapter = effectAdapter;
        rvAudioEffect.setAdapter(effectAdapter);

        this.keyScreen = String.valueOf(getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS()));
        if (String.valueOf(getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE())).length() <= 0) {
            z = false;
        }
        if (z) {
            path = String.valueOf(getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE()));
            ChangeEffectsModule changerModule = new ChangeEffectsModule(context);
            this.changeEffectsModule = changerModule;
            changerModule.createOutputDir(ChangeEffectActivity.this);
            ChangeEffectsModule changerModule1 = this.changeEffectsModule;
            if (changerModule1 != null) {
                changerModule1.setPath(path);
            }
            ChangeEffectsModule module = this.changeEffectsModule;
            if (module != null) {
                module.createDBMedia();
            }
        }
        try {
            JSONArray array = new JSONArray(AppConstant.APP_CONSTANT.getVoiceEffect(this));
            int length = array.length();
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                JSONObject jsonobject = array.getJSONObject(i);
                ChangeEffectsModule changeEffectsModule1 = this.changeEffectsModule;
                if (changeEffectsModule1 != null) {
                    changeEffectsModule1.insertEffect(jsonobject.toString());
                }
                List<String> listEffectName1 = this.listEffectName;
                String string2 = jsonobject.getString("name");
                Intrinsics.checkNotNullExpressionValue(string2, "jsonObj.getString(\"name\")");
                listEffectName1.add(string2);
                i = i2;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        playEffect(0);
        Object systemService = getSystemService(Context.AUDIO_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.audioManager = (AudioManager) systemService;
    }

    public void initViews() {
        MediaPlayerDb mediaPlayer;

        TapClick.tap(Objects.requireNonNull(getBindingData()).toolbar.ivDone, (Function1<View, Unit>) view -> {
            String keyScreen1 = keyScreen;
            int code = keyScreen1.hashCode();
            if (code != 1197439160) {
                if (code != 1345236640) {
                    if (code == 1511250982 && keyScreen1.equals("AudioFragment")) {
                        final Bundle bundles = new Bundle();
                        effectActivity.setDownloadDialog(new DownloadDialog(ChangeEffectActivity.this, false, str -> {
                            String nameOrigin;
                            pauseAudio();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append('_');
                            EffectModel modelSelected = ChangeEffectActivity.Companion.getEffectModelSelected();
                            Integer integer = null;
                            stringBuilder.append((Object) ((modelSelected == null || (nameOrigin = modelSelected.getNameOrigin()) == null) ? null : StringsKt.replace(nameOrigin, " ", "", false)));
                            String sb2 = stringBuilder.toString();
                            if (FilenameUtils.getBaseName(ChangeEffectActivity.Companion.getPath()).equals(sb2)) {
                                Toast.makeText(effectActivity, effectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                                return null;
                            }
                            String key_position_effect = AppConstant.APP_CONSTANT.getKEY_POSITION_EFFECT();
                            ChangeEffectsModule vCM1 = ChangeEffectActivity.this.changeEffectsModule;
                            if (vCM1 != null) {
                                integer = vCM1.getIndexPLaying();
                            }
                            Intrinsics.checkNotNull(integer);
                            bundles.putInt(key_position_effect, integer.intValue());
                            bundles.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE()));
                            bundles.putString(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                            bundles.putString(AppConstant.APP_CONSTANT.getKEY_DURATION_VOICE(), ((TextView) getBindingData().playerView.exoDuration).getText().toString());
                            bundles.putString(AppConstant.APP_CONSTANT.getKEY_SIZE_VOICE(), byteToMB(new File(ChangeEffectActivity.Companion.getPath()).length()));
                            final Bundle bundle2 = bundles;
                            nextActivity(SaveActivity.class, bundle2);
                            return null;
                        }));
                        DownloadDialog downloadDialog = getDownloadDialog();
                        if (downloadDialog == null) {
                            return null;
                        }
                        downloadDialog.show();
                        return null;
                    }
                } else if (keyScreen1.equals("RecordActivity")) {
                    final Bundle bundle2 = new Bundle();
                    setDownloadDialog(new DownloadDialog(ChangeEffectActivity.this, false, str -> {
                        String nameOrigin;
                        pauseAudio();
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append('_');
                        EffectModel effectModelSelected = Companion.getEffectModelSelected();
                        Integer num = null;
                        sb.append((Object) ((effectModelSelected == null || (nameOrigin = effectModelSelected.getNameOrigin()) == null) ? null : StringsKt.replace(nameOrigin, " ", "", false)));
                        String sb2 = sb.toString();
                        if (FilenameUtils.getBaseName(ChangeEffectActivity.Companion.getPath()).equals(sb2)) {
                            Toast.makeText(effectActivity, effectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                            return null;
                        }
                        Bundle bundle = bundle2;
                        String key_position_effect = AppConstant.APP_CONSTANT.getKEY_POSITION_EFFECT();
                        ChangeEffectsModule vCM1 = ChangeEffectActivity.this.changeEffectsModule;
                        if (vCM1 != null) {
                            num = vCM1.getIndexPLaying();
                        }
                        Intrinsics.checkNotNull(num);
                        bundle.putInt(key_position_effect, num.intValue());
                        bundle2.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE()));
                        bundle2.putString(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                        bundle2.putString(AppConstant.APP_CONSTANT.getKEY_DURATION_VOICE(), ((TextView) getBindingData().playerView.exoDuration).getText().toString());
                        bundle2.putString(AppConstant.APP_CONSTANT.getKEY_SIZE_VOICE(), byteToMB(new File(ChangeEffectActivity.Companion.getPath()).length()));
                        nextActivity(SaveActivity.class, bundle);
                        return null;
                    }));
                    DownloadDialog dialogs = getDownloadDialog();
                    if (dialogs == null) {
                        return null;
                    }
                    dialogs.show();
                }
            } else if (keyScreen1.equals("TextAudioActivity")) {
                final Bundle bundle22 = new Bundle();
                ChangeEffectActivity changeEffectActivity32 = ChangeEffectActivity.this;
                final ChangeEffectActivity changeEffectActivity222 = ChangeEffectActivity.this;
                changeEffectActivity32.setDownloadDialog(new DownloadDialog(ChangeEffectActivity.this, false, str -> {
                    String nameOrigin;
                    changeEffectActivity222.pauseAudio();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append('_');
                    EffectModel effectModelSelected = ChangeEffectActivity.Companion.getEffectModelSelected();
                    Integer num = null;
                    sb.append((Object) ((effectModelSelected == null || (nameOrigin = effectModelSelected.getNameOrigin()) == null) ? null : StringsKt.replace(nameOrigin, " ", "", false)));
                    String sb2 = sb.toString();
                    if (FilenameUtils.getBaseName(ChangeEffectActivity.Companion.getPath()).equals(sb2)) {
                        ChangeEffectActivity changeEffectActivity = changeEffectActivity222;
                        Toast.makeText(changeEffectActivity, changeEffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    Bundle bundle = bundle22;
                    String key_position_effect = AppConstant.APP_CONSTANT.getKEY_POSITION_EFFECT();
                    ChangeEffectsModule changerModule = changeEffectActivity222.changeEffectsModule;
                    if (changerModule != null) {
                        num = changerModule.getIndexPLaying();
                    }
                    Intrinsics.checkNotNull(num);
                    bundle.putInt(key_position_effect, num.intValue());
                    bundle22.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), changeEffectActivity222.getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE()));
                    bundle22.putString(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                    bundle22.putString(AppConstant.APP_CONSTANT.getKEY_DURATION_VOICE(), ((TextView) changeEffectActivity222.getBindingData().playerView.exoDuration).getText().toString());
                    bundle22.putString(AppConstant.APP_CONSTANT.getKEY_SIZE_VOICE(), byteToMB(new File(ChangeEffectActivity.Companion.getPath()).length()));
                    final ChangeEffectActivity changeEffectActivity2 = changeEffectActivity222;
                    final Bundle bundle2 = bundle22;
                    changeEffectActivity2.nextActivity(SaveActivity.class, bundle2);
                    return null;
                }));
                DownloadDialog downloadDialog = getDownloadDialog();
                if (downloadDialog == null) {
                    return null;
                }
                downloadDialog.show();
            }

            final Bundle bundle222 = new Bundle();
            setDownloadDialog(new DownloadDialog(ChangeEffectActivity.this, false, str -> {
                Intrinsics.checkNotNullParameter(str, "it");
                pauseAudio();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append('_');
                EffectModel effectModelSelected = ChangeEffectActivity.Companion.getEffectModelSelected();
                Integer num = null;
                String replace$default = (effectModelSelected == null || str == null) ? null : StringsKt.replace(str, " ", "", false);
                sb.append(replace$default);
                String sb2 = sb.toString();
                if (FilenameUtils.getBaseName(ChangeEffectActivity.Companion.getPath()).equals(sb2)) {
                    Toast.makeText(effectActivity, effectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                    return null;
                }
                Bundle bundle = bundle222;
                String key_position_effect = AppConstant.APP_CONSTANT.getKEY_POSITION_EFFECT();
                ChangeEffectsModule vCM1 = ChangeEffectActivity.this.changeEffectsModule;
                if (vCM1 != null) {
                    num = vCM1.getIndexPLaying();
                }
                Intrinsics.checkNotNull(num);
                bundle.putInt(key_position_effect, num.intValue());
                bundle222.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE()));
                bundle222.putString(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                bundle222.putString(AppConstant.APP_CONSTANT.getKEY_DURATION_VOICE(), ((TextView) getBindingData().playerView.exoDuration).getText().toString());
                bundle222.putString(AppConstant.APP_CONSTANT.getKEY_SIZE_VOICE(), byteToMB(new File(ChangeEffectActivity.Companion.getPath()).length()));
                final Bundle bundle2 = bundle222;
                nextActivity(SaveActivity.class, bundle2);
                return null;
            }));
            return null;
        });


        TapClick.tap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            onBackPressed();
            return null;
        });

        Objects.requireNonNull(getMViewModel()).getTypeEffects(this);
        getMViewModel().getLiveType().observe(this, typeEffectModels -> {
            EffectAdapter effectAdapter;
            if (typeEffectModels != null && (effectAdapter = effectActivity.effectAdapter) != null) {
                effectAdapter.addList(typeEffectModels);
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        EffectVoiceAdapter effectVoiceAdapter = new EffectVoiceAdapter(supportFragmentManager);
        ViewPager viewPager = getBindingData().viewPager;
        viewPager.setAdapter(effectVoiceAdapter);

        getBindingData().viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Objects.requireNonNull(getBindingData()).rvAudioEffect.smoothScrollToPosition(position);
                EffectAdapter typeAdapter1 = effectAdapter;
                if (typeAdapter1 != null) {
                    typeAdapter1.selItemPosition(position);
                }
                PagerAdapter pagerAdapter = getBindingData().viewPager.getAdapter();
                if (pagerAdapter != null) {
                    pagerAdapter.notifyDataSetChanged();
                }
                if (position == 0) {
                    getBindingData().viewPager.setCurrentItem(0);
                } else if (position == 1) {
                    getBindingData().viewPager.setCurrentItem(1);
                } else if (position == 2) {
                    getBindingData().viewPager.setCurrentItem(2);
                } else if (position == 3) {
                    getBindingData().viewPager.setCurrentItem(3);
                } else if (position == 4) {
                    getBindingData().viewPager.setCurrentItem(4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        TapClick.tap(getBindingData().playerView.exoVolume, (Function1<View, Unit>) view -> {
            isMutes = !isMutes;
            if (isMutes) {
                getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_mute);
                mute();
            } else {
                getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_volume);
                unMute();
            }
            return null;
        });

        TapClick.tap(getBindingData().playerView.exoPlay, (Function1<View, Unit>) view -> {
            isPlayAudio = !isPlayAudio;
            if (isPlayAudio) {
                pauseAudio();
                getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_play_effect);
            } else {
                playAudio();
                getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_pause_effect);
            }
            return null;
        });

        TextView exoDuration = getBindingData().playerView.exoDuration;

        ChangeEffectsModule changeEffectsModule = this.changeEffectsModule;
        Long l = null;
        if (!(changeEffectsModule == null || (mediaPlayer = changeEffectsModule.getDBMedia()) == null)) {
            l = (long) mediaPlayer.getIntDuration();
        }

        exoDuration.setText(FileMethods.milliSecFormat(l.longValue() * ((long) 1000)));

        getBindingData().playerView.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int i, boolean z) {
                MediaPlayerDb mediaPlayer;
                MediaPlayerDb mediaPlayer1;
                if (z) {
                    ChangeEffectsModule vCM1 = ChangeEffectActivity.this.changeEffectsModule;
                    if (!(vCM1 == null || (mediaPlayer1 = vCM1.getDBMedia()) == null)) {
                        mediaPlayer1.toSeek(i);
                    }
                    Objects.requireNonNull(getBindingData()).playerView.seekbar.setProgress(i);
                }
                ChangeEffectsModule vCM1 = ChangeEffectActivity.this.changeEffectsModule;
                Long l = null;
                if (!(vCM1 == null || (mediaPlayer = vCM1.getDBMedia()) == null)) {
                    l = (long) mediaPlayer.getIntDuration();
                }
                Intrinsics.checkNotNull(l);
                if (l.longValue() == ((long) i)) {
                    isPlayAudio = true;
                    ((ImageView) Objects.requireNonNull(getBindingData()).playerView.exoPlay).setImageResource(R.drawable.ic_play_effect);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public final void mute() {
        AudioManager manager = this.audioManager;
        if (manager != null) {
            manager.setStreamMute(3, true);
        }
    }


    public final void unMute() {
        AudioManager manager = this.audioManager;
        if (manager != null) {
            manager.setStreamMute(3, false);
        }
    }


    @SuppressLint("SetTextI18n")
    public final void playAudio() {
        MediaPlayerDb mediaPlayer;
        MediaPlayerDb player;
        ChangeEffectsModule changerModule = this.changeEffectsModule;
        if ((changerModule == null ? null : changerModule.getDBMedia()) != null) {
            ChangeEffectsModule changeEffectsModule2 = this.changeEffectsModule;
            if (!(changeEffectsModule2 == null || (player = changeEffectsModule2.getDBMedia()) == null)) {
                player.audioStart();
            }
            ChangeEffectsModule changeEffectsModule3 = this.changeEffectsModule;
            boolean z = false;
            if (!(changeEffectsModule3 == null || (mediaPlayer = changeEffectsModule3.getDBMedia()) == null || mediaPlayer.getIntCurrPosition() != 0)) {
                z = true;
            }
            if (z) {
                Objects.requireNonNull(getBindingData()).playerView.exoPosition1.setText("00:00");
            }
            updateSeekbar();
        }
    }


    public final void pauseAudio() {
        ChangeEffectsModule module;
        MediaPlayerDb mediaPlayer;
        ChangeEffectsModule module1 = this.changeEffectsModule;
        if ((module1 == null ? null : module1.getDBMedia()) != null && (module = this.changeEffectsModule) != null && (mediaPlayer = module.getDBMedia()) != null) {
            mediaPlayer.audioPause();
        }
    }

    private final void updateSeekbar() {
        try {
            ChangeEffectsModule module = this.changeEffectsModule;
            Integer integer = null;
            if (module != null) {
                MediaPlayerDb dBMedia = module.getDBMedia();
                if (dBMedia != null) {
                    integer = dBMedia.getIntCurrPosition();
                }
            }
            Intrinsics.checkNotNull(integer);
            this.intCurrPos = integer.intValue();
            if (this.intCurrPos != -1) {
                Objects.requireNonNull(getBindingData()).playerView.seekbar.setProgress(this.intCurrPos);
                getBindingData().playerView.exoPosition1.setText(FileMethods.milliSecFormat(this.intCurrPos * 1000L));
                this.mRunnable = () -> {
                    Intrinsics.checkNotNullParameter(effectActivity, "this$0");
                    effectActivity.updateSeekbar();
                };
                Handler handler2 = this.mHandler;
                if (handler2 != null) {
                    Intrinsics.checkNotNull(mRunnable);
                    handler2.postDelayed(mRunnable, 500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playEffect(int ii) {
        this.isPlayAudio = false;
        try {
            SeekBar bar = Objects.requireNonNull(getBindingData()).playerView.seekbar;
            ChangeEffectsModule module = this.changeEffectsModule;
            Integer integer = null;
            if (module != null) {
                MediaPlayerDb dBMedia = module.getDBMedia();
                if (dBMedia != null) {
                    integer = dBMedia.getIntDuration();
                }
            }
            Intrinsics.checkNotNull(integer);
            bar.setMax(integer.intValue());
            ChangeEffectsModule changerModule = this.changeEffectsModule;
            if (changerModule != null) {
                changerModule.effectPlay(ii);
            }
            updateSeekbar();
            getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_pause_effect);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, getString(R.string.error_file), Toast.LENGTH_SHORT).show();
        }
    }

    public void onStop() {
        super.onStop();
        pauseAudio();
        this.isPlayAudio = true;
        ((ImageView) Objects.requireNonNull(getBindingData()).playerView.exoPlay).setImageResource(R.drawable.ic_play_effect);
    }

    public void onResume() {
        super.onResume();
        if (this.initZView) {
            playAudio();
            this.initZView = false;
        }
        if (this.isMutes) {
            ((ImageView) Objects.requireNonNull(getBindingData()).playerView.exoVolume).setImageResource(R.drawable.ic_mute);
            mute();
            return;
        }
        ((ImageView) Objects.requireNonNull(getBindingData()).playerView.exoVolume).setImageResource(R.drawable.ic_volume);
        unMute();
    }

    public void onDestroy() {
        MediaPlayerDb mediaPlayerDb;
        super.onDestroy();
        ChangeEffectsModule changeEffectsModule = this.changeEffectsModule;
        if (!(changeEffectsModule == null || (mediaPlayerDb = changeEffectsModule.getDBMedia()) == null)) {
            mediaPlayerDb.audioPause();
        }
        path = "";
    }

    public void onBackPressed() {
        ApplovinInterAds.getInstance().showInterstitialAd();
        itemReset();
        finish();
        try {
            ChangeEffectsModule module = this.changeEffectsModule;
            if (module != null) {
                MediaPlayerDb dbMedia = module.getDBMedia();
                if (dbMedia != null) {
                    dbMedia.audioPause();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNull(event);
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        if (keyCode != 24) {
            if (keyCode != 25) {
                return super.dispatchKeyEvent(event);
            }
            if (action != 0) {
                return true;
            }
            AudioManager manager = this.audioManager;
            Integer valueOf = manager == null ? null : manager.getStreamVolume(3);
            AudioManager manager1 = this.audioManager;
            if (manager1 != null) {
                manager1.adjustStreamVolume(3, -1, 5);
            }
            if (valueOf == null || valueOf != 0) {
                return true;
            }
            ((ImageView) Objects.requireNonNull(getBindingData()).playerView.exoVolume).setImageResource(R.drawable.ic_mute);
            this.isMutes = true;
            return true;
        } else if (action != 0) {
            return true;
        } else {
            AudioManager manager = this.audioManager;
            if (manager != null) {
                Integer.valueOf(manager.getStreamVolume(3));
            }
            AudioManager manager1 = this.audioManager;
            if (manager1 != null) {
                manager1.adjustStreamVolume(3, 1, 5);
            }
            ((ImageView) Objects.requireNonNull(getBindingData()).playerView.exoVolume).setImageResource(R.drawable.ic_volume);
            this.isMutes = false;
            return true;
        }
    }

    private final void itemReset() {
        effectModel = null;
        String s = getString(R.string.normal);
        Intrinsics.checkNotNullExpressionValue(s, "getString(R.string.normal)");
        effectModel = new EffectModel(0, s, "normal", 0, 0, 0, true);
    }

    public String byteToMB(long jj) {
        DecimalFormat format = new DecimalFormat("0.00");
        float ff = (float) jj;
        if (ff < 1048576.0f) {
            return Intrinsics.stringPlus(format.format((double) (ff / 1024.0f)), "KB");
        }
        if (ff < 1.07374182E9f) {
            return Intrinsics.stringPlus(format.format((double) (ff / 1048576.0f)), "MB");
        }
        return ff < 1.09951163E12f ? Intrinsics.stringPlus(format.format((double) (ff / 1.07374182E9f)), "GB") : "";
    }

}
