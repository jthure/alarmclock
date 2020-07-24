package com.jonasthuresson.onealarmclock.android

import com.jonasthuresson.onealarmclock.di.DaggerApplicationComponent
import com.jonasthuresson.onealarmclock.di.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class OneAlarmApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<OneAlarmApplication> {
        return DaggerApplicationComponent.factory()
            .create(
                this,
                this,
                DatabaseModule(this)
            )
    }

}