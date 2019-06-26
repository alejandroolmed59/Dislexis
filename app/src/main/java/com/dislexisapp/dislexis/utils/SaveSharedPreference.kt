package com.dislexisapp.dislexis.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.dislexisapp.dislexis.utils.AppConstants.LOGGED_IN_PREF
import com.dislexisapp.dislexis.utils.AppConstants.PASSWORD_PREF
import com.dislexisapp.dislexis.utils.AppConstants.USER_PREF

object SaveSharedPreference {

    private fun getPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    /**
     * Set the Login Status
     * @param context
     * @param status
     */
    fun setLoggedIn(context: Context, status: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(LOGGED_IN_PREF, status)
        editor.apply()
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    fun getLoggedStatus(context: Context): Boolean {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false)
    }

    fun setUsername(context: Context, user: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_PREF, user)
        editor.apply()
    }

    fun setPassword(context: Context, pass: String) {
        val editor = getPreferences(context).edit()
        editor.putString(PASSWORD_PREF, pass)
        editor.apply()
    }

    fun getUsername(context: Context): String? {
        return getPreferences(context).getString(USER_PREF, "YUSER")
    }

    fun getPassword(context: Context): String? {
        return getPreferences(context).getString(PASSWORD_PREF, "SEÑA")
    }
}