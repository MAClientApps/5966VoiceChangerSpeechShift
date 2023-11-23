package com.voice.changer.speechshift.fragments;


import android.os.Bundle;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.activity.ChangeEffectActivity;
import com.voice.changer.speechshift.activity.OpenFileActivity;
import com.voice.changer.speechshift.adapters.AudioAdapter;
import com.voice.changer.speechshift.databinding.FragmentAudioBinding;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.viewModel.DeviceMusicViewModel;

import java.util.ArrayList;
import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;

public final class FragmentAudio extends BaseFragment<DeviceMusicViewModel, FragmentAudioBinding> {


    public FrameLayout ads;
    public RelativeLayout llyBanner;

    private AudioAdapter audioAdapter;
    private FragmentAudio fragmentAudio;

    public Class<DeviceMusicViewModel> createViewModel() {
        return DeviceMusicViewModel.class;
    }

    public int getFragResourceLayout() {
        fragmentAudio = this;
        return R.layout.fragment_audio;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void bindViews() {


        ads = getDataBinding().ads;
        llyBanner = getDataBinding().llBanner;

       // ApplovinBannerAds.getInstance().showBannerAds(llyBanner,requireActivity());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        getDataBinding().rclAudio.setLayoutManager(manager);
        AudioAdapter audioAdapter = new AudioAdapter(requireContext(), new ArrayList(), audioModel -> {
            Intrinsics.checkNotNullParameter(audioModel, "it");
            Bundle bundles = new Bundle();
            bundles.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), audioModel.getPath());
            bundles.putString(AppConstant.APP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS(), "AudioFragment");
            showActivity(ChangeEffectActivity.class, bundles);
            return null;
        });
        this.audioAdapter = audioAdapter;
        getDataBinding().rclAudio.setAdapter(audioAdapter);
    }

    public void bindViewModels() {
        Objects.requireNonNull(getViewModel()).getAudioDataClass(requireContext(), getDataBinding().llLoading, getDataBinding().ads, getDataBinding().noData);
        OpenFileActivity.Companion.getLiveSortCreateAudio().observe(requireActivity(), num -> {
            if (num != null && num == 1) {
                Objects.requireNonNull(fragmentAudio.getViewModel()).getMutableLiveData().observe(fragmentAudio.requireActivity(), list -> {
                    AudioAdapter audioAdapter;
                    if ((audioAdapter = fragmentAudio.audioAdapter) != null) {
                        audioAdapter.sortListRefresh(list);
                    }
                });
            } else if (num != null && num == 2) {
                Objects.requireNonNull(fragmentAudio.getViewModel()).getMutableLiveData().observe(fragmentAudio.requireActivity(), list -> {
                    AudioAdapter audioAdapter;
                    if (list != null && (audioAdapter = fragmentAudio.audioAdapter) != null) {
                        audioAdapter.oldDataSort(list);
                    }
                });
            }
        });
        OpenFileActivity.Companion.getLiveSortNameAudio().observe(requireActivity(), num -> {
            Intrinsics.checkNotNullParameter(fragmentAudio, "this$0");
            if (num != null && num == 1) {
                Objects.requireNonNull(fragmentAudio.getViewModel()).getMutableLiveData().observe(fragmentAudio.requireActivity(), list -> {
                    AudioAdapter audioAdapter;
                    Intrinsics.checkNotNullParameter(fragmentAudio, "this$0");
                    if (list != null && (audioAdapter = fragmentAudio.audioAdapter) != null) {
                        audioAdapter.sortByFileName(list);
                    }
                });
            } else if (num != null && num == 2) {
                fragmentAudio.getViewModel().getMutableLiveData().observe(fragmentAudio.requireActivity(), list -> {
                    AudioAdapter audioAdapter;
                    Intrinsics.checkNotNullParameter(fragmentAudio, "this$0");
                    if (list != null && (audioAdapter = fragmentAudio.audioAdapter) != null) {
                        audioAdapter.sortOldByName(list);
                    }
                });
            }
        });
    }


}
