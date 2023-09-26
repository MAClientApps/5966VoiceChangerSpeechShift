package com.voice.changer.speechshift.allBaseAct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.getApiData.InterfaceDataManager;
import com.voice.changer.speechshift.getApiData.appScheduler.SchedularProvider;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends Fragment implements IFragmentCallback {
    public Activity activity;
    public DB dataBinding;
    @Inject
    public InterfaceDataManager interfaceDataManager;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    public ViewModelFactory factory;
    private final Lazy mProgressDialog$delegate = LazyKt.lazy(new Function0<Object>() {
        @Override
        public Object invoke() {
            Activity activity1 = activity;
            if (activity1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
                activity1 = null;
            }
            return new BaseFragment.ProgressDialog(activity1);
        }
    });
    private Navigators navigators;
    @Inject
    public SchedularProvider schedule;
    public VM viewModel;

    public abstract void bindViewModels();

    public abstract Class<VM> createViewModel();

    public abstract int getFragResourceLayout();

    public abstract void bindViews();

    public final void setDataManager(InterfaceDataManager interfaceDataManager2) {
        Intrinsics.checkNotNullParameter(interfaceDataManager2, "<set-?>");
        this.interfaceDataManager = interfaceDataManager2;
    }

    public final ViewModelFactory getFactory() {
        ViewModelFactory viewModelFactory = this.factory;
        if (viewModelFactory != null) {
            return viewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("factory");
        return null;
    }

    public final void setFactory(ViewModelFactory viewModelFactory) {
        Intrinsics.checkNotNullParameter(viewModelFactory, "<set-?>");
        this.factory = viewModelFactory;
    }

    public final void setSchedule(SchedularProvider schedularProvider) {
        Intrinsics.checkNotNullParameter(schedularProvider, "<set-?>");
        this.schedule = schedularProvider;
    }

    public final VM getViewModel() {
        VM vm = this.viewModel;
        if (vm != null) {
            return vm;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(VM vm) {
        Intrinsics.checkNotNullParameter(vm, "<set-?>");
        this.viewModel = vm;
    }

    public final DB getDataBinding() {
        DB db = this.dataBinding;
        if (db != null) {
            return db;
        }
        return null;
    }

    public final void setDataBinding(DB db) {
        Intrinsics.checkNotNullParameter(db, "<set-?>");
        this.dataBinding = db;
    }

    private final ProgressDialog getMProgressDialog() {
        return (ProgressDialog) this.mProgressDialog$delegate.getValue();
    }

    public final void inject(ViewModelFactory viewModelFactory, SchedularProvider schedularProvider, InterfaceDataManager interfaceDataManager2) {
        Intrinsics.checkNotNullParameter(viewModelFactory, "factory");
        Intrinsics.checkNotNullParameter(schedularProvider, "schedule");
        Intrinsics.checkNotNullParameter(interfaceDataManager2, "dataManager");
        setFactory(viewModelFactory);
        setSchedule(schedularProvider);
        setDataManager(interfaceDataManager2);
    }

    public Context getContext() {
        return this.activity;
    }

    public void onAttach(@NonNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof Navigators) {
            Navigators navigators2 = (Navigators) context;
            navigators2.fragmentRequestInject(this);
            this.activity = (Activity) context;
            this.navigators = navigators2;
            ViewModel viewModel2 = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) getFactory()).get(createViewModel());
            Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProvider(this, …y).get(createViewModel())");
            setViewModel((VM) viewModel2);
            getViewModel().setFragmentCallback(this);
            BaseViewModel viewModel3 = getViewModel();
            Navigators navigators3 = this.navigators;
            if (navigators3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigators");
                navigators3 = null;
            }
            viewModel3.setNavigation(navigators3);
        }
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return onCreateViewInternal(layoutInflater, viewGroup);
    }

    public void onViewCreated(@NonNull View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setupUI(view);
        Navigators navigators2 = this.navigators;
        if (navigators2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigators");
            navigators2 = null;
        }
        navigators2.onFragmentResumed(this);
        bindViews();
        bindViewModels();
    }

    @SuppressLint("ClickableViewAccessibility")
    private final void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                Intrinsics.checkNotNullParameter(activity, "this$0");
                hideSoftKeyboard();
                return false;
            });
        }
        if (view instanceof ViewGroup) {
            int i = 0;
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            while (i < childCount) {
                int i2 = i + 1;
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "innerView");
                setupUI(childAt);
                i = i2;
            }
        }
    }

    private final void hideSoftKeyboard() {
        Activity activity2 = this.activity;
        Activity activity3 = null;
        if (activity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity2 = null;
        }
        View currentFocus = activity2.getCurrentFocus();
        if (currentFocus != null) {
            Activity activity4 = this.activity;
            if (activity4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
            } else {
                activity3 = activity4;
            }
            Object systemService = activity3.getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private final View onCreateViewInternal(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (getFragResourceLayout() <= 0) {
            return null;
        }
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, getFragResourceLayout(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        setDataBinding((DB) inflate);
        getDataBinding().setVariable(2, getViewModel());
        return getDataBinding().getRoot();
    }

    public void showLoading() {
        if (!getMProgressDialog().isShowing() && getUserVisibleHint()) {
            getMProgressDialog().show();
        }
    }

    public void hideLoading() {
        if (getMProgressDialog().isShowing() && !isDetached()) {
            getMProgressDialog().dismiss();
        }
    }

    public void showActivity(Class<?> cls, Bundle bundle) {
        Intrinsics.checkNotNullParameter(cls, "act");
        Activity activity2 = this.activity;
        if (activity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity2 = null;
        }
        Intent intent = new Intent(activity2, cls);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public static final class ProgressDialog extends Dialog {
        @SuppressLint("ResourceType")
        public ProgressDialog(Context context) {
            super(context);
            Window window;
            Intrinsics.checkNotNullParameter(context, "context");
            setCancelable(false);
            requestWindowFeature(1);
            setContentView(R.layout.layout_progress_circle);
            if (getWindow() != null && (window = getWindow()) != null) {
                window.setBackgroundDrawableResource(17170445);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        getViewModel().onDestroyView();
        this.disposable.clear();
    }

    public void onDetach() {
        super.onDetach();
    }
}
