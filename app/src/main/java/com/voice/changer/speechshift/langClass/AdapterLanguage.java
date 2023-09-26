package com.voice.changer.speechshift.langClass;

import static com.voice.changer.speechshift.activity.LanguageActivity.strLangCode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.voice.changer.speechshift.R;

import java.util.List;

public class AdapterLanguage extends RecyclerView.Adapter<AdapterLanguage.LangViewHolder> {
    private final Context mCtx;
    private List<LanguageModel> langArrayList;
    private final onClickLangItem mOnClickLangItem;

    public interface onClickLangItem {
        void languageSelected(LanguageModel languageModel);
    }

    public AdapterLanguage(Context context, onClickLangItem onClickLangItem) {
        this.mCtx = context;
        this.mOnClickLangItem = onClickLangItem;
    }

    public void setLang(List<LanguageModel> langList) {
        this.langArrayList = langList;
        notifyDataSetChanged();
    }

    public void setDefaultCheck() {
        this.langArrayList.get(0).setCheck(true);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LangViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_language, viewGroup, false);
        return new LangViewHolder(view);
    }

    public void onBindViewHolder(LangViewHolder langViewHolder, int i) {
        langViewHolder.binData(this.langArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        List<LanguageModel> langList = this.langArrayList;
        if (langList != null) {
            return langList.size();
        }
        return 0;
    }


    public class LangViewHolder extends RecyclerView.ViewHolder {
        TextView langName;
        TextView txtLang;
        ImageView imgSelected;
        ImageView imgFlag;
        View myView;

        public LangViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
            langName = (TextView) itemView.findViewById(R.id.lan_name);
            txtLang = (TextView) itemView.findViewById(R.id.txtLang);
            imgFlag = (ImageView) itemView.findViewById(R.id.imgFlag);
            imgSelected = (ImageView) itemView.findViewById(R.id.imgSelected);
        }

        public void binData(final LanguageModel languageModel) {
            langName.setText(languageModel.getName());
            txtLang.setText(languageModel.getStrLang());

            Glide.with(mCtx).load(languageModel.getFlags()).into(imgFlag);

            if (languageModel.isCheck()) {
                imgSelected.setVisibility(View.VISIBLE);
                strLangCode = languageModel.getCode();
            } else {
                imgSelected.setVisibility(View.GONE);
            }

            myView.setOnClickListener(view -> LangViewHolder.this.click(languageModel, view));
        }

        public void click(LanguageModel languageModel, View view) {
            AdapterLanguage.this.mOnClickLangItem.languageSelected(languageModel);
            languageModel.setCheck(true);
            AdapterLanguage.this.handleLangDisplay(languageModel);
        }
    }

    public void handleLangDisplay(LanguageModel lang) {
        for (int i = 0; i < this.langArrayList.size(); i++) {
            if (!this.langArrayList.get(i).getName().equals(lang.getName())) {
                this.langArrayList.get(i).setCheck(false);
            }
        }
        notifyDataSetChanged();
    }
}
