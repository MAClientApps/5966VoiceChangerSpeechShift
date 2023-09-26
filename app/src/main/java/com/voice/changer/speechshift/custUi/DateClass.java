package com.voice.changer.speechshift.custUi;

import java.text.DecimalFormat;

import kotlin.jvm.internal.Intrinsics;

public final class DateClass {
    public static final DateClass DATE_CLASS = new DateClass();

    private DateClass() {
    }

    public String byteToMB(long jj) {
        DecimalFormat format = new DecimalFormat("0.00");
        float ff = (float) jj;
        if (ff < 1048576.0f) {
            return Intrinsics.stringPlus(format.format((double) (ff / 1024.0f)), "KB");
        }
        if (ff < 1.07374182E9f) {
            return Intrinsics.stringPlus(format.format((double) (ff / 1048576.0f)), "MB");
        }
        return ff < 1.09951163E12f ? Intrinsics.stringPlus(format.format((double) (ff / 1.07374182E9f)), "GB") : "";
    }

}
