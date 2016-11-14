package com.kukuhsain.simple.boilerplate.model.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.kukuhsain.simple.boilerplate.SimpleApp;

/**
 * Created by kukuh on 20/10/16.
 */

public class PreferencesHelper {
    private static PreferencesHelper INSTANCE;
    private SharedPreferences sharedPreferences;

    private PreferencesHelper() {
        sharedPreferences = SimpleApp.getInstance()
                .getSharedPreferences("simpletour.host.sp", Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesHelper();
        }
        return INSTANCE;
    }

    public void putAccessToken(String accessToken) {
        sharedPreferences.edit().putString("accessToken", accessToken).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString("accessToken", "");
    }

    public void setLoggedInStatus(boolean loggedInStatus) {
        sharedPreferences.edit().putBoolean("loggedInStatus", loggedInStatus).apply();
    }

    public boolean getLoggedInStatus() {
        return sharedPreferences.getBoolean("loggedInStatus", false);
    }

    public void clearData() {
        sharedPreferences.edit().clear().apply();
    }
}
