package com.voice.changer.speechshift.allBaseAct;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.voice.changer.speechshift.R;

public abstract class BaseDialog<DB extends ViewDataBinding> extends Dialog {
    boolean canAble;
    public DB dataBinding;

    public abstract void bindId();

    public abstract int getDialogView();

    public abstract void initViews();

    public abstract void setLanguage();

    public BaseDialog(Activity activity, boolean z) {
        super(activity, R.style.BottomSheetDialogTheme);
        this.canAble = z;
    }

    public final DB getDataBinding() {
        DB db = this.dataBinding;
        if (db != null) {
            return db;
        }
        return null;
    }

    public final void setDataBinding(DB db) {
        this.dataBinding = db;
    }

    public void onCreate(Bundle bundle) {
        setLanguage();
        super.onCreate(bundle);
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getDialogView(), (ViewGroup) null, false);
        setDataBinding((DB) inflate);
        setContentView(getDataBinding().getRoot());
        Window window = getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
        setCancelable(this.canAble);
        initViews();
        bindId();
    }
}
