package com.voice.changer.speechshift.allBaseAct;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.voice.changer.speechshift.getApiData.InterfaceDataManager;
import com.voice.changer.speechshift.getApiData.appScheduler.SchedularProvider;
import com.voice.changer.speechshift.viewModel.ChangeEffectViewModel;
import com.voice.changer.speechshift.viewModel.CreationViewModel;
import com.voice.changer.speechshift.viewModel.DeviceMusicViewModel;
import com.voice.changer.speechshift.viewModel.MainActViewModel;
import com.voice.changer.speechshift.viewModel.MusicPlayerViewModel;
import com.voice.changer.speechshift.viewModel.OpenFileViewModel;
import com.voice.changer.speechshift.viewModel.PermissionViewModel;
import com.voice.changer.speechshift.viewModel.RecordingViewModel;
import com.voice.changer.speechshift.viewModel.SaveViewModel;
import com.voice.changer.speechshift.viewModel.SplashActViewModel;
import com.voice.changer.speechshift.viewModel.CreationStudioViewModel;
import com.voice.changer.speechshift.viewModel.TextToAudioViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import kotlin.jvm.internal.Intrinsics;

@Singleton
public final class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Context context;
    private final InterfaceDataManager mInterfaceDataManager;
    private final SchedularProvider mScheduler;

    @Inject
    public ViewModelFactory(Context context2, InterfaceDataManager interfaceDataManager, SchedularProvider schedularProvider) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(interfaceDataManager, "mDataManager");
        Intrinsics.checkNotNullParameter(schedularProvider, "mScheduler");
        this.context = context2;
        this.mInterfaceDataManager = interfaceDataManager;
        this.mScheduler = schedularProvider;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        T t;
        if (cls.isAssignableFrom(SplashActViewModel.class)) {
            t = (T) new SplashActViewModel();
        } else if (cls.isAssignableFrom(MainActViewModel.class)) {
            t = (T) new MainActViewModel();
        } else if (cls.isAssignableFrom(TextToAudioViewModel.class)) {
            t = (T) new TextToAudioViewModel();
        } else if (cls.isAssignableFrom(RecordingViewModel.class)) {
            t = (T) new RecordingViewModel();
        } else if (cls.isAssignableFrom(MusicPlayerViewModel.class)) {
            t = (T) new MusicPlayerViewModel();
        } else if (cls.isAssignableFrom(SaveViewModel.class)) {
            t = (T) new SaveViewModel();
        } else if (cls.isAssignableFrom(OpenFileViewModel.class)) {
            t = (T) new OpenFileViewModel();
        } else if (cls.isAssignableFrom(DeviceMusicViewModel.class)) {
            t = (T) new DeviceMusicViewModel();
        } else if (cls.isAssignableFrom(CreationViewModel.class)) {
            t = (T) new CreationViewModel();
        } else if (cls.isAssignableFrom(CreationStudioViewModel.class)) {
            t = (T) new CreationStudioViewModel();
        } else if (cls.isAssignableFrom(ChangeEffectViewModel.class)) {
            t = (T) new ChangeEffectViewModel();
        } else if (cls.isAssignableFrom(PermissionViewModel.class)) {
            t = (T) new PermissionViewModel();
        } else {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown ViewModel class: ", cls.getName()));
        }
        if (t instanceof BaseViewModel) {
            ((BaseViewModel) t).initData(this.context, this.mInterfaceDataManager, this.mScheduler);
        }
        return t;
    }
}
