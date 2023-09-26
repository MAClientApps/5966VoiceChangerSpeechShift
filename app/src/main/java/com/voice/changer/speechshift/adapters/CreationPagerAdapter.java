package com.voice.changer.speechshift.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.voice.changer.speechshift.fragments.FragmentAudioStudio;

public final class CreationPagerAdapter extends FragmentPagerAdapter {
    public int getCount() {
        return 1;
    }

    public CreationPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @NonNull
    public Fragment getItem(int i) {
        return new FragmentAudioStudio();
    }
}
