package com.voice.changer.speechshift.custUi.constatnt;

import android.view.View;

import com.voice.changer.speechshift.tapListener.CustomTapListener;

import kotlin.jvm.functions.Function1;

public final class TapClick {
    public static void tap(View view, Function1 function1) {
        view.setOnClickListener(new CustomTapListener() {
            @Override
            public void onTap(View view) {
                function1.invoke(view);
            }
        });
    }

}
