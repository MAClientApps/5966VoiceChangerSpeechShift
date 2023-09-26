package com.voice.changer.speechshift.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.ViewPager;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.adapters.DeviceVideoPager;
import com.voice.changer.speechshift.allBaseAct.BaseActivity;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.allBaseAct.BasePopupMenu;
import com.voice.changer.speechshift.custUi.constatnt.TapClick;
import com.voice.changer.speechshift.databinding.ActivityOpenFileBinding;
import com.voice.changer.speechshift.myAdsClasses.ApplovinInterAds;
import com.voice.changer.speechshift.viewModel.OpenFileViewModel;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public final class OpenFileActivity extends BaseActivity<OpenFileViewModel, ActivityOpenFileBinding> {
    public static final Companion Companion = new Companion(null);
    public static MutableLiveData<Integer> sortingByCreateDate = new MutableLiveData<>(1);
    public static MutableLiveData<Integer> sortingByName = new MutableLiveData<>(0);
    private ImageView ivCreated;
    private ImageView ivFileName;

    public static ImageView imgSort;

    public Class<OpenFileViewModel> createViewModel() {
        return OpenFileViewModel.class;
    }

    public int getContent() {
        return R.layout.activity_open_file;
    }

    public void navigate(int i, Bundle bundle, boolean z) {
    }

    public void navigateUp() {
    }

    public void onFragmentResumed(BaseFragment<?, ?> baseFragment) {
        Intrinsics.checkNotNullParameter(baseFragment, "fragment");
    }

    public void switchFragment(KClass<?> kClass, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(kClass, "fragment");
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<Integer> getLiveSortCreateAudio() {
            return OpenFileActivity.sortingByCreateDate;
        }

        public MutableLiveData<Integer> getLiveSortNameAudio() {
            return OpenFileActivity.sortingByName;
        }
    }

    public final ImageView getIvCreated() {
        return this.ivCreated;
    }

    public final void setIvCreated(ImageView imageView) {
        this.ivCreated = imageView;
    }

    public final ImageView getIvFileName() {
        return this.ivFileName;
    }

    public final void setIvFileName(ImageView imageView) {
        this.ivFileName = imageView;
    }

    public void mainView() {
        boolean z = false;
        ApplovinInterAds.getInstance().loadInterstitialAd(OpenFileActivity.this);
        ApplovinInterAds.getInstance().showInterstitialAd();

        SharedPreferences sharedPreferences = getSharedPreferences("MY_PRE", 0);
        imgSort = getBindingData().toolbar.ivSort;
        getBindingData().toolbar.tvTitle.setText(getString(R.string.change_voice));
    }

    public void initViews() {
        TapClick.tap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            onBackPressed();
            return null;
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeviceVideoPager deviceVideoPager = new DeviceVideoPager(fragmentManager);
        ViewPager viewPager = getBindingData().viewPager;
        viewPager.setAdapter(deviceVideoPager);

        BasePopupMenu basePopupMenus = new BasePopupMenu(this, R.layout.layout_popup_menu_sort, new BasePopupMenu.PopupMenuCustomOnClickListener() {
            @Override
            public void initView(View view) {
                setIvCreated(view.findViewById(R.id.iv_created));
                setIvFileName(view.findViewById(R.id.iv_file_name));
            }

            @Override
            public void onClick(int i, View view) {
                Integer int1;
                Integer int2;
                if (i == R.id.ll_created) {
                    OpenFileActivity.Companion.getLiveSortNameAudio().postValue(0);
                    Integer value5 = OpenFileActivity.Companion.getLiveSortCreateAudio().getValue();
                    if ((value5 != null && value5.intValue() == 1) || ((int1 = OpenFileActivity.Companion.getLiveSortCreateAudio().getValue()) != null && int1.intValue() == 0)) {
                        OpenFileActivity.Companion.getLiveSortCreateAudio().postValue(2);
                        ImageView ivCreated = getIvCreated();
                        if (ivCreated != null) {
                            ivCreated.setImageResource(R.drawable.ic_menu_down);
                            return;
                        }
                        return;
                    }
                    OpenFileActivity.Companion.getLiveSortCreateAudio().postValue(1);
                    ImageView ivCreated2 = getIvCreated();
                    if (ivCreated2 != null) {
                        ivCreated2.setImageResource(R.drawable.ic_menu_up);
                    }
                } else if (i == R.id.ll_file_name) {
                    OpenFileActivity.Companion.getLiveSortCreateAudio().postValue(0);
                    Integer value7 = OpenFileActivity.Companion.getLiveSortNameAudio().getValue();
                    if ((value7 != null && value7.intValue() == 1) || ((int2 = OpenFileActivity.Companion.getLiveSortNameAudio().getValue()) != null && int2.intValue() == 0)) {
                        OpenFileActivity.Companion.getLiveSortNameAudio().postValue(2);
                        ImageView ivFileName = getIvFileName();
                        if (ivFileName != null) {
                            ivFileName.setImageResource(R.drawable.ic_menu_down);
                            return;
                        }
                        return;
                    }
                    OpenFileActivity.Companion.getLiveSortNameAudio().postValue(1);
                    ImageView ivFileName2 = getIvFileName();
                    if (ivFileName2 != null) {
                        ivFileName2.setImageResource(R.drawable.ic_menu_up);
                    }
                }
            }
        });
        TapClick.tap(getBindingData().toolbar.ivSort, (Function1<View, Unit>) view -> {
            BasePopupMenu basePopupMenu = basePopupMenus;
            ImageView imageView = getBindingData().toolbar.ivSort;
            Intrinsics.checkNotNullExpressionValue(imageView, "mDataBinding.toolbar.iv_sort");
            basePopupMenu.showRight(imageView);
            return null;
        });
    }

    public void onDestroy() {
        super.onDestroy();
        sortingByCreateDate.postValue(1);
        sortingByName.postValue(0);
    }

    public void onBackPressed() {
        ApplovinInterAds.getInstance().showInterstitialAd();
        finish();
    }
}
