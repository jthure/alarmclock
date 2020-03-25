package com.jonasthuresson.onealarmclock.di

import android.content.Context
import androidx.room.Room
import com.itshaven.testapp.test_room.di.ApplicationContext
import com.itshaven.testapp.test_room.di.DatabaseInfo
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.db.AlarmDao
import com.jonasthuresson.onealarmclock.db.AppDatabase
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