package com.voice.changer.speechshift.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwnerKt;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.myAdsClasses.ApplovinBannerAds;
import com.voice.changer.speechshift.myAdsClasses.ApplovinInterAds;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.allBaseAct.BasePopupMenu;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.custUi.FileMethods;
import com.voice.changer.speechshift.custUi.constatnt.AppDataException;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.ActivityTxtToAudioBinding;
import com.voice.changer.speechshift.viewModel.TextToAudioViewModel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

public final class TxtToAudioActivity extends BaseActivity<TextToAudioViewModel, ActivityTxtToAudioBinding> {

    public FrameLayout ads;
    public RelativeLayout llyBanner;

    public String code = "en";
    public boolean isClickNext;
    public TextToSpeech tts;
    private TxtToAudioActivity toAudioActivity;
    int label;

    public Class<TextToAudioViewModel> createViewModel() {
        return TextToAudioViewModel.class;
    }

    public int getContent() {
        toAudioActivity = this;
        return R.layout.activity_txt_to_audio;
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

    public void onRestart() {
        super.onRestart();
        if (!this.isClickNext) {
            getBindingData().llLoading.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SetTextI18n")
    public void mainView() {

        ApplovinInterAds.getInstance().loadInterstitialAd(TxtToAudioActivity.this);
        ApplovinInterAds.getInstance().showInterstitialAd();

        getBindingData().tvLocate.setVisibility(View.VISIBLE);
        getBindingData().toolbar.tvTitle.setText("Text to Audio");
        this.isClickNext = false;


        ads = findViewById(R.id.ads);
        llyBanner = findViewById(R.id.ll_banner);

        ApplovinBannerAds.getInstance().showBannerAds(llyBanner, TxtToAudioActivity.this);
    }

    public void initViews() {
        TapClick.tap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            onBackPressed();
            return null;
        });

        TapClick.tap(getBindingData().tvNext, (Function1<View, Unit>) view -> {
            boolean z;
            z = isClickNext;
            if (StringsKt.trim(getBindingData().input.getText().toString()).toString().length() > 0)
                if (z) {
                    Toast.makeText(toAudioActivity, R.string.please_wait, Toast.LENGTH_SHORT).show();
                    return null;
                }
            if (StringsKt.trim(getBindingData().input.getText().toString()).toString().length() > 2) {
                String obj = StringsKt.trim(getBindingData().input.getText().toString()).toString();
                String directoryPath = FileMethods.getMainDirPath(toAudioActivity);
                Intrinsics.checkNotNullExpressionValue(directoryPath, "getDirectoryPath(this)");
                initTextToSpeech(obj, directoryPath);
                isClickNext = true;
            } else {
                Toast.makeText(toAudioActivity, R.string.enter_minimum_words, Toast.LENGTH_SHORT).show();
            }
            return null;
        });

        TapClick.tap(getBindingData().tvLocate, (Function1<View, Unit>) view -> {
            if (!isClickNext) {
                BasePopupMenu basePopupMenu = new BasePopupMenu(toAudioActivity, R.layout.layout_popup_menu_locate, new BasePopupMenu.PopupMenuCustomOnClickListener() {
                    @Override
                    public void initView(View view) {
                    }

                    @SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
                    @Override
                    public void onClick(int i, View view) {
                        switch (i) {
                            case R.id.tv_english:
                                code = "en";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.english));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_english, null));

                                return;
                            case R.id.tv_french:
                                code = "fr";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.french));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_france, null));

                                return;
                            case R.id.tv_german:
                                code = "de";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.german));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_german, null));

                                return;
                            case R.id.tv_hindi:
                                code = "hi";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.hindi));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_hindi, null));

                                return;
                            case R.id.tv_indonesia:
                                code = "id";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.indonesia));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_indonesia, null));

                                return;
                            case R.id.tv_portuguese:
                                code = "pt";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.portuguese));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_portuguese, null));

                                return;
                            case R.id.tv_spanish:
                                code = "es";
                                getBindingData().tvCountry.setText(getResources().getString(R.string.spanish));
                                getBindingData().imgFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_spanish, null));
                                return;
                            default:
                        }
                    }
                });
                basePopupMenu.showRight(getBindingData().imgArrow);
            }
            return null;
        });
    }

    public void initTextToSpeech(String str, String strPath) {
        tts = new TextToSpeech(this, i -> {
            if (i == -1) {
                Toast.makeText(toAudioActivity, toAudioActivity.getString(R.string.Error), Toast.LENGTH_SHORT).show();
            } else if (i == 0) {
                TextToSpeech textToSpeech = TxtToAudioActivity.this.tts;
                Integer valueOf = textToSpeech == null ? null : textToSpeech.setLanguage(new Locale(toAudioActivity.code));
                if ((valueOf != null && valueOf == -1) || (valueOf != null && valueOf == -2)) {
                    Toast.makeText(toAudioActivity, toAudioActivity.getString(R.string.language_is_not_supported), Toast.LENGTH_SHORT).show();
                    toAudioActivity.isClickNext = false;
                    return;
                }
                toAudioActivity.promiseEffect(str, strPath);
            }
        }, "android.intent.action.TTS_SERVICE");
    }

    public String fileCreate(String str, String str2) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append('/');
        String format = String.format("voice_%1$s", Arrays.copyOf(new Object[]{Long.valueOf(System.currentTimeMillis() / ((long) 1000))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(".wav");
        String sb2 = sb.toString();
        Bundle bundle = new Bundle();
        bundle.putString("utteranceId", str);
        File file = new File(sb2);
        try {
            if (file.exists() || file.createNewFile()) {
                TextToSpeech textToSpeech = this.tts;
                String str3 = null;
                Intrinsics.stringPlus("synthesize returns = ", textToSpeech == null ? null : Integer.valueOf(textToSpeech.synthesizeToFile(charSequence, bundle, file, sb2)));
                Uri fromFile = Uri.fromFile(file);
                if (fromFile != null) {
                    str3 = fromFile.getPath();
                }
                if (str3 == null) {
                    return "";
                }
                return str3;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    Ref.ObjectRef objectRef;

    private void promiseEffect(String str, String str2) {
        objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        AppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope(this), continuation -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                getBindingData().llLoading.setVisibility(View.VISIBLE);
                objectRef.element = "";
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, continuation -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                objectRef.element = fileCreate(str, str2);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, unit -> {
            TextToSpeech tts1 = tts;
            if (tts1 != null) {
                final Ref.ObjectRef<String> stringObjectRef = objectRef;
                tts1.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                    public void onStart(String str1) {
                    }

                    public void onDone(String str1) {
                        Bundle bundle = new Bundle();
                        bundle.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), (String) stringObjectRef.element);
                        bundle.putString(AppConstant.APP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS(), "TextAudioActivity");

                        nextActivity(ChangeEffectActivity.class, bundle);
                        isClickNext = false;
                        getBindingData().input.setText("");
                        getBindingData().llLoading.setVisibility(View.GONE);
                    }

                    public void onError(String str1) {
                        Toast.makeText(TxtToAudioActivity.this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
                        getBindingData().llLoading.setVisibility(View.GONE);
                    }
                });
            }

            return null;
        });
    }

    public void onBackPressed() {
        ApplovinInterAds.getInstance().showInterstitialAd();
        if (!isClickNext) {
            finish();
        }
    }
}
