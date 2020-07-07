package com.jonasthuresson.onealarmclock.di

import android.content.Context
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SystemAlarmManagerModule(@ApplicationContext private val context: Context) {

    @Singleton
    @Provides
    fun provideSystemAlarmManager(@ApplicationContext context: Context): SystemAlarmManager {
        return SystemAlarmManager(context)
    }

}