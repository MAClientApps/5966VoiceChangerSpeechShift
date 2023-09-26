package com.voice.changer.speechshift.getApiData.appScheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class AppProvider implements SchedularProvider {
    private final Scheduler scheduler2;

    private final Scheduler scheduler;

    private final Scheduler scheduler1;

    public AppProvider() {
        Scheduler computation2 = Schedulers.computation();
        this.scheduler2 = computation2;
        Scheduler io = Schedulers.io();
        this.scheduler = io;
        Scheduler thread = AndroidSchedulers.mainThread();
        this.scheduler1 = thread;
    }

    public Scheduler getScheduler2() {
        return this.scheduler2;
    }

    public Scheduler getIo() {
        return this.scheduler;
    }

    public Scheduler getUi() {
        return this.scheduler1;
    }
}
