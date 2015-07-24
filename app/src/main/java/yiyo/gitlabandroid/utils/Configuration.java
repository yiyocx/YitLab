package yiyo.gitlabandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yiyo on 24/07/15.
 */
public class Configuration {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context mContext;

    private static final String SHARED_PREFS_NAME = "GITLAB_ANDROID_PREFS";

    // Llaves del SharedPreferences
    private static final String IS_LOGGED_IN = "is_logged_in";
    private static final String NAME = "name";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PRIVATE_TOKEN = "private_token";
    private static final String USER_ID = "user_id";

    public Configuration(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = this.mContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public boolean createSession(String name, String username, String email, String privateToken, Integer id) {
        if (privateToken != null) {
            editor.putString(NAME, name);
            editor.putString(USERNAME, username);
            editor.putString(EMAIL, email);
            editor.putString(PRIVATE_TOKEN, privateToken);
            editor.putInt(USER_ID, id);
            editor.putBoolean(IS_LOGGED_IN, true);
            return editor.commit();
        } else {
            return false;
        }
    }

    /**
     * Check for login state
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    /**
     * Cleaning all data from Shared Preferences
     */
    public void closeSession() {
        editor.remove(NAME);
        editor.remove(USERNAME);
        editor.remove(EMAIL);
        editor.remove(PRIVATE_TOKEN);
        editor.remove(USER_ID);
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.apply();
    }
}
