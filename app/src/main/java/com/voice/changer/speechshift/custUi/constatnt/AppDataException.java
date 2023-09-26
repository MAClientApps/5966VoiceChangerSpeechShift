package com.voice.changer.speechshift.custUi.constatnt;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.DisplayMetrics;

import com.voice.changer.speechshift.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u001a\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b\u001a\u000e\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0003\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0016\u001a\u00020\u0017\u001a\u001a\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0019\u001a\u00020\u0006H\u0007\u001a\u001a\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0019\u001a\u00020\u0006H\u0007\u001ah\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u001d*\u00020\u001e2\u001c\u0010\u001f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110!\u0012\u0006\u0012\u0004\u0018\u00010\"0 2\u001c\u0010#\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0!\u0012\u0006\u0012\u0004\u0018\u00010\"0 2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u00020\u00110 ø\u0001\u0000¢\u0006\u0002\u0010%\u001a\u0016\u0010&\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001d*\b\u0012\u0004\u0012\u0002H\u001d0'\u001a*\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u001d0)\"\u0004\b\u0000\u0010\u001d*\b\u0012\u0004\u0012\u0002H\u001d0)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010+\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"checkFistLastCurrency", "", "currency", "", "getCurrencySub", "getHeightMetrics", "", "activity", "Landroid/app/Activity;", "getIconEffect", "context", "Landroid/content/Context;", "fileName", "getSpecialCharacterCount", "s", "getWithMetrics", "hideKeyboard", "", "isNetwork", "logEvent", "event", "round2Dec", "number", "", "setStatusBar", "statusBar", "setStatusBarColor", "executeAsync", "Lkotlinx/coroutines/Job;", "T", "Lkotlinx/coroutines/CoroutineScope;", "onPreExecute", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "doInBackground", "onPostExecute", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "hasValue", "Landroidx/lifecycle/LiveData;", "trackingProgress", "Lkotlinx/coroutines/flow/Flow;", "progressBar", "Lio/reactivex/subjects/PublishSubject;", "VoiceEffect_v1.0.7_v10_03.23.2023_appProductRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class AppDataException {
    public static AsyncTask<String, String, String[]> executeAsync(CoroutineScope coroutineScope, Function1 onPreExecute, Function1 doInBackground, Function1 onPostExecute) {
        return new AsyncTaskExample(onPreExecute, doInBackground, onPostExecute).execute();
    }

    private static class AsyncTaskExample extends AsyncTask<String, String, String[]> {
        private final Function1 mOnPreExecute;
        private final Function1 mDoInBackground;
        private final Function1 mOnPostExecute;

        public <T> AsyncTaskExample(Function1 onPreExecute, Function1 doInBackground, Function1 onPostExecute) {
            this.mOnPreExecute = onPreExecute;
            this.mDoInBackground = doInBackground;
            this.mOnPostExecute = onPostExecute;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mOnPreExecute.invoke("onPreExecute");
        }

        @Override
        protected String[] doInBackground(String... strings) {
            mDoInBackground.invoke(strings);
            return strings;
        }

        @Override
        protected void onPostExecute(String[] string) {
            super.onPostExecute(string);
            mOnPostExecute.invoke(string);
        }
    }


    public static int getWithMetrics(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getIconEffect(String s) {
        CharSequence s1 = s;
        int i = StringsKt.contains(s1, "normal", false) ? R.drawable.ic_effect_normal : -1;
        if (StringsKt.contains(s1, "alien", false)) {
            i = R.drawable.ic_effect_alien;
        }
        if (StringsKt.contains(s1, "robot", false)) {
            i = R.drawable.ic_effect_roboto;
        }
        if (StringsKt.contains(s1, "ghost", false)) {
            i = R.drawable.ic_effect_ghost;
        }
        if (StringsKt.contains(s1, "cat", false)) {
            i = R.drawable.ic_effect_cat;
        }
        if (StringsKt.contains(s1, "oldman", false)) {
            i = R.drawable.ic_effect_oldman;
        }
        if (StringsKt.contains(s1, "child", false)) {
            i = R.drawable.ic_effect_child;
        }
        if (StringsKt.contains(s1, "women", false)) {
            i = R.drawable.ic_effect_women;
        }
        if (StringsKt.contains(s1, "helium", false)) {
            i = R.drawable.ic_effect_helium;
        }
        if (StringsKt.contains(s1, "monster", false)) {
            i = R.drawable.ic_effect_monster;
        }
        if (StringsKt.contains(s1, "multiple", false)) {
            i = R.drawable.ic_effect_multiple;
        }
        if (StringsKt.contains(s1, "cave", false)) {
            i = R.drawable.ic_effect_cave;
        }
        if (StringsKt.contains(s1, "drunk", false)) {
            i = R.drawable.ic_effect_drunk;
        }
        if (StringsKt.contains(s1, "extraterrestrial", false)) {
            i = R.drawable.ic_effect_extraterrestrial;
        }
        if (StringsKt.contains(s1, "nervous", false)) {
            i = R.drawable.ic_effect_nervous;
        }
        if (StringsKt.contains(s1, "telephone", false)) {
            i = R.drawable.ic_effect_telephone;
        }
        if (StringsKt.contains(s1, "backchipmunk", false)) {
            i = R.drawable.ic_effect_chimp;
        }
        if (StringsKt.contains(s1, "underwater", false)) {
            i = R.drawable.ic_effect_underwater;
        }
        if (StringsKt.contains(s1, "zombie", false)) {
            i = R.drawable.ic_effect_zombie;
        }
        if (StringsKt.contains(s1, "megaphone", false)) {
            i = R.drawable.ic_effect_megaphone;
        }
        if (StringsKt.contains(s1, "death", false)) {
            i = R.drawable.ic_effect_death;
        }
        if (StringsKt.contains(s1, "villain", false)) {
            i = R.drawable.ic_effect_villain;
        }
        if (StringsKt.contains(s1, "grandcanyon", false)) {
            i = R.drawable.ic_effect_grand_canyon;
        }
        if (StringsKt.contains(s1, "reverse", false)) {
            i = R.drawable.ic_effect_reverse;
        }
        return StringsKt.contains(s1, "squirrel", false) ? R.drawable.ic_effect_squirrel : i;
    }

    public static boolean getSpecialChar(String strSpecial) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9_ ]");
        Intrinsics.checkNotNullExpressionValue(pattern, "compile(\"[^A-Za-z0-9_ ]\")");
        Matcher matchers = pattern.matcher(strSpecial);
        Intrinsics.checkNotNullExpressionValue(matchers, "p.matcher(s)");
        return matchers.find();
    }
}
