package com.dzaigames.anymasterchat.di

import android.content.SharedPreferences
import com.dzaigames.anymasterchat.data.manager.UserPreferencesManager
import dagger.Module
import dagger.Provides

@Module
class UserPreferencesModule {

    @Provides
    fun provideSharedPreferences(userPrefs: SharedPreferences): UserPreferencesManager {
        return UserPreferencesManager(userPrefs = userPrefs)
    }
}