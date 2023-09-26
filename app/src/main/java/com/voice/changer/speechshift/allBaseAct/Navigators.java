package com.voice.changer.speechshift.allBaseAct;

import android.os.Bundle;

import kotlin.reflect.KClass;

public interface Navigators {
    void fragmentRequestInject(BaseFragment<?, ?> baseFragment);

    void navigate(int i, Bundle bundle, boolean z);

    void navigateUp();

    void onFragmentResumed(BaseFragment<?, ?> baseFragment);

    void nextActivity(Class<?> cls, Bundle bundle);

    void switchFragment(KClass<?> kClass, Bundle bundle, boolean z);

      final class DefaultImpl {

        public static  void showDefaultAct(Navigators navigators, Class cls, Bundle bundle, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    bundle = null;
                }
                navigators.nextActivity(cls, bundle);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showActivity");
        }
    }
}
