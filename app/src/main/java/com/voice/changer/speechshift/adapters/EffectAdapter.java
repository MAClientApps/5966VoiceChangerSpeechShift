package com.voice.changer.speechshift.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.databinding.ItemTypeEffectAdapterBinding;
import com.voice.changer.speechshift.getApiData.allModel.TypeEffectModel;
import com.voice.changer.speechshift.allBaseAct.BaseAdapter;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public final class EffectAdapter extends BaseAdapter<ItemTypeEffectAdapterBinding, TypeEffectModel> {
    Context mContext;
    List<TypeEffectModel> typeEffectModelList;
    private Function2 function2;

    public int getLayoutRes() {
        return R.layout.item_type_effect_adapter;
    }


    public Function2 getFunction2() {
        return this.function2;
    }

    public EffectAdapter(Context context2, List<TypeEffectModel> list, Function2<? super TypeEffectModel, ? super Integer, Unit> function2) {
        super(list);
        this.mContext = context2;
        this.typeEffectModelList = list;
        this.function2 = function2;
    }

    public RecyclerView.ViewHolder createVH(ItemTypeEffectAdapterBinding itemTypeEffectBinding) {
        Intrinsics.checkNotNullParameter(itemTypeEffectBinding, "binding");
        return new TypeEffectViewHolder(this, itemTypeEffectBinding);
    }

    public final class TypeEffectViewHolder extends BaseAdapter<ItemTypeEffectAdapterBinding, TypeEffectModel>.BaseVH<Object> {
        final EffectAdapter effectAdapter;

        public TypeEffectViewHolder(EffectAdapter effectAdapter, ItemTypeEffectAdapterBinding itemTypeEffectBinding) {
            super(effectAdapter, itemTypeEffectBinding);
            Intrinsics.checkNotNullParameter(effectAdapter, "this$0");
            Intrinsics.checkNotNullParameter(itemTypeEffectBinding, "binding");
            this.effectAdapter = effectAdapter;
        }

        public void onItemClickListener(TypeEffectModel typeEffectModel) {
            Intrinsics.checkNotNullParameter(typeEffectModel, "data");
            super.onItemClickListener(typeEffectModel);
            this.effectAdapter.selectItem(typeEffectModel);
            this.effectAdapter.getFunction2().invoke(typeEffectModel, Integer.valueOf(getPosition()));
        }

        public void bind(TypeEffectModel typeEffectModel) {
            Intrinsics.checkNotNullParameter(typeEffectModel, "data");
            super.bind(typeEffectModel);
            (getBinding()).txtEffectType.setText(typeEffectModel.getType());
            if (typeEffectModel.isActive()) {
                (getBinding()).txtEffectType.setTextColor(mContext.getColor(R.color.black));
                return;
            }
            (getBinding()).txtEffectType.setTextColor(mContext.getColor(R.color.grayText));
        }
    }

    public void selectItem(TypeEffectModel typeEffectModel) {
        try {
            for (TypeEffectModel next : this.typeEffectModelList) {
                next.setActive(Intrinsics.areEqual((Object) next, (Object) typeEffectModel));
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void selItemPosition(int pos) {
        try {
            TypeEffectModel typeEffectModel = this.typeEffectModelList.get(pos);
            for (TypeEffectModel nextPos : this.typeEffectModelList) {
                nextPos.setActive(Intrinsics.areEqual((Object) nextPos, (Object) typeEffectModel));
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
