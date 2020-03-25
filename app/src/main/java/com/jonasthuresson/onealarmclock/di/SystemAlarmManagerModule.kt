package com.jonasthuresson.onealarmclock.di

import android.content.Context
import androidx.room.Room
import com.itshaven.testapp.test_room.di.ApplicationContext
import com.itshaven.testapp.test_room.di.DatabaseInfo
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.db.AlarmDao
import com.jonasthuresson.onealarmclock.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SystemAlarmManagerModule(@ApplicationContext context: Context) {
    @ApplicationContext
    private val mContext: Context = context

    @Singleton
    @Provides
    fun provideSystemAlarmManager(): SystemAlarmManager {
        return SystemAlarmManager(mContext)
    }

}