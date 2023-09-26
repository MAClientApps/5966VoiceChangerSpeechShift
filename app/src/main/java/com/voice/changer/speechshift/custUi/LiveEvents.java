package com.voice.changer.speechshift.custUi;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class LiveEvents<T> extends MutableLiveData<T> {

    public final AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull final Observer<? super T> observer) {
        super.observe(lifecycleOwner, t -> {
            if (LiveEvents.this.atomicBoolean.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    public void setValue(T t) {
        this.atomicBoolean.set(true);
        super.setValue(t);
    }

    public void call() {
        setValue((T) null);
    }
}
