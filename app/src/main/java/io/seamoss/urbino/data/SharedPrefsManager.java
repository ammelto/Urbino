package io.seamoss.urbino.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Alexander Melton on 2/18/2017.
 *
 * Manager fpr shared preferences
 * This holds all of the final string keys as well as default values for the shared preferences.
 * This also returns any of the values needed from the shared preferences and takes care of the boilerplate
 * so the actual implementation doesn't get muddied up with shared prefs code.
 */

public class SharedPrefsManager {
    public static final Boolean NOT_ONBOARDED = false;
    public static final String ONBOARDING = "urbino.onboarding";

    private SharedPreferences sharedPreferences;

    public SharedPrefsManager(Application application){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

    public boolean isOnboarded(){
        return sharedPreferences.getBoolean(SharedPrefsManager.ONBOARDING, SharedPrefsManager.NOT_ONBOARDED);
    }

    public void setOnboarding(Boolean isOnboarded){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SharedPrefsManager.ONBOARDING, isOnboarded);
        editor.apply();
    }

    public void clearSharedPrefs(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
