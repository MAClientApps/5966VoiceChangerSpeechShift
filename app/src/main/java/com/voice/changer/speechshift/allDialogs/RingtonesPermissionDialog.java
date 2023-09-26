package com.voice.changer.speechshift.allDialogs;

import android.app.Activity;
import android.os.Handler;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.databinding.DialogPremissionRingtoneBinding;
import com.voice.changer.speechshift.myAdsClasses.ApplovinOpenAppAds;
import com.voice.changer.speechshift.allBaseAct.BaseDialog;
import com.voice.changer.speechshift.custUi.SetLanguage;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public final class RingtonesPermissionDialog extends BaseDialog<DialogPremissionRingtoneBinding> {
    private Activity acts;
    private final Function0 allowClick;

    public int getDialogView() {
        return R.layout.dialog_premission_ringtone;
    }

    public Activity getActs() {
        return this.acts;
    }

    public void setActs(Activity activity2) {
        this.acts = activity2;
    }

    public Function0 getAllowClick() {
        return this.allowClick;
    }

    public RingtonesPermissionDialog(Activity activity2, boolean z, Function0<Unit> function0) {
        super(activity2, z);
        this.acts = activity2;
        this.allowClick = function0;
    }

    public void setLanguage() {
        SetLanguage.setLocale(this.acts);
    }

    public void initViews() {

    }

    public void bindId() {
        assert getDataBinding() != null;
        TapClick.tap(getDataBinding().tvCancel, view -> {
            dismiss();
            return null;
        });
        TapClick.tap(getDataBinding().tvAllow, view -> {
            new Handler().postDelayed(() -> ApplovinOpenAppAds.isScreenOnOff = true, 500);
            getAllowClick().invoke();
            dismiss();
            return null;
        });
    }
}
