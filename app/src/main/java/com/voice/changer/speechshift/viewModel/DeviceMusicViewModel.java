package com.voice.changer.speechshift.viewModel;

import static com.voice.changer.speechshift.activity.OpenFileActivity.imgSort;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.lifecycle.MutableLiveData;

import com.airbnb.lottie.LottieAnimationView;
import com.voice.changer.speechshift.FilenameUtils;
import com.voice.changer.speechshift.allBaseAct.BaseViewModel;
import com.voice.changer.speechshift.getApiData.allModel.AudioModel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

public final class DeviceMusicViewModel extends BaseViewModel {

    public static List<AudioModel> audioModelList = new ArrayList<>();
    private final MutableLiveData<List<AudioModel>> mutableLiveData = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<List<AudioModel>> getMutableLiveData() {
        return this.mutableLiveData;
    }

    public void getAudioDataClass(Context context, LinearLayout linearLayout, FrameLayout ads, LottieAnimationView noData) {
        new GetAudioData(context, linearLayout, ads, noData).execute();
    }

    private class GetAudioData extends AsyncTask<String, String, String[]> {
        Context ctx;
        LinearLayout lly;
        LottieAnimationView noData;
        FrameLayout adsLayout;

        public GetAudioData(Context ctx, LinearLayout lly, FrameLayout ads, LottieAnimationView noData) {
            this.ctx = ctx;
            this.lly = lly;
            adsLayout = ads;
            this.noData = noData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            lly.setVisibility(View.VISIBLE);
            audioModelList.clear();
        }

        @Override
        protected String[] doInBackground(String... strings) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                audioModelList.addAll(loadAudio(ctx));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            Intrinsics.checkNotNullParameter(strings, "it");
            lly.setVisibility(View.GONE);
            if (audioModelList.size() == 0) {
                adsLayout.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            } else {
                noData.setVisibility(View.GONE);
                if (audioModelList.size() > 1) {
                    imgSort.setVisibility(View.VISIBLE);
                    adsLayout.setVisibility(View.VISIBLE);
                } else {
                    imgSort.setVisibility(View.INVISIBLE);
                    adsLayout.setVisibility(View.GONE);
                }
            }
            getMutableLiveData().setValue(audioModelList);
        }
    }

    // Working Method

    @SuppressLint("Range")
    private ArrayList<AudioModel> loadAudio(Context ctx) {
        ArrayList<AudioModel> arrayList = new ArrayList<>();
        ContentResolver contentResolver = ctx.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {"_data", "duration", "date_added"};
        String selection = "_data like ? OR _data like ? ";
        String[] selectionArgs = {"%.mp3%", "%.wav%"};
        String sortOrder = Intrinsics.stringPlus("datetaken", " DESC");

        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                long lastModified = Long.parseLong(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED)));

                int size = Integer.parseInt(convertSize(new File(path).length()));

                if (size >= 20) {
                    if (duration == null) {
                        Uri uriPath = Uri.parse(path);
                        duration = getBaseDuration(ctx, uriPath);
                    }

                    File ff = new File(path);
                    String removeExtension = FilenameUtils.removeExtension(ff.getName());
                    String valueOf = String.valueOf(ff.length());
                    String extension = FilenameUtils.getExtension(ff.getName());

                    arrayList.add(new AudioModel(path, removeExtension, duration, lastModified, valueOf, extension));
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }


    public String convertSize(long j) {
        double d = j / 1024.0d;
        return new DecimalFormat("0").format(d);
    }

    public String getBaseDuration(Context mContext, Uri strTime) {

        int i;
        MediaPlayer create;
        create = MediaPlayer.create(mContext, strTime);
        Intrinsics.checkNotNullExpressionValue(create, "create(context, Uri.parse(uriOfFile))");
        i = create.getDuration();
        create.release();

        return String.valueOf(i);
    }
}