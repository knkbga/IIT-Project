# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\OM\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
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
-keepclassmembers class com.dom925.com.example.MyApp {
   public *;
}

-keepattributes SourceFile,LineNumberTable,Signature
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keep class * extends com.google.gson.reflect.TypeToken
-keepclasseswithmembernames class * {
    native <methods>;
}
-renamesourcefileattribute SourceFile
-keep public class * extends android.app.Application

-keep class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
    private *;
    void set*(***);
    *** get*();
}
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.example.MyApp.** { *; }
