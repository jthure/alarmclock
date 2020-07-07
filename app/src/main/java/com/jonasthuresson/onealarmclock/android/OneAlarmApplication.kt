package com.jonasthuresson.onealarmclock.android

import android.app.Application
import com.jonasthuresson.onealarmclock.di.*
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class OneAlarmApplication : DaggerApplication() {
    protected var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
//        mApplicationComponent = DaggerApplicationComponent
//            .builder()
//            .applicationModule(ApplicationModule(this))
//            .databaseModule(DatabaseModule(this))
//            .systemAlarmManagerModule(SystemAlarmManagerModule(this))
////            .spotifyManagerModule(SpotifyManagerModule(this))
//            .build()
//        mApplicationComponent!!.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<OneAlarmApplication> {
        return DaggerApplicationComponent.factory()
            .create(
                this,
                SystemAlarmManagerModule(this),
                DatabaseModule(this)
            )
    }

//    fun getComponent(): ApplicationComponent? {
//        return mApplicationComponent
//    }
}