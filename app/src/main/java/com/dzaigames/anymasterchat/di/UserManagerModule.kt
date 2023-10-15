package com.dzaigames.anymasterchat.di

import android.content.SharedPreferences
import com.dzaigames.anymasterchat.data.manager.UserPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserManagerModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(userPrefs: SharedPreferences): UserPreferencesManager {
        return UserPreferencesManager(userPrefs = userPrefs)
    }
}