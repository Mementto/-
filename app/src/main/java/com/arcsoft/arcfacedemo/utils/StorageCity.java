package com.arcsoft.arcfacedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageCity {

    public static void setCityName(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences("city", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("now_name", name);
        editor.commit();
    }

    public static String getCityName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("city", Context.MODE_PRIVATE);
        return preferences.getString("now_name", null);
    }

    public static void setLatAndLong(Context context, double latitude, double longitude) {
        SharedPreferences preferences = context.getSharedPreferences("city", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("latitude", latitude + "");
        editor.putString("longitude", longitude + "");
        editor.commit();
    }

    public static Double getLat(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("city", Context.MODE_PRIVATE);
        return Double.parseDouble(preferences.getString("latitude", "0"));
    }

    public static Double getLong(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("city", Context.MODE_PRIVATE);
        return Double.parseDouble(preferences.getString("longitude", "0"));
    }

}
