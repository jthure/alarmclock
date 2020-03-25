package com.jonasthuresson.onealarmclock.android

import android.app.Application
import com.jonasthuresson.onealarmclock.di.*


class OneAlarmApplication : Application() {
    protected var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .systemAlarmManagerModule(SystemAlarmManagerModule(this))
            .spotifyManagerModule(SpotifyManagerModule(this))
            .build()
        mApplicationComponent!!.inject(this)
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}