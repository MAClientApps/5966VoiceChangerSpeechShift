#------------- Glide ----------
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.** { *; }

-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.picasso.**

-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#=======================================

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static *** d(...);
    public static *** w(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
}

#------------------ My Class -----------------------

-dontwarn com.voice.changer.speechshift.myAdsClasses.**
-keep class com.voice.changer.speechshift.myAdsClasses.** { *; }

-dontwarn com.reactlibrary.object.ModelEffects
-keep class com.reactlibrary.object.ModelEffects { *; }

-dontwarn com.voice.changer.speechshift.MainApplication
-keep class com.voice.changer.speechshift.MainApplication { *; }

-dontwarn com.un4seen.bass.**
-keep class com.un4seen.bass.** { *; }

-dontwarn com.voice.changer.speechshift.getApiData.allModel.**
-keep class com.voice.changer.speechshift.getApiData.allModel.** { *; }

-dontwarn com.voice.changer.speechshift.getApiData.appScheduler.**
-keep class com.voice.changer.speechshift.getApiData.appScheduler.** { *; }

#==================================================

-flattenpackagehierarchy
-ignorewarnings

-keep public class com.google.android.gms.ads.**{
   public *;
}
-keep public class com.google.android.gms.** { public protected *; }

-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient.**
-keep class org.apache.http.** { *; }
-keep class android.net.http.** { *; }

-keep class com.google.android.** { *; }

-obfuscationdictionary "V:\Sdk\tools\class_encode_dictionary.txt"
-classobfuscationdictionary "V:\Sdk\tools\class_encode_dictionary.txt"
-packageobfuscationdictionary "V:\Sdk\tools\class_encode_dictionary.txt"

-mergeinterfacesaggressively
-overloadaggressively
-repackageclasses "com.voice.changer.speechshift"