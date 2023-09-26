package com.voice.changer.speechshift.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import androidx.recyclerview.widget.RecyclerView;

import com.voice.changer.speechshift.Comparisons;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseAdapter;
import com.voice.changer.speechshift.databinding.ItemAudioAdapterBinding;
import com.voice.changer.speechshift.getApiData.allModel.AudioModel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class AudioAdapter extends BaseAdapter<ItemAudioAdapterBinding, AudioModel> {
    private final List<AudioModel> audioModelList;
    private final Function1 unitFunction1;

    public int getLayoutRes() {
        return R.layout.item_audio_adapter;
    }

    public final Function1 getUnitFunction1() {
        return this.unitFunction1;
    }


    public AudioAdapter(Context context2, List<AudioModel> list, Function1<? super AudioModel, Unit> function1) {
        super(list);
        this.audioModelList = list;
        this.unitFunction1 = function1;
    }

    public RecyclerView.ViewHolder createVH(ItemAudioAdapterBinding itemAudioBinding) {
        return new AudioViewHolder(this, itemAudioBinding);
    }

    public final class AudioViewHolder extends BaseAdapter<ItemAudioAdapterBinding, AudioModel>.BaseVH<Object> {
        final AudioAdapter audioAdapter;
        int[] androidColors;

        public AudioViewHolder(AudioAdapter audioAdapter, ItemAudioAdapterBinding itemAudioBinding) {
            super(audioAdapter, itemAudioBinding);
            this.audioAdapter = audioAdapter;
            androidColors = itemView.getResources().getIntArray(R.array.androidcolors);
        }

        public void onItemClickListener(AudioModel audioModel) {
            super.onItemClickListener(audioModel);
            this.audioAdapter.getUnitFunction1().invoke(audioModel);

        }

        @SuppressLint("SetTextI18n")
        public void bind(AudioModel audioModel) {
            super.bind(audioModel);
            getBinding().setAudioMode(audioModel);

            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

            GradientDrawable draw = new GradientDrawable();
            draw.setShape(GradientDrawable.OVAL);
            draw.setColor(randomAndroidColor);
            getBinding().ivAvt.setBackground(draw);

            getBinding().tvName.setText(audioModel.getFileName() + '.' + audioModel.getType());
            getBinding().tvDetail.setText(convertLongDataToDuration(Long.parseLong(audioModel.getDuration())) + " | " + convertSize(new File(audioModel.getPath()).length()));
        }
    }

    public String convertLongDataToDuration(long longTime) {
        String strFirst;
        String strSec;

        if (longTime > 3600000) {
            int i = (int) (longTime / 3600000);
            if (i < 10) {
                strFirst = "0" + i + ":";
            } else {
                strFirst = i + ":";
            }
        } else {
            strFirst = "";
        }
        int i2 = ((int) (longTime / 60000)) % 60;
        if (i2 < 10) {
            strSec = strFirst + "0" + i2 + ":";
        } else {
            strSec = strFirst + i2 + ":";
        }
        int i3 = ((int) (longTime / 1000)) % 60;
        if (i3 < 10) {
            return strSec + "0" + i3;
        }
        return strSec + i3;
    }

    public String convertSize(long j) {
        double d = j / 1024.0d;
        if (d >= 1048576.0d) {
            double d2 = 1024;
            return new DecimalFormat("0.00").format((d / d2) / d2) + " GB";
        } else if (d >= 1024.0d) {
            return new DecimalFormat("0.00").format(d / 1024) + " MB";
        } else {
            return new DecimalFormat("0").format(d) + " KB";
        }
    }

    public void sortListRefresh(List<AudioModel> models) {
        this.audioModelList.clear();
        if (models.size() > 1) {
            CollectionsKt.sortWith(models, (o1, o2) -> ComparisonsKt.compareValues(Long.valueOf(((AudioModel) o2).getDateCreate()), Long.valueOf(((AudioModel) o1).getDateCreate())));
        }
        this.audioModelList.addAll(models);
        notifyDataSetChanged();
    }

    public final void oldDataSort(List<AudioModel> audioModels) {
        Intrinsics.checkNotNullParameter(audioModels, "newList");
        this.audioModelList.clear();
        if (audioModels.size() > 1) {
            CollectionsKt.sortWith(audioModels, (o1, o2) -> ComparisonsKt.compareValues(((AudioModel) o1).getDateCreate(), ((AudioModel) o2).getDateCreate()));
        }
        this.audioModelList.addAll(audioModels);
        notifyDataSetChanged();
    }

    public final void sortByFileName(List<AudioModel> audioModelList) {
        this.audioModelList.clear();
        if (audioModelList.size() > 1) {
            CollectionsKt.sortWith(audioModelList, new Comparisons());
        }
        this.audioModelList.addAll(audioModelList);
        notifyDataSetChanged();
    }

    public final void sortOldByName(List<AudioModel> audioModels) {
        Intrinsics.checkNotNullParameter(audioModels, "newList");
        this.audioModelList.clear();
        if (audioModels.size() > 1) {
            CollectionsKt.sortWith(audioModels, (o1, o2) -> {
                String s = o1.getFileName().toLowerCase();
                String lowerCase2 = o2.getFileName().toLowerCase();
                return ComparisonsKt.compareValues(s, lowerCase2);
            });
        }
        this.audioModelList.addAll(audioModels);
        notifyDataSetChanged();
    }
}
