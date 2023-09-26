package com.voice.changer.speechshift.allBaseAct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.voice.changer.speechshift.R;

public abstract class BaseBottomSheetDialog<DB extends ViewDataBinding> extends BottomSheetDialogFragment {
    boolean canAble;
    public DB dataBinding;

    public abstract void bindView();

    public abstract int getContentView();

    public int getTheme() {
        return R.style.BaseBottomSheetDialog;
    }

    public abstract void initView();

    public void onDestroyView() {
        super.onDestroyView();
    }

    public BaseBottomSheetDialog(Activity activity, boolean z) {
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

    @SuppressLint("RestrictedApi")
    public void setupDialog(@NonNull Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getContentView(), (ViewGroup) null, false);
        setDataBinding((DB) inflate);
        dialog.setContentView(getDataBinding().getRoot());
        setCancelable(this.canAble);
        initView();
        bindView();
    }
}
