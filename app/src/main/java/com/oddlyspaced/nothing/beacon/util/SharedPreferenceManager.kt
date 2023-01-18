package com.oddlyspaced.nothing.beacon.util

import android.content.Context
import com.oddlyspaced.nothing.beacon.lib.enum.NothingResource


class SharedPreferenceManager(context: Context) {

    companion object {
        const val KEY_SELECTED_RINGTONE = "ringtone"
    }

    private val sharedPreferencesKey = "shared_preference"
    private val sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
    private val editor by lazy { sharedPreferences.edit() }

    fun getRingtone(): NothingResource {
        val ringtoneName = sharedPreferences.getString(KEY_SELECTED_RINGTONE, "ABRA") ?: "ABRA"
        return NothingResource.valueOf(ringtoneName)
    }

    fun saveRingtone(ringtone: NothingResource) {
        editor.apply {
            putString(KEY_SELECTED_RINGTONE, ringtone.name)
            apply()
        }
    }

}