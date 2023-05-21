package com.etechspare.viteats;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    private static final String PREF_NAME = "viteats";

    private SharedPrefHelper() {
    }


    @SuppressWarnings("deprecation")
    private static final int MODE = Context.MODE_PRIVATE;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    public static int readInteger(Context context, String key) {
        return getPreferences(context).getInt(key, 0);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    public static String readString(Context context, String key) {
        return getPreferences(context).getString(key, null);
    }

    public static void clear(Context context, String key) {
        getEditor(context).remove(key).clear().commit();
    }

    public static void clearAll(Context context) {
        getEditor(context).clear().commit();
    }
}