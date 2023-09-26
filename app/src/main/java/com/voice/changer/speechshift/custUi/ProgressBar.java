package com.voice.changer.speechshift.custUi;

import android.annotation.SuppressLint;

import com.voice.changer.speechshift.getApiData.appScheduler.SchedularProvider;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.BehaviorSubject;

public final class ProgressBar extends Observable<Boolean> {
    Observable<Object> objectObservable;
    private final BehaviorSubject<Integer> integerBehaviorSubject;

    public ProgressBar(SchedularProvider provider) {
        BehaviorSubject<Integer> aDefault = BehaviorSubject.createDefault(0);
        this.integerBehaviorSubject = aDefault;
        this.objectObservable = aDefault.subscribeOn(provider.getIo()).observeOn(provider.getUi()).onErrorReturnItem(0).map((Function<Integer, Object>) integer -> ProgressBar.getProgresses(integer)).share();
    }

    public static Boolean getProgresses(Integer num) {
        return Boolean.valueOf(num.intValue() > 0);
    }

    @SuppressLint("CheckResult")
    public void subscribeActual(Observer<? super Boolean> observer) {
        this.objectObservable.subscribe((Consumer<? super Object>) observer);
    }

    public void reset() {
        this.integerBehaviorSubject.onNext(0);
    }
}
