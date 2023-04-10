package com.example.doan.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveToken {

    private static SharedPreferences sharedPreferences;
    private static final String TOKEN_KEY = "TOKEN_KEY";

    public  static void   init(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveTokens(String token) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply();
    }
    public static String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public static void clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply();
    }
}
