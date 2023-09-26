package com.voice.changer.speechshift.viewModel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;

import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseViewModel;
import com.voice.changer.speechshift.custUi.constatnt.AppDataException;
import com.voice.changer.speechshift.getApiData.allModel.EffectModel;
import com.voice.changer.speechshift.getApiData.allModel.TypeEffectModel;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;

public final class ChangeEffectViewModel extends BaseViewModel {
    List<EffectModel> effectList = new ArrayList<>();
    List<TypeEffectModel> effectTypeList = new ArrayList<>();
    MutableLiveData<List<TypeEffectModel>> liveType = new MutableLiveData<>();
    private int label;

    public List<TypeEffectModel> getEffectTypeList() {
        return this.effectTypeList;
    }

    public MutableLiveData<List<TypeEffectModel>> getLiveType() {
        return this.liveType;
    }

    public List<EffectModel> getEffectList() {
        return this.effectList;
    }

    public void getTypeEffects(Context ctx) {
        AppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) ctx), o -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                getEffectTypeList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, o -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                List<TypeEffectModel> listType = getEffectTypeList();
                String string = ctx.getString(R.string.all_effects);
                listType.add(new TypeEffectModel(string, true));

                List<TypeEffectModel> listType2 = getEffectTypeList();
                String string2 = ctx.getString(R.string.scary_effects);

                return Boxing.boxBoolean(listType2.add(new TypeEffectModel(string2, false)));

            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, o -> {
            getLiveType().setValue(getEffectTypeList());
            return Unit.INSTANCE;
        });
    }

    public List<EffectModel> getAllVoiceEffects(Context ctx) {
        AppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) ctx), o -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, o -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {

                List<EffectModel> effectModels = getEffectList();
                String s = ctx.getString(R.string.normal);
                effectModels.add(new EffectModel(0, s, "normal", R.drawable.ic_effect_normal, R.drawable.ic_effect_normal, 0, true));

                List<EffectModel> effectModels2 = getEffectList();
                String s2 = ctx.getString(R.string.alien);
                effectModels2.add(new EffectModel(1, s2, "alien", R.drawable.ic_effect_alien, R.drawable.ic_effect_alien, 0, false));

                List<EffectModel> effectModels3 = getEffectList();
                String s3 = ctx.getString(R.string.robot);
                effectModels3.add(new EffectModel(2, s3, "robot", R.drawable.ic_effect_roboto, R.drawable.ic_effect_roboto, 0, false));

                List<EffectModel> effectModels4 = getEffectList();
                String s4 = ctx.getString(R.string.ghost);
                effectModels4.add(new EffectModel(3, s4, "ghost", R.drawable.ic_effect_ghost, R.drawable.ic_effect_ghost, 0, false));

                List<EffectModel> effectModels5 = getEffectList();
                String s5 = ctx.getString(R.string.squirrel);
                effectModels5.add(new EffectModel(4, s5, "squirrel", R.drawable.ic_effect_squirrel, R.drawable.ic_effect_squirrel, 0, false));

                List<EffectModel> effectModels6 = getEffectList();
                String s6 = ctx.getString(R.string.cat);
                effectModels6.add(new EffectModel(5, s6, "cat", R.drawable.ic_effect_cat, R.drawable.ic_effect_cat, 0, false));

                List<EffectModel> effectModels7 = getEffectList();
                String s7 = ctx.getString(R.string.oldman);
                effectModels7.add(new EffectModel(6, s7, "oldman", R.drawable.ic_effect_oldman, R.drawable.ic_effect_oldman, 0, false));

                List<EffectModel> effectModels8 = getEffectList();
                String s8 = ctx.getString(R.string.child);
                effectModels8.add(new EffectModel(7, s8, "child", R.drawable.ic_effect_child, R.drawable.ic_effect_child, 0, false));

                List<EffectModel> effectModels9 = getEffectList();
                String s9 = ctx.getString(R.string.women);
                effectModels9.add(new EffectModel(8, s9, "women", R.drawable.ic_effect_women, R.drawable.ic_effect_women, 0, false));

                List<EffectModel> effectModels10 = getEffectList();
                String s10 = ctx.getString(R.string.helium);
                effectModels10.add(new EffectModel(9, s10, "helium", R.drawable.ic_effect_helium, R.drawable.ic_effect_helium, 0, false));

                List<EffectModel> effectModels11 = getEffectList();
                String s11 = ctx.getString(R.string.monster);
                effectModels11.add(new EffectModel(10, s11, "monster", R.drawable.ic_effect_monster, R.drawable.ic_effect_monster, 0, false));

                List<EffectModel> effectModels12 = getEffectList();
                String s12 = ctx.getString(R.string.multiple);
                effectModels12.add(new EffectModel(11, s12, "multiple", R.drawable.ic_effect_multiple, R.drawable.ic_effect_multiple, 0, false));

                List<EffectModel> effectModels13 = getEffectList();
                String s13 = ctx.getString(R.string.drunk);
                effectModels13.add(new EffectModel(12, s13, "drunk", R.drawable.ic_effect_drunk, R.drawable.ic_effect_drunk, 0, false));

                List<EffectModel> effectModels14 = getEffectList();
                String s14 = ctx.getString(R.string.reverse);
                effectModels14.add(new EffectModel(13, s14, "reverse", R.drawable.ic_effect_reverse, R.drawable.ic_effect_reverse, 0, false));

                List<EffectModel> effectModels15 = getEffectList();
                String s15 = ctx.getString(R.string.zombie);
                effectModels15.add(new EffectModel(14, s15, "zombie", R.drawable.ic_effect_zombie, R.drawable.ic_effect_zombie, 0, false));

                List<EffectModel> effectModels16 = getEffectList();
                String s16 = ctx.getString(R.string.nervous);
                effectModels16.add(new EffectModel(15, s16, "nervous", R.drawable.ic_effect_nervous, R.drawable.ic_effect_nervous, 0, false));

                List<EffectModel> effectModels17 = getEffectList();
                String s17 = ctx.getString(R.string.death);
                effectModels17.add(new EffectModel(16, s17, "death", R.drawable.ic_effect_death, R.drawable.ic_effect_death, 0, false));

                List<EffectModel> effectModels18 = getEffectList();
                String s18 = ctx.getString(R.string.underwater);
                effectModels18.add(new EffectModel(17, s18, "underwater", R.drawable.ic_effect_underwater, R.drawable.ic_effect_underwater, 0, false));

                List<EffectModel> effectModels19 = getEffectList();
                String s19 = ctx.getString(R.string.telephone);
                effectModels19.add(new EffectModel(18, s19, "telephone", R.drawable.ic_effect_telephone, R.drawable.ic_effect_telephone, 0, false));

                List<EffectModel> effectModels20 = getEffectList();
                String s20 = ctx.getString(R.string.extraterrestrial);
                effectModels20.add(new EffectModel(19, s20, "extraterrestrial", R.drawable.ic_effect_extraterrestrial, R.drawable.ic_effect_extraterrestrial, 0, false));

                List<EffectModel> effectModels21 = getEffectList();
                String s21 = ctx.getString(R.string.cave);
                effectModels21.add(new EffectModel(20, s21, "cave", R.drawable.ic_effect_cave, R.drawable.ic_effect_cave, 0, false));

                List<EffectModel> effectModels22 = getEffectList();
                String s22 = ctx.getString(R.string.megaphone);
                effectModels22.add(new EffectModel(21, s22, "megaphone", R.drawable.ic_effect_megaphone, R.drawable.ic_effect_megaphone, 0, false));

                List<EffectModel> effectModels23 = getEffectList();
                String s23 = ctx.getString(R.string.villain);
                effectModels23.add(new EffectModel(22, s23, "villain", R.drawable.ic_effect_villain, R.drawable.ic_effect_villain, 0, false));

                List<EffectModel> effectModels24 = getEffectList();
                String s24 = ctx.getString(R.string.back_chipmunks);
                effectModels24.add(new EffectModel(23, s24, "backchipmunk", R.drawable.ic_effect_chimp, R.drawable.ic_effect_chimp, 0, false));

                List<EffectModel> effectModels25 = getEffectList();
                String s25 = ctx.getString(R.string.grand_canyon);
                return Boxing.boxBoolean(effectModels25.add(new EffectModel(24, s25, "grandcanyon", R.drawable.ic_effect_grand_canyon, R.drawable.ic_effect_grand_canyon, 0, false)));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, o -> {
//                invoke(o);
            return Unit.INSTANCE;
        });
        return this.effectList;
    }

    public List<EffectModel> getAllAmbientEffect(Context ctx) {
        AppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) ctx), o -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, o -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                List<EffectModel> effectModels6 = getEffectList();
                String s6 = ctx.getString(R.string.cat);
                effectModels6.add(new EffectModel(5, s6, "cat", R.drawable.ic_effect_cat, R.drawable.ic_effect_cat, 0, false));

                List<EffectModel> effectModels10 = getEffectList();
                String s10 = ctx.getString(R.string.helium);
                effectModels10.add(new EffectModel(9, s10, "helium", R.drawable.ic_effect_helium, R.drawable.ic_effect_helium, 0, false));

                List<EffectModel> effectModels12 = getEffectList();
                String s12 = ctx.getString(R.string.multiple);
                effectModels12.add(new EffectModel(11, s12, "multiple", R.drawable.ic_effect_multiple, R.drawable.ic_effect_multiple, 0, false));

                List<EffectModel> effectModels18 = getEffectList();
                String s18 = ctx.getString(R.string.underwater);
                effectModels18.add(new EffectModel(17, s18, "underwater", R.drawable.ic_effect_underwater, R.drawable.ic_effect_underwater, 0, false));

                List<EffectModel> effectModels20 = getEffectList();
                String s20 = ctx.getString(R.string.extraterrestrial);
                effectModels20.add(new EffectModel(19, s20, "extraterrestrial", R.drawable.ic_effect_extraterrestrial, R.drawable.ic_effect_extraterrestrial, 0, false));

                List<EffectModel> effectModels21 = getEffectList();
                String s21 = ctx.getString(R.string.cave);
                effectModels21.add(new EffectModel(20, s21, "cave", R.drawable.ic_effect_cave, R.drawable.ic_effect_cave, 0, false));

                List<EffectModel> effectModels25 = getEffectList();
                String s25 = ctx.getString(R.string.grand_canyon);
                return Boxing.boxBoolean(effectModels25.add(new EffectModel(24, s25, "grandcanyon", R.drawable.ic_effect_grand_canyon, R.drawable.ic_effect_grand_canyon, 0, false)));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, o -> Unit.INSTANCE);
        return this.effectList;
    }

}
