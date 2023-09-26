package com.voice.changer.speechshift.adapters;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseAdapter;
import com.voice.changer.speechshift.databinding.ItemEffectAdapterBinding;
import com.voice.changer.speechshift.getApiData.allModel.EffectModel;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class ItemEffectAdapter extends BaseAdapter<ItemEffectAdapterBinding, EffectModel> {
    Context mCtx;
    List<EffectModel> effectModels;
    Function1 onClickItem;

    public int getLayoutRes() {
        return R.layout.item_effect_adapter;
    }

    public final Function1 getOnClickItem() {
        return this.onClickItem;
    }

    public ItemEffectAdapter(Context context2, List<EffectModel> list, Function1<? super EffectModel, Unit> function1) {
        super(list);
        this.mCtx = context2;
        this.effectModels = list;
        this.onClickItem = function1;
    }

    public RecyclerView.ViewHolder createVH(ItemEffectAdapterBinding itemEffectBinding) {
        Intrinsics.checkNotNullParameter(itemEffectBinding, "binding");
        return new EffectViewHolder(this, itemEffectBinding);
    }

    public final class EffectViewHolder extends BaseAdapter<ItemEffectAdapterBinding, EffectModel>.BaseVH<Object> {
        final ItemEffectAdapter effectAdapter;

        public EffectViewHolder(ItemEffectAdapter itemEffectAdapter, ItemEffectAdapterBinding itemEffectBinding) {
            super(itemEffectAdapter, itemEffectBinding);
            this.effectAdapter = itemEffectAdapter;
        }

        public void onItemClickListener(EffectModel effectModel) {
            super.onItemClickListener(effectModel);
            getOnClickItem().invoke(effectModel);
        }

        public void bind(EffectModel effectModel) {
            super.bind(effectModel);
            //setModel
            getBinding().tvEffect.setSelected(true);
            getBinding().tvEffect.setText(effectModel.getName());
            getBinding().setEffectModel(effectModel);
            if (effectModel.isActive()) {
                getBinding().tvEffect.setTextColor(mCtx.getResources().getColor(R.color.colorOrange, null));
                Glide.with(mCtx).load(effectModel.getIconUnSelected()).into(getBinding().ivAvt);
                getBinding().ivAvtSelected.setVisibility(View.VISIBLE);
            } else {
                getBinding().tvEffect.setTextColor(mCtx.getResources().getColor(R.color.grayText, null));
                Glide.with(mCtx).load(effectModel.getIconSelected()).into(getBinding().ivAvt);
                getBinding().ivAvtSelected.setVisibility(View.GONE);
            }

        }
    }

    public void selectEffectItem(EffectModel effectModel) {
        for (EffectModel next : this.effectModels) {
            next.setActive(Intrinsics.areEqual((Object) next.getName(), (Object) effectModel.getName()));
        }
        notifyDataSetChanged();
    }
}
