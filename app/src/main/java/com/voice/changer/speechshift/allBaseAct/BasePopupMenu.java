package com.voice.changer.speechshift.allBaseAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;

public final class BasePopupMenu {
    private final PopupMenuCustomOnClickListener onClickListener;
    private final View popupView;
    private final PopupWindow popupWindow;

    public interface PopupMenuCustomOnClickListener {
        void initView(View view);

        void onClick(int i, View view);
    }

    public BasePopupMenu(Context context2, int i, PopupMenuCustomOnClickListener popupMenuCustomOnClickListener) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(popupMenuCustomOnClickListener, "onClickListener");
        this.onClickListener = popupMenuCustomOnClickListener;
        Object systemService = context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        View inflate = ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(rLayoutId, null)");
        this.popupView = inflate;
        popupMenuCustomOnClickListener.initView(inflate);
        PopupWindow popupWindow2 = new PopupWindow(inflate, -2, -2, true);
        this.popupWindow = popupWindow2;
        popupWindow2.setElevation(10.0f);
        LinearLayout linearLayout = (LinearLayout) inflate;
        int i2 = 0;
        int childCount = linearLayout.getChildCount();
        while (i2 < childCount) {
            int i3 = i2 + 1;
            View childAt = linearLayout.getChildAt(i2);
            Intrinsics.checkNotNullExpressionValue(childAt, "linearLayout.getChildAt(i)");
            childAt.setOnClickListener(v -> {
                Intrinsics.checkNotNullParameter(context2, "this$0");
                onClickListener.onClick(v.getId(), popupView);
                popupWindow.dismiss();
            });
            i2 = i3;
        }
    }


    public void show() {
        this.popupWindow.showAtLocation(this.popupView, 17, 0, 0);
    }

    public void show(View view) {
        this.popupWindow.showAsDropDown(view, view.getWidth() - this.popupWindow.getWidth(), 0);
    }

    public void showRight(View view) {
        this.popupWindow.showAsDropDown(view, view.getWidth() - this.popupWindow.getWidth(), 0, 53);
    }

}
