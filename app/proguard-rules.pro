# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


-keepattributes *JavascriptInterface*
-keepattributes InnerClasses
-keepattributes EnclosingMethod

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#忽略警告 也可以用-ignorewarnings
-dontwarn
#避免混淆泛型
#不混淆注释
-keepattributes *Annotation

#声明第三方jar包,不用管第三方jar包中的.so文件(如果有)

-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation { *; }


-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keepclasseswithmembernames class * {
native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
public void onReceiveTransaction(java.lang.Object[]);
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
public void onReceiveTransaction(java.lang.Object[]);
}
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}

-keep public class * implements java.io.Serializable {
    *;
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
   public void onReceiveTransaction(java.lang.Object[]);
}

-keep public class [com.baimi.wallet].R$*{
public static final int *;
}


-dontwarn android.support.v4.**
-keep class android.support.v4.** {*;}

-keepattributes Signature

-dontwarn com.rt.**
-keep class com.rt.**{*;}

-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

-dontwarn okio.**
-keep class okio.**{*;}

-keep class com.iflytek.**{*;}

-dontwarn com.google.**
-keep class com.google.gson.** {*;}

-dontwarn com.google.**
-keep class com.google.protobuf.** {*;}

-dontwarn javax.annotation.**
-dontwarn javax.inject.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static Java.lang.String TABLENAME;
}
-keep class **$Properties


