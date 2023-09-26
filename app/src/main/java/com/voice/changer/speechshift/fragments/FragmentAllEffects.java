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

public final class FragmentAllEffects extends BaseFragment<ChangeEffectViewModel, FragmentAllEffectsBinding> {
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctx, Intent intent) {
            EffectModel effectModel;
            ItemEffectAdapter access$getItemEffectAdapter$p;
            if (Intrinsics.areEqual(intent == null ? null : intent.getAction(), "select_effect") && (effectModel = intent.getParcelableExtra("effect_model")) != null && (access$getItemEffectAdapter$p = itemEffectAdapter) != null) {
                access$getItemEffectAdapter$p.selectEffectItem(effectModel);
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
        Context context = requireContext();
        List<EffectModel> effects = getViewModel().getAllVoiceEffects(context);
        RecyclerView rclEffect = getDataBinding().rclEffect;
        rclEffect.setHasFixedSize(true);
        Context requireContext2 = requireContext();
        ItemEffectAdapter itemEffectAdapter2 = new ItemEffectAdapter(requireContext2, effects, effectModel -> {
            Intrinsics.checkNotNullParameter(effectModel, "it");
            ItemEffectAdapter itemEffectAdapter1 = itemEffectAdapter;
            if (itemEffectAdapter1 != null) {
                itemEffectAdapter1.selectEffectItem(effectModel);
            }
            Context context1 = getContext();
            Intrinsics.checkNotNullExpressionValue(context1, "context");
            String nameOrigin = effectModel.getNameOrigin();
            Intrinsics.checkNotNull(nameOrigin);
            ((ChangeEffectActivity) requireActivity()).playEffect(effectModel.getId());
            ChangeEffectActivity.Companion.setEffectModelSelected(effectModel);
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(new Intent("select_effect").putExtra("effect_model", effectModel));

            return null;
        });
        this.itemEffectAdapter = itemEffectAdapter2;
        rclEffect.setAdapter(itemEffectAdapter2);
    }

    public void onResume() {
        ItemEffectAdapter itemEffectAdapter2;
        super.onResume();
        EffectModel effectModelSelected = ChangeEffectActivity.Companion.getEffectModelSelected();
        if (effectModelSelected != null && (itemEffectAdapter2 = this.itemEffectAdapter) != null) {
            itemEffectAdapter2.selectEffectItem(effectModelSelected);
        }
    }
}
