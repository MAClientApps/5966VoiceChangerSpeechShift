package com.voice.changer.speechshift.getApiData.appScheduler;

import io.reactivex.Scheduler;

public interface SchedularProvider {
    Scheduler getScheduler2();

    Scheduler getIo();

    Scheduler getUi();
}
