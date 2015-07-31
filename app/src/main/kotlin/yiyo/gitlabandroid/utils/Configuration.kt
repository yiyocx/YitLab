package yiyo.gitlabandroid.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by yiyo on 24/07/15.
 */
class Configuration(val mContext: Context) {

    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = this.mContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    public fun createSession(name: String, username: String, email: String, privateToken: String?, id: Int?): Boolean {
        if (privateToken != null) {
            editor.putString(NAME, name)
            editor.putString(USERNAME, username)
            editor.putString(EMAIL, email)
            editor.putString(PRIVATE_TOKEN, privateToken)
            editor.putInt(USER_ID, id!!)
            editor.putBoolean(IS_LOGGED_IN, true)
            return editor.commit()
        } else {
            return false
        }
    }

    /**
     * Check for login state
     */
    public fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    /**
     * Cleaning all data from Shared Preferences
     */
    public fun closeSession() {
        editor.remove(NAME)
        editor.remove(USERNAME)
        editor.remove(EMAIL)
        editor.remove(PRIVATE_TOKEN)
        editor.remove(USER_ID)
        editor.putBoolean(IS_LOGGED_IN, false)
        editor.apply()
    }

    companion object {

        private val SHARED_PREFS_NAME = "GITLAB_ANDROID_PREFS"

        // Llaves del SharedPreferences
        private val IS_LOGGED_IN = "is_logged_in"
        private val NAME = "name"
        private val USERNAME = "username"
        private val EMAIL = "email"
        private val PRIVATE_TOKEN = "private_token"
        private val USER_ID = "user_id"
    }
}
