package com.arcsoft.arcfacedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {
    public static void setUserId(Context context, Long userId) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("userId", userId);
    }

    public static Long getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getLong("userId", 0);
    }
}
