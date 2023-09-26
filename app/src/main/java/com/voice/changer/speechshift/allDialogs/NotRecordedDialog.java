package com.voice.changer.speechshift.allDialogs;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseDialog;
import com.voice.changer.speechshift.custUi.SetLanguage;
import com.voice.changer.speechshift.custUi.constatnt.AppDataException;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.DialogAudioNotSaveBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public final class NotRecordedDialog extends BaseDialog<DialogAudioNotSaveBinding> {
    private final Activity act;
    private String strContent;
    private final Function0 function0;

    private String strPos;

    public int getDialogView() {
        return R.layout.dialog_audio_not_save;
    }

    public Function0 getFunction0() {
        return function0;
    }

    public NotRecordedDialog(String str, String str2, Activity activity2, boolean z, Function0<Unit> function0) {
        super(activity2, z);
        this.strContent = str;
        this.strPos = str2;
        this.act = activity2;
        this.function0 = function0;
    }

    public void setLanguage() {
        SetLanguage.setLocale(this.act);
    }

    public void initViews() {
        Window getWin = getWindow();
        WindowManager.LayoutParams layoutParams = getWin == null ? null : getWin.getAttributes();
        if (layoutParams != null) {
            layoutParams.width = (int) (((double) AppDataException.getWithMetrics(this.act)) * 0.9d);
        }
        getDataBinding().tvContent.setText(this.strContent);
        getDataBinding().tvExit.setText(strPos);
    }

    public void bindId() {
        TapClick.tap(getDataBinding().tvCancel, view -> {
            dismiss();
            return null;
        });
        TapClick.tap(getDataBinding().tvExit, view -> {
            getFunction0().invoke();
            dismiss();
            return null;
        });
    }
}
