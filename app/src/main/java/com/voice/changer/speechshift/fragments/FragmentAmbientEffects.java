package com.voice.changer.speechshift.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.activity.ChangeEffectActivity;
import com.voice.changer.speechshift.adapters.ItemEffectAdapter;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.databinding.FragmentAllEffectsBinding;
import com.voice.changer.speechshift.getApiData.allModel.EffectModel;
import com.voice.changer.speechshift.viewModel.ChangeEffectViewModel;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public final class FragmentAmbientEffects extends BaseFragment<ChangeEffectViewModel, FragmentAllEffectsBinding> {
    final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctx, Intent dataIntent) {
            EffectModel model;
            ItemEffectAdapter itemEffectAdapter1;
            if (Intrinsics.areEqual(dataIntent == null ? null : dataIntent.getAction(), "select_effect") && (model = dataIntent.getParcelableExtra("effect_model")) != null && (itemEffectAdapter1 = itemEffectAdapter) != null) {
                itemEffectAdapter1.selectEffectItem(model);
            }
        }
    };

    public ItemEffectAdapter itemEffectAdapter;

    public void bindViewModels() {
    }

    public Class<ChangeEffectViewModel> createViewModel() {
        return ChangeEffectViewModel.class;
    }

    public int getFragResourceLayout() {
        return R.layout.fragment_all_effects;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void bindViews() {
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.receiver, new IntentFilter("select_effect"));
        List<EffectModel> scaryEffects = getViewModel().getAllAmbientEffect(requireContext());
        RecyclerView recyclerView = getDataBinding().rclEffect;
        recyclerView.setHasFixedSize(true);
        Context requireContext2 = requireContext();
        ItemEffectAdapter itemEffectAdapter2 = new ItemEffectAdapter(requireContext2, scaryEffects, effectModel -> {
            ItemEffectAdapter effectAdapter = itemEffectAdapter;
            if (effectAdapter != null) {
                effectAdapter.selectEffectItem(effectModel);
            }
            ((ChangeEffectActivity) requireActivity()).playEffect(effectModel.getId());
            ChangeEffectActivity.Companion.setEffectModelSelected(effectModel);
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(new Intent("select_effect").putExtra("effect_model", effectModel));

            return null;
        });
        this.itemEffectAdapter = itemEffectAdapter2;
        recyclerView.setAdapter(itemEffectAdapter2);
    }

    public void onResume() {
        ItemEffectAdapter itemEffectAdapter1;
        super.onResume();
        EffectModel effectModelSelected = ChangeEffectActivity.Companion.getEffectModelSelected();
        if (effectModelSelected != null && (itemEffectAdapter1 = this.itemEffectAdapter) != null) {
            itemEffectAdapter1.selectEffectItem(effectModelSelected);
        }
    }
}
