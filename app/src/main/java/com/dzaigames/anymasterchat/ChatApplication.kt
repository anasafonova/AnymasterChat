package com.dzaigames.anymasterchat

import android.app.Application
import com.dzaigames.anymasterchat.di.ApplicationComponent
import com.dzaigames.anymasterchat.di.DaggerApplicationComponent

class ChatApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}