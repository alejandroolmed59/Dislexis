package com.dislexisapp.dislexis.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.dislexisapp.dislexis.AppConstants.LOGGED_IN_PREF

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
}