package com.voice.changer.speechshift.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.voice.changer.speechshift.fragments.FragmentAllEffects;
import com.voice.changer.speechshift.fragments.FragmentAmbientEffects;

import kotlin.jvm.internal.Intrinsics;

public final class EffectVoiceAdapter extends FragmentPagerAdapter {
    public int getCount() {
        return 5;
    }

    public EffectVoiceAdapter(FragmentManager manager) {
        super(manager);
        Intrinsics.checkNotNullParameter(manager, "fm");
    }

    @NonNull
    public Fragment getItem(int pos) {
        if (pos == 0) {
            return new FragmentAllEffects();
        }
        if (pos == 1) {
            return new FragmentAmbientEffects();
        }
        return new FragmentAllEffects();
    }
}
