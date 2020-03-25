package com.jonasthuresson.onealarmclock.di

import android.app.Application
import android.content.Context
import com.itshaven.testapp.test_room.di.ApplicationContext
import com.itshaven.testapp.test_room.di.DatabaseInfo
import com.jonasthuresson.onealarmclock.MainActivity
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import com.jonasthuresson.onealarmclock.android.services.BaseSoundService
import com.jonasthuresson.onealarmclock.android.ui.BaseFragment
import com.jonasthuresson.onealarmclock.android.ui.addalarm.AddAlarmFragment
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsFragment
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.android.ui.triggeredalarm.TriggeredAlarm
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, ViewModelModule::class, SystemAlarmManagerModule::class, SpotifyManagerModule::class])
interface ApplicationComponent {
    fun inject(o: OneAlarmApplication?)
    fun inject(o: MainActivity?)
    fun inject(o: AlarmsRepo?)
    fun inject(o: AlarmsFragment?)
    fun inject(o: AddAlarmFragment?)
    fun inject(o: TriggeredAlarm?)
    fun inject(o: BaseFragment?)
    fun inject(o: BaseSoundService?)
    @get:ApplicationContext
    val context: Context?

    val application: Application?
    @get:DatabaseInfo
    val databaseName: String?
}