package com.voice.changer.speechshift.activity;

import static com.voice.changer.speechshift.activity.SplashActivity.IntentFromSetting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.content.FileProvider;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.voice.changer.speechshift.BuildConfig;
import com.voice.changer.speechshift.FilenameUtils;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.allDialogs.SetRingtoneDialog;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.custUi.MobileState;
import com.voice.changer.speechshift.custUi.constatnt.RingtonePermission;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.ActivityMusicPlayerBinding;
import com.voice.changer.speechshift.viewModel.MusicPlayerViewModel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

public final class MusicPlayerActivity extends BaseActivity<MusicPlayerViewModel, ActivityMusicPlayerBinding> {

    public FrameLayout ads;
    public RelativeLayout llyBanner;

    private float curVolume;
    private ExoPlayer player;
    private boolean isMuteAudio = true;
    private String strPath = "";
    String fileName = "";
    String strDuration = "";
    private Context previewActivity;

    @Override
    public Class<MusicPlayerViewModel> createViewModel() {
        return MusicPlayerViewModel.class;
    }

    @Override
    public int getContent() {
        previewActivity = this;
        return R.layout.activity_music_player;
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
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void mainView() {

        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button press here.
                backPressed();
            }
        });
        //ApplovinInterAds.getInstance().loadInterstitialAd(MusicPlayerActivity.this);

       // ApplovinInterAds.getInstance().showInterstitialAd();


        SharedPreferences preferences = getSharedPreferences("MY_PRE", 0);
        TapClick.tap(getBindingData().ivBack, (Function1<View, Unit>) view -> {
            backPressed();
            return null;
        });

        Object systemService = getSystemService(Context.AUDIO_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        ((AudioManager) systemService).setStreamMute(3, false);
        try {
            this.fileName = getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT()) != null ? String.valueOf(getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT())) : "";
            this.strPath = getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE()) != null ? String.valueOf(getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE())) : "";
            this.strDuration = getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_DURATION_VOICE()) != null ? String.valueOf(getIntent().getStringExtra(AppConstant.APP_CONSTANT.getKEY_DURATION_VOICE())) : "";
            SimpleExoPlayer build = new SimpleExoPlayer.Builder(this).build();
            this.player = build;
            String agent = Util.getUserAgent(this, getString(R.string.app_name));
            ProgressiveMediaSource mediaSource = new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(this, agent), new DefaultExtractorsFactory()).createMediaSource(MediaItem.fromUri(Uri.parse(new File(this.strPath).getPath())));
            ProgressiveMediaSource source = mediaSource;
            PlayerView view = getBindingData().playerView;
            ExoPlayer player1 = this.player;
            ExoPlayer player2 = null;
            if (player1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                player1 = null;
            }
            view.setPlayer(player1);
            getBindingData().playerView.setKeepScreenOn(true);
            ExoPlayer player3 = this.player;
            if (player3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                player3 = null;
            }
            player3.prepare(source);
            ExoPlayer player4 = this.player;
            if (player4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            } else {
                player2 = player4;
            }

            String sName = fileName;
            String subString = sName.substring(StringsKt.lastIndexOf(sName, "/", 0, false) + 1);
            Intrinsics.checkNotNullExpressionValue(subString, "this as java.lang.String).substring(startIndex)");
            ((TextView) getBindingData().playerView.findViewById(R.id.tv_name)).setText(subString);
            new Handler().postDelayed(() ->
                    ((TextView) getBindingData().playerView.findViewById(R.id.tv_detail)).setText(((TextView) getBindingData().playerView.findViewById(R.id.exo_duration)).getText() + " | " + byteToMB(new File(strPath).length())), 500L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ads = findViewById(R.id.ads);
        llyBanner = findViewById(R.id.ll_banner);

       // ApplovinBannerAds.getInstance().showBannerAds(llyBanner, MusicPlayerActivity.this);
    }

    @Override
    public void initViews() {
        TapClick.tap(getBindingData().playerView.findViewById(R.id.exo_volume), (Function1<View, Unit>) view -> {
            boolean z;
            ExoPlayer mExoPlayer;
            float f;
            boolean z2;
            ExoPlayer exoPlayer2;
            ExoPlayer exoPlayer3;
            z = isMuteAudio;
            ExoPlayer exoPlayer4 = null;
            if (!z) {
                curVolume = 1.0f;
                mExoPlayer = player;
                if (mExoPlayer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                } else {
                    exoPlayer4 = mExoPlayer;
                }
                f = curVolume;
                exoPlayer4.setVolume(f);
                ((ImageView) getBindingData().playerView.findViewById(R.id.exo_volume)).setImageResource(R.drawable.ic_volume);
            } else {
                exoPlayer2 = player;
                if (exoPlayer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                    exoPlayer2 = null;
                }
                curVolume = exoPlayer2.getVolume();
                ((ImageView) getBindingData().playerView.findViewById(R.id.exo_volume)).setImageResource(R.drawable.ic_mute);
                exoPlayer3 = player;
                if (exoPlayer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                } else {
                    exoPlayer4 = exoPlayer3;
                }
                exoPlayer4.setVolume(0.0f);
            }
            z2 = isMuteAudio;
            isMuteAudio = !z2;

            return Unit.INSTANCE;
        });
        TapClick.tap(getBindingData().llSetRingtone, (Function1<View, Unit>) view -> {
            if (RingtonePermission.checkSystemWritePermission(MusicPlayerActivity.this)) {
                new SetRingtoneDialog(MusicPlayerActivity.this, true, phoneState -> {

                    int[] iArr = new int[MobileState.values().length];
                    iArr[MobileState.STATE_RINGTONE.ordinal()] = 1;
                    iArr[MobileState.STATE_ALARM.ordinal()] = 2;
                    iArr[MobileState.STATE_NOTIFICATION.ordinal()] = 3;
                    int[] EnumArray = iArr;

                    int i = EnumArray[phoneState.ordinal()];
                    if (i == 1) {
                        settingsPhoneRing(MusicPlayerActivity.this, strPath, MobileState.STATE_RINGTONE, true);
                    } else if (i == 2) {
                        settingsPhoneRing(MusicPlayerActivity.this, strPath, MobileState.STATE_ALARM, true);
                    } else if (i == 3) {
                        settingsPhoneRing(MusicPlayerActivity.this, strPath, MobileState.STATE_NOTIFICATION, true);
                    }
                    return null;
                }).show();
            } else {
                AppConstant.APP_CONSTANT.setCheckResumePermissionRingtone(true);
                RingtonePermission.openAndroidPermissionsMenu(MusicPlayerActivity.this);
            }
            return null;
        });
        TapClick.tap(getBindingData().llReRecord, (Function1<View, Unit>) view -> {
            DefaultImpl.showDefaultAct(MusicPlayerActivity.this, RecordingActivity.class, null, 2, null);
            ChangeEffectActivity.Companion.setEffectModelSelected(null);
            backPressed();
            return null;
        });
        TapClick.tap(getBindingData().tvShare, (Function1<View, Unit>) view -> {
            String str;
            str = strPath;
            shareFileProject(previewActivity, str);
            return Unit.INSTANCE;
        });
        TapClick.tap(getBindingData().ivHome, (Function1<View, Unit>) view -> {
            ChangeEffectActivity.Companion.setEffectModelSelected(null);
            IntentFromSetting = true;
            DefaultImpl.showDefaultAct(MusicPlayerActivity.this, MainActivity.class, null, 2, null);
            backPressed();
            return null;
        });

        String name = FilenameUtils.getBaseName(this.strPath);
        Intrinsics.checkNotNullExpressionValue(name, "getBaseName(path)");
    }

    public void shareFileProject(Context context, String str) {
        Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(str));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        String msgShare;
        msgShare = context.getResources().getString(R.string.appShare) + "\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n";
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, msgShare);
        intent.putExtra(Intent.EXTRA_STREAM, uriForFile);

        new Handler().postDelayed(() -> FilenameUtils.isScreenOnOff = true, 500);

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
    }

    private final void pauseMusicPlayer() {
        ExoPlayer player1 = this.player;
        ExoPlayer exoPlayer = null;
        if (player1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            player1 = null;
        }
        player1.setPlayWhenReady(false);
        ExoPlayer exoPlayer1 = this.player;
        if (exoPlayer1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
        } else {
            exoPlayer = exoPlayer1;
        }
        exoPlayer.getPlaybackState();
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseMusicPlayer();
        new Handler(Looper.getMainLooper()).postDelayed(() -> FilenameUtils.isScreenOnOff = true, 500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ExoPlayer player1 = this.player;
        ExoPlayer exoPlayer = null;
        if (player1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            player1 = null;
        }
        player1.stop();
        ExoPlayer exoplayer3 = this.player;
        if (exoplayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
        } else {
            exoPlayer = exoplayer3;
        }
        exoPlayer.release();
    }


    public void backPressed() {
        finish();
    }

    public void settingsPhoneRing(Context context, String path, MobileState stateMobile, boolean z) {

        int[] iArr = new int[MobileState.values().length];
        iArr[MobileState.STATE_ALARM.ordinal()] = 1;
        iArr[MobileState.STATE_NOTIFICATION.ordinal()] = 2;
        iArr[MobileState.STATE_RINGTONE.ordinal()] = 3;
        int[] switchMapping = iArr;

        File file;
        try {
            file = new File(path);
            Uri fromFile = Uri.fromFile(file);
            Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(ring)");
            int i = switchMapping[stateMobile.ordinal()];
            if (i == 1) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 4, fromFile);
                if (z) {
                    Toast makeText = Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG);
                    Intrinsics.checkNotNullExpressionValue(makeText, "makeText(\n              …ONG\n                    )");
                    makeText.show();
                }
            } else if (i == 2) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 2, fromFile);
                if (z) {
                    Toast makeText2 = Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG);
                    Intrinsics.checkNotNullExpressionValue(makeText2, "makeText(\n              …ONG\n                    )");
                    makeText2.show();
                }
            } else if (i == 3) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 1, fromFile);
                if (z) {
                    Toast makeText3 = Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG);
                    Intrinsics.checkNotNullExpressionValue(makeText3, "makeText(\n              …ONG\n                    )");
                    makeText3.show();
                }
            }
        } catch (Throwable th) {
            Log.e("setAsDefaultRingtone err", th.toString());
        }
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
