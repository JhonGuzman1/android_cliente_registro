package com.guzman.appevaluacion.components;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    SharedPreferences preferences;

    public Session(Context context) {
        preferences = context.getSharedPreferences("Session", Context.MODE_PRIVATE);
    }

    public static Boolean getLogin(SharedPreferences preferences) {
        return preferences.getBoolean("login", false);
    }

}
