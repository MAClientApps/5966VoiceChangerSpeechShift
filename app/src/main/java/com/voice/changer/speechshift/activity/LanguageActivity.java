package com.voice.changer.speechshift.activity;

import static com.voice.changer.speechshift.activity.SplashActivity.IntentFromSetting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.voice.changer.speechshift.MainApplication;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.databinding.ActivityLanguageBinding;
import com.voice.changer.speechshift.langClass.AdapterLanguage;
import com.voice.changer.speechshift.langClass.LanguageModel;
import com.voice.changer.speechshift.myAdsClasses.ApplovinBannerAds;

import java.util.ArrayList;
import java.util.List;

public class LanguageActivity extends AppCompatActivity implements AdapterLanguage.onClickLangItem {

    ActivityLanguageBinding binding;
    private String strCode;
    private final List<LanguageModel> arrayList = new ArrayList<>();
    public static String strLangCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApplovinBannerAds.getInstance().showBannerAds(binding.adsLayout, LanguageActivity.this);

        MainApplication.getPrefManager().setFirstInstallApp(false);
        strCode = MainApplication.getPrefManager().getDefaultLanguage();
        setLanguages();

        binding.txtDone.setOnClickListener(v -> {
            nextAct();
        });
    }

    private void setLanguages() {
        arrayList.clear();
        arrayList.addAll(getLanguageData());
        AdapterLanguage adapter = new AdapterLanguage(this, this);
        adapter.setLang(arrayList);
        binding.rvLanguage.setLayoutManager(new GridLayoutManager(this, 1));
        binding.rvLanguage.setAdapter(adapter);
        if (strCode == null) {
            adapter.setDefaultCheck();
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setCheck(strCode.equals(arrayList.get(i).getCode()));
        }
        adapter.notifyDataSetChanged();
    }

    private List<LanguageModel> getLanguageData() {
        ArrayList langList = new ArrayList();
        langList.add(new LanguageModel("English", "en", "(English)", R.drawable.ic_lang_english));
        langList.add(new LanguageModel("French", "fr", "(Français)", R.drawable.ic_lang_france));
        langList.add(new LanguageModel("Portuguese", "pt", "(Português)", R.drawable.ic_lang_portuguese));
        langList.add(new LanguageModel("Mandarin", "zh", "(普通话)", R.drawable.ic_lang_mandarin));
        langList.add(new LanguageModel("Hindi", "hi", "(हिंदी)", R.drawable.ic_lang_hindi));
        langList.add(new LanguageModel("Arabic", "ar", "(عربي)", R.drawable.ic_lang_arabic));
        langList.add(new LanguageModel("JAPANESE", "ja", "(日本)", R.drawable.ic_lang_japanese));
        langList.add(new LanguageModel("German", "de", "(Deutsch)", R.drawable.ic_lang_german));
        langList.add(new LanguageModel("Bengali", "bn", "(বাংলা)", R.drawable.ic_lang_bengali));
        langList.add(new LanguageModel("Urdu", "ur", "(اردو)", R.drawable.ic_lang_urdu));
        langList.add(new LanguageModel("Russian", "ru", "(Русский)", R.drawable.ic_lang_russian));
        langList.add(new LanguageModel("Spanish", "es", "(Española)", R.drawable.ic_lang_spanish));
        return langList;
    }

    @Override
    public void languageSelected(LanguageModel languageModel) {
        strLangCode = languageModel.getCode();
    }

    private void nextAct() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(LanguageActivity.this);
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        edit.putBoolean("langKey", true);
        edit.apply();

        MainApplication.getPrefManager().setAppLanguage(strLangCode);

        if (hasPermissions(this)) {
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(LanguageActivity.this, R.string.language_selected, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, PermissionActivity.class));
        }

    }

    @Override
    public void onBackPressed() {
        if (IntentFromSetting) {
            super.onBackPressed();
        } else {
            if (hasPermissions(this)) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, PermissionActivity.class));
            }
        }
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