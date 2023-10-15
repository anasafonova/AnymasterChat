package com.dzaigames.anymasterchat.di

import android.content.Context
import com.dzaigames.anymasterchat.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        MessagesRepositoryModule::class,
        SharedPreferencesModule::class,
        UserManagerModule::class,
        WebSocketModule::class
    ]
)
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}