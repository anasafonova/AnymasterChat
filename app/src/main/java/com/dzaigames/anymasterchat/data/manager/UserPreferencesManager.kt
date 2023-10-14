package com.dzaigames.anymasterchat.data.manager

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

private const val USER_ID = "USER_ID"
private const val DEFAULT_USER_ID = 1

class UserPreferencesManager @Inject constructor(
    private val userPrefs: SharedPreferences
) {
    var userId: Int
        get() = userPrefs.getInt(USER_ID, 0)
        private set(value) = userPrefs.edit {
            putInt(USER_ID, value)
        }

    fun setDefaultUserId() {
        userId = DEFAULT_USER_ID
    }
}