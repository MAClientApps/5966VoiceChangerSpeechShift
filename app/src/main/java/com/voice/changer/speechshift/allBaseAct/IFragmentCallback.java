package com.voice.changer.speechshift.allBaseAct;

import android.content.Context;
import android.os.Bundle;

public interface IFragmentCallback {
    Context getContext();

    void showActivity(Class<?> cls, Bundle bundle);

}
