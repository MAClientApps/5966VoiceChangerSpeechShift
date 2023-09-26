package com.voice.changer.speechshift.tapListener;

import android.os.SystemClock;
import android.view.View;

import kotlin.jvm.internal.DefaultConstructorMarker;

public abstract class CustomTapListener implements View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long deBounce = 300;
    private long lastMillClick;
    long nowTime;

    public abstract void onTap(View view);

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onClick(View view) {
        long elRealTime = SystemClock.elapsedRealtime();
        this.nowTime = elRealTime;
        if (elRealTime - this.lastMillClick > deBounce) {
            onTap(view);
        }
        this.lastMillClick = this.nowTime;
    }
}
