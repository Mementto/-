package com.arcsoft.arcfacedemo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.arcsoft.arcfacedemo.repository.UserBean;

public class Storage {
    public static void setUserInfo(Context context, UserBean userBean) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("userId", userBean.getUserId());
        editor.putString("identity", userBean.getIdentity());
        editor.putString("username", userBean.getUsername());
        editor.putString("password", userBean.getPassword());
        editor.putInt("userType", userBean.getUserType());
        editor.putString("telephone", userBean.getTelephone());
        editor.putString("facePath", userBean.getFacePath());
        editor.commit();
    }

    public static void setFacePath(Context context, String facePath) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("facePath", facePath);
        editor.commit();
    }

    public static Long getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getLong("userId", 0);
    }

    public static int getUserType(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getInt("userType", 0);
    }

    public static String getFacePath(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getString("facePath", "0");
    }

    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getString("username", "0");
    }
}
