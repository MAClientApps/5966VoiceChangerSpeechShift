package com.voice.changer.speechshift.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.voice.changer.speechshift.fragments.FragmentAudio;

import kotlin.jvm.internal.Intrinsics;

public final class DeviceVideoPager extends FragmentPagerAdapter {
    public int getCount() {
        return 1;
    }
    public DeviceVideoPager(FragmentManager manager) {
        super(manager);
        Intrinsics.checkNotNullParameter(manager, "fm");
    }

    @NonNull
    public Fragment getItem(int i) {
        return new FragmentAudio();
    }
}
