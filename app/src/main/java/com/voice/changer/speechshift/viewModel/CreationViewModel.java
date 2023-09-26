package com.voice.changer.speechshift.viewModel;

import static com.voice.changer.speechshift.activity.CreationActivity.imgSort;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.lifecycle.MutableLiveData;

import com.airbnb.lottie.LottieAnimationView;
import com.voice.changer.speechshift.FilenameUtils;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.allBaseAct.BaseViewModel;
import com.voice.changer.speechshift.getApiData.allModel.AudioModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

public final class CreationViewModel extends BaseViewModel {
    int label;
    List<AudioModel> arrayList = new ArrayList<>();
    MutableLiveData<List<AudioModel>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<AudioModel>> getListMutableLiveData() {
        return this.listMutableLiveData;
    }

    public void getAudioDataClass(Context context, LinearLayout linearLayout, FrameLayout adsLayout, LottieAnimationView noData) {
        new GetAudioData(context, linearLayout, adsLayout, noData).execute();
    }

    private class GetAudioData extends AsyncTask {
        Context ctx;
        LinearLayout layout;
        FrameLayout ads;
        LottieAnimationView noData;

        public GetAudioData(Context ctx, LinearLayout lly, FrameLayout adsLayout, LottieAnimationView noData) {
            this.ctx = ctx;
            this.ads = adsLayout;
            this.layout = lly;
            this.noData = noData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                layout.setVisibility(View.VISIBLE);
                arrayList.clear();
            }
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (label == 0) {
                try {
                    File[] files = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + ctx.getResources().getString(R.string.app_name) + "/" + "VoiceEffects").listFiles();
                    if (!(files.length == 0)) {
                        int length = files.length;
                        int i = 0;
                        while (i < length) {
                            File file = files[i];
                            int i2 = i + 1;
                            AudioModel audioModel = null;
                            String absolutePath1 = file.getAbsolutePath();
                            String name = FilenameUtils.removeExtension(file.getName());
                            Uri uriPath = Uri.parse(absolutePath1);
                            String baseDuration = getBaseDuration(getContext(), uriPath);
                            long modified = file.lastModified();
                            String of = String.valueOf(file.length());
                            String extensions = FilenameUtils.getExtension(file.getName());
                            arrayList.add(new AudioModel(absolutePath1, name, baseDuration, modified, of, extensions));
                            i = i2;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return Unit.INSTANCE;
            }
            return objects;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            layout.setVisibility(View.GONE);
            if (arrayList.size() == 0) {
                ads.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            } else {
                noData.setVisibility(View.GONE);
                if (arrayList.size() > 1) {
                    imgSort.setVisibility(View.VISIBLE);
                    ads.setVisibility(View.VISIBLE);
                } else {
                    imgSort.setVisibility(View.GONE);
                    ads.setVisibility(View.GONE);
                }
            }

            getListMutableLiveData().setValue(arrayList);
        }
    }

    public String getBaseDuration(Context mContext, Uri strTime) {
        String ii;
        MediaPlayer mediaPlayer = MediaPlayer.create(mContext, strTime);
        ii = String.valueOf(mediaPlayer.getDuration());
        mediaPlayer.release();

        return ii;
    }

}
