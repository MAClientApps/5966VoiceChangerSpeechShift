package com.voice.changer.speechshift.allBaseAct;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.voice.changer.speechshift.custUi.ProgressBar;
import com.voice.changer.speechshift.getApiData.InterfaceDataManager;
import com.voice.changer.speechshift.getApiData.appScheduler.SchedularProvider;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseViewModel extends ViewModel {
    public Scheduler computation;
    @SuppressLint("StaticFieldLeak")
    Context context;
    public InterfaceDataManager interfaceDataManager;
    public IFragmentCallback fragmentCallback;

    public Scheduler io;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    public Navigators navigation;
    public ProgressBar progressBar;
    public SchedularProvider scheduler;
    public Scheduler ui;


    public final void setFragmentCallback(IFragmentCallback iFragmentCallback) {
        Intrinsics.checkNotNullParameter(iFragmentCallback, "<set-?>");
        this.fragmentCallback = iFragmentCallback;
    }

    public final Context getContext() {
        Context context2 = this.context;
        if (context2 != null) {
            return context2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final void setNavigation(Navigators navigators) {
        Intrinsics.checkNotNullParameter(navigators, "<set-?>");
        this.navigation = navigators;
    }

    public final void setDataManager(InterfaceDataManager interfaceDataManager2) {
        Intrinsics.checkNotNullParameter(interfaceDataManager2, "<set-?>");
        this.interfaceDataManager = interfaceDataManager2;
    }

    public final void setScheduler(SchedularProvider schedularProvider) {
        Intrinsics.checkNotNullParameter(schedularProvider, "<set-?>");
        this.scheduler = schedularProvider;
    }

    public final Scheduler getIo() {
        Scheduler scheduler2 = this.io;
        if (scheduler2 != null) {
            return scheduler2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("io");
        return null;
    }

    public final void setIo(Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(scheduler2, "<set-?>");
        this.io = scheduler2;
    }

    public final Scheduler getUi() {
        Scheduler scheduler2 = this.ui;
        if (scheduler2 != null) {
            return scheduler2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ui");
        return null;
    }

    public final void setUi(Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(scheduler2, "<set-?>");
        this.ui = scheduler2;
    }

    public final void setComputation(Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(scheduler2, "<set-?>");
        this.computation = scheduler2;
    }

    public final ProgressBar getProgressBar() {
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            return progressBar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        return null;
    }

    public final void setProgressBar(ProgressBar progressBar) {
        Intrinsics.checkNotNullParameter(progressBar, "<set-?>");
        this.progressBar = progressBar;
    }

    public final void initData(Context context2, InterfaceDataManager interfaceDataManager2, SchedularProvider schedularProvider) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(interfaceDataManager2, "dataManager");
        Intrinsics.checkNotNullParameter(schedularProvider, "scheduler");
        setContext(context2);
        setDataManager(interfaceDataManager2);
        setScheduler(schedularProvider);
        setIo(schedularProvider.getIo());
        setUi(schedularProvider.getUi());
        setComputation(schedularProvider.getScheduler2());
        setProgressBar(new ProgressBar(schedularProvider));
    }

    public void onCleared() {
        super.onCleared();
    }

    public final void onDestroyView() {
        getProgressBar().reset();
        this.mDisposable.clear();
    }
}
