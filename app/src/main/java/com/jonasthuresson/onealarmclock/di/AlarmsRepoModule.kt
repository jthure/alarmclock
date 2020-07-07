package com.jonasthuresson.onealarmclock.di

import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.db.AlarmDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AlarmsRepoModule(context: AlarmDao) {
    private val mAlarmDao: AlarmDao = context

    @Singleton
    @Provides
    fun provideAlarmRepo(): AlarmsRepo {
        return AlarmsRepo(mAlarmDao)
    }

}