package com.voice.changer.speechshift.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voice.changer.speechshift.BuildConfig;
import com.voice.changer.speechshift.FilenameUtils;
import com.voice.changer.speechshift.R;
import com.voice.changer.speechshift.activity.CreationActivity;
import com.voice.changer.speechshift.activity.MusicPlayerActivity;
import com.voice.changer.speechshift.adapters.ItemDowAudioAdapter;
import com.voice.changer.speechshift.allBaseAct.BaseFragment;
import com.voice.changer.speechshift.allDialogs.DeleteDialog;
import com.voice.changer.speechshift.allDialogs.RenameDialog;
import com.voice.changer.speechshift.allDialogs.SetRingtoneDialog;
import com.voice.changer.speechshift.custUi.AppConstant;
import com.voice.changer.speechshift.custUi.MobileState;
import com.voice.changer.speechshift.custUi.constatnt.RingtonePermission;
import com.voice.changer.speechshift.databinding.FragmentStudioAudioBinding;
import com.voice.changer.speechshift.getApiData.allModel.AudioModel;
import com.voice.changer.speechshift.viewModel.CreationViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

public final class FragmentAudioStudio extends BaseFragment<CreationViewModel, FragmentStudioAudioBinding> {

    public RelativeLayout llyBanner;
    public FrameLayout ads;


    public ItemDowAudioAdapter audioAdapter;
    public AudioModel audioModel;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<AudioModel> audioModels;

            if (intent.getAction().equals("delete_file")) {
                TypeIntrinsics.asMutableCollection(list).remove(audioModel);
                setAdapter();
                Toast.makeText(getContext(), getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
                ItemDowAudioAdapter adapter = FragmentAudioStudio.this.audioAdapter;
                if (adapter != null && (audioModels = adapter.getModelList()) != null && audioModels.size() == 0) {
                    list.add(new AudioModel("", "Sample.mp3", "", 0, "", ""));
                    ItemDowAudioAdapter audioAdapter1 = FragmentAudioStudio.this.audioAdapter;
                    audioAdapter1.notifyDataSetChanged();
                }
            } else if (intent.getAction().equals("rename_file")) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AudioModel next = (AudioModel) it.next();
                    if (Intrinsics.areEqual(next, next)) {
                        next.setFileName(newFileName);
                        break;
                    }
                }
                setAdapter();
                Toast.makeText(getContext(), getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
            }
        }
    };
    public DeleteDialog deleteDialog;
    public RenameDialog renameDialog;
    public File fromPath;
    public List<AudioModel> list = new ArrayList<>();
    public String newFileName = "";

    public File fileTo;
    public ArrayList<Uri> uris = new ArrayList<>();


    public Class<CreationViewModel> createViewModel() {
        return CreationViewModel.class;
    }

    public int getFragResourceLayout() {
        return R.layout.fragment_studio_audio;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }


    public void bindViews() {

        ads = getDataBinding().ads;
        llyBanner = getDataBinding().llBanner;

        //ApplovinBannerAds.getInstance().showBannerAds(llyBanner, requireActivity());


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("delete_file");
        intentFilter.addAction("rename_file");
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.receiver, intentFilter);
        RecyclerView rclAudio = getDataBinding().rclAudio;
        rclAudio.setHasFixedSize(true);
        Context requireContext = requireContext();
        ItemDowAudioAdapter itemDowAudioAdapter = new ItemDowAudioAdapter(requireContext, new ArrayList<>(), audioModel -> {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.APP_CONSTANT.getKEY_PATH_VOICE(), audioModel.getPath());
            bundle.putString(AppConstant.APP_CONSTANT.getKEY_FILENAME_EFFECT(), audioModel.getFileName());
            showActivity(MusicPlayerActivity.class, bundle);
            return null;
        }, audioModel -> {
            FragmentAudioStudio.this.audioModel = audioModel;
            FragmentActivity requireActivity = requireActivity();
            String fileName = audioModel.getFileName();
            renameDialog = new RenameDialog(requireActivity, true, fileName, (Function2<String, String, Unit>) (str, str2) -> {
                boolean z;
                ItemDowAudioAdapter audioAdapter1 = audioAdapter;
                String str3 = null;
                String newName = str + "_" + str2;
                List<AudioModel> lists = audioAdapter1 == null ? null : audioAdapter1.getModelList();
                Intrinsics.checkNotNull(lists);
                assert lists != null;

                Iterator<AudioModel> it = lists.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual(FilenameUtils.getBaseName(it.next().getFileName()), newName)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }

                if (z) {
                    Toast.makeText(getContext(), requireActivity().getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                    return null;
                }

                AudioModel audioModel1 = audioModel;
                fromPath = new File(audioModel1.getPath());

                File file = new File(audioModel1.getPath());
                File file2 = new File(file.getAbsolutePath());
                File file3 = new File(file.getAbsolutePath().replace(file.getName(), str + "_" + str2 + ".mp3"));

                fileTo = file3;

                CreationActivity.COMPANION.setFrom(fromPath);
                CreationActivity.COMPANION.setTo(fileTo);
                File from1 = fromPath;

                if (Build.VERSION.SDK_INT <= 29) {
                    file2.renameTo(fileTo);
                    Toast.makeText(getContext(), getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
                    setAdapter();
                } else {
                    if (from1 != null) {
                        str3 = from1.getAbsolutePath();
                    }
                    String valueOf = String.valueOf(str3);
                    Context context = getContext();
                    Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Media.getContentUri("external"), getFilePathToMedia(valueOf, context));
                    uris.clear();
                    uris.add(withAppendedId);
                    reqRenameData(uris);
                }

                RenameDialog rename = renameDialog;
                if (rename != null) {
                    rename.dismiss();
                }
                return null;
            });

            RenameDialog renameDialog1 = renameDialog;
            renameDialog1.show();
            return null;
        }, audioModel -> {

            if (RingtonePermission.checkSystemWritePermission(requireContext())) {
                new SetRingtoneDialog(requireActivity(), true, phoneState -> {

                    int[] iArr = new int[MobileState.values().length];
                    iArr[MobileState.STATE_RINGTONE.ordinal()] = 1;
                    iArr[MobileState.STATE_ALARM.ordinal()] = 2;
                    iArr[MobileState.STATE_NOTIFICATION.ordinal()] = 3;
                    int[] $EnumSwitchMapping$0 = iArr;

                    int i = $EnumSwitchMapping$0[phoneState.ordinal()];
                    if (i == 1) {
                        Context requireContext1 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext1, "requireContext()");
                        settingsPhoneRing(requireContext1, audioModel.getPath(), MobileState.STATE_RINGTONE, true);
                    } else if (i == 2) {
                        Context requireContext2 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        settingsPhoneRing(requireContext2, audioModel.getPath(), MobileState.STATE_ALARM, true);
                    } else if (i == 3) {
                        Context requireContext3 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        settingsPhoneRing(requireContext3, audioModel.getPath(), MobileState.STATE_NOTIFICATION, true);
                    }
                    return null;
                }).show();
            } else {
                AppConstant.APP_CONSTANT.setCheckResumePermissionRingtone(true);
                RingtonePermission.openAndroidPermissionsMenu((Activity) requireContext);
            }
            return Unit.INSTANCE;
        }, audioModel -> {
            Intrinsics.checkNotNullParameter(audioModel, "it");
            AppConstant.APP_CONSTANT.setCheckResumeShareMyVoice(true);
            Context requireContext12 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext12, "requireContext()");
            shareFileProject(requireContext12, audioModel.getPath());
            return null;
        }, p1 -> {
            audioModel = p1;
            AudioModel model = p1;
            final File file = new File(model == null ? null : model.getPath());
            FragmentActivity requireActivity = requireActivity();

            if (Build.VERSION.SDK_INT <= 29) {
                deleteDialog = new DeleteDialog(requireActivity, true, () -> {
                    List<AudioModel> lists;
                    file.delete();
                    TypeIntrinsics.asMutableCollection(list).remove(p1);
                    boolean z = false;
                    Toast.makeText(getContext(), getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
                    ItemDowAudioAdapter audioAdapter2 = audioAdapter;
                    if (!(audioAdapter2 == null || (lists = audioAdapter2.getModelList()) == null || lists.size() != 0)) {
                        z = true;
                    }
                    if (z) {
                        list.add(new AudioModel("", "Sample.mp3", "", 0, "", ""));
                        ItemDowAudioAdapter audioAdapter1 = audioAdapter;
                        audioAdapter1.notifyDataSetChanged();
                    }
                    setAdapter();
                    return null;
                });
                DeleteDialog deleteDialog1 = deleteDialog;
                deleteDialog1.show();
            } else {
                String str = file.getAbsolutePath().toString();
                Context context = getContext();
                Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Media.getContentUri("external"), getFilePathToMedia(str, context));
                uris.clear();
                uris.add(withAppendedId);
                reqDeleteData(uris);
            }
            return null;
        });
        this.audioAdapter = itemDowAudioAdapter;
        rclAudio.setAdapter(itemDowAudioAdapter);

    }

    public void shareFileProject(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "path");
        Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(str));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        String msgShare;
        msgShare = context.getResources().getString(R.string.appShare) + "\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n";
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, msgShare);
        intent.putExtra(Intent.EXTRA_STREAM, uriForFile);

        new Handler().postDelayed(() -> FilenameUtils.isScreenOnOff = true, 500);

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
    }

    public void bindViewModels() {
        setAdapter();
    }

    public void setAdapter() {

        Context requireContext = requireContext();
        getViewModel().getAudioDataClass(requireContext, getDataBinding().llLoading, getDataBinding().ads, getDataBinding().noData);

        CreationActivity.COMPANION.getLiveSortCreateStudio().observe(requireActivity(), num -> {
            if (num != null && num == 1) {
                getViewModel().getListMutableLiveData().observe(requireActivity(), audioModels -> {
                    ItemDowAudioAdapter itemDowAudioAdapter;
                    if ((itemDowAudioAdapter = audioAdapter) != null) {
                        itemDowAudioAdapter.sortLatestData(audioModels);
                    }
                });
            } else if (num != null && num == 2) {
                getViewModel().getListMutableLiveData().observe(requireActivity(), audioModels -> {
                    ItemDowAudioAdapter itemDowAudioAdapter;
                    if ((itemDowAudioAdapter = audioAdapter) != null) {
                        itemDowAudioAdapter.sortOldestData(audioModels);
                    }
                });
            }
        });

        CreationActivity.COMPANION.getLiveSortNameStudio().observe(requireActivity(), num -> {
            Intrinsics.checkNotNullParameter(requireContext, "this$0");
            if (num != null && num.intValue() == 1) {
                getViewModel().getListMutableLiveData().observe(requireActivity(), audioModels -> {
                    ItemDowAudioAdapter itemDowAudioAdapter;
                    Intrinsics.checkNotNullParameter(requireContext, "this$0");
                    if (audioModels != null && (itemDowAudioAdapter = audioAdapter) != null) {
                        itemDowAudioAdapter.sortLatestDataByFileName(audioModels);
                    }
                });
            } else if (num != null && num.intValue() == 2) {
                getViewModel().getListMutableLiveData().observe(requireActivity(), audioModels -> {
                    ItemDowAudioAdapter itemDowAudioAdapter;
                    Intrinsics.checkNotNullParameter(requireContext, "this$0");
                    if (audioModels != null && (itemDowAudioAdapter = audioAdapter) != null) {
                        itemDowAudioAdapter.sortOldestByFileName(audioModels);
                    }
                });
            }
        });
    }

    public final long getFilePathToMedia(String strPaths, Context mCtx) {
        ContentResolver resolver = mCtx.getContentResolver();
        Uri external = MediaStore.Files.getContentUri("external");
        Cursor cursor = resolver.query(external, new String[]{"_id"}, Intrinsics.stringPlus("_data", "=?"), new String[]{strPaths}, (String) null);
        long j = 0;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String string = cursor.getString(cursor.getColumnIndex("_id"));
                j = Long.parseLong(string);
            }
        }
        return j;
    }

    public void reqRenameData(ArrayList<Uri> uris) {
        if (Build.VERSION.SDK_INT >= 30) {
            PendingIntent writeRequest = MediaStore.createWriteRequest(getContext().getContentResolver(), uris);
            try {
                requireActivity().startIntentSenderForResult(writeRequest.getIntentSender(), 1112, null, 0, 0, 67108864);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }


    public void reqDeleteData(ArrayList<Uri> uris) {
        PendingIntent deleteRequest = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            deleteRequest = MediaStore.createDeleteRequest(getContext().getContentResolver(), uris);
        }
        try {
            requireActivity().startIntentSenderForResult(deleteRequest.getIntentSender(), 1111, null, 0, 0, 67108864);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(this.receiver);
    }


    public void settingsPhoneRing(Context mCtx, String strPath, MobileState stateMobile, boolean z) {

        int[] iArr = new int[MobileState.values().length];
        iArr[MobileState.STATE_ALARM.ordinal()] = 1;
        iArr[MobileState.STATE_NOTIFICATION.ordinal()] = 2;
        iArr[MobileState.STATE_RINGTONE.ordinal()] = 3;
        int[] switchMapping = iArr;


        File file;
        Intrinsics.checkNotNullParameter(mCtx, "context");
        Intrinsics.checkNotNullParameter(strPath, "path");
        Intrinsics.checkNotNullParameter(stateMobile, "state");
        try {
            file = new File(strPath);
            Uri fromFile = Uri.fromFile(file);
            int i = switchMapping[stateMobile.ordinal()];
            if (i == 1) {
                RingtoneManager.setActualDefaultRingtoneUri(mCtx, 4, fromFile);
                if (z) {
                    Toast makeText = Toast.makeText(mCtx, mCtx.getString(R.string.success), Toast.LENGTH_LONG);
                    makeText.show();
                }
            } else if (i == 2) {
                RingtoneManager.setActualDefaultRingtoneUri(mCtx, 2, fromFile);
                if (z) {
                    Toast makeText2 = Toast.makeText(mCtx, mCtx.getString(R.string.success), Toast.LENGTH_LONG);
                    makeText2.show();
                }
            } else if (i == 3) {
                RingtoneManager.setActualDefaultRingtoneUri(mCtx, 1, fromFile);
                if (z) {
                    Toast makeText3 = Toast.makeText(mCtx, mCtx.getString(R.string.success), Toast.LENGTH_LONG);
                    makeText3.show();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

}
