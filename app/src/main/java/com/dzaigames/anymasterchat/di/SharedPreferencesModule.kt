package com.dzaigames.anymasterchat.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    }
}