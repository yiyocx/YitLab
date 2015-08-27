package yiyo.gitlabandroid.utils

import android.content.Context
import android.content.SharedPreferences

/**
* Created by yiyo on 24/07/15.
*/
class Configuration(val mContext: Context) {

    public val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object {
        // Nombre del SharedPreferences de la aplicación
        private val SHARED_PREFS_NAME = "GITLAB_ANDROID_PREFS"

        // Llaves del SharedPreferences
        private val IS_LOGGED_IN = "is_logged_in"
        val NAME = "name"
        val USERNAME = "username"
        private val EMAIL = "email"
        private val PRIVATE_TOKEN = "private_token"
        private val USER_ID = "user_id"
    }

    init {
        sharedPreferences = mContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    /**
     * Create session for a user
     */
    fun createSession(name: String, username: String, email: String, privateToken: String, id: Int): Boolean {
        editor.putString(NAME, name)
        editor.putString(USERNAME, username)
        editor.putString(EMAIL, email)
        editor.putString(PRIVATE_TOKEN, privateToken)
        editor.putInt(USER_ID, id)
        editor.putBoolean(IS_LOGGED_IN, true)
        return editor.commit()
    }

    /**
     * Check for login state
     */
    fun isLoggedIn() = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

    /**
     * Cleaning all data from Shared Preferences
     */
    fun closeSession() {
        editor.remove(NAME)
        editor.remove(USERNAME)
        editor.remove(EMAIL)
        editor.remove(PRIVATE_TOKEN)
        editor.remove(USER_ID)
        editor.putBoolean(IS_LOGGED_IN, false)
        editor.apply()
    }

    fun getUserDetails() = hashMapOf(
            NAME  to sharedPreferences.getString(NAME, null),
            USERNAME to sharedPreferences.getString(USERNAME, null))

    fun getPrivateToken() = sharedPreferences.getString(PRIVATE_TOKEN, null)
}
