package com.jonasthuresson.onealarmclock.di

import android.app.Application
import android.content.Context
import com.jonasthuresson.onealarmclock.android.MainActivity
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
//        ActivityBuilder::class,
//        MainActivityModule::class,
        FragmentBuilder::class,
        DatabaseModule::class,
        ViewModelModule::class,
        SystemAlarmManagerModule::class
    ]
)
interface ApplicationComponent: AndroidInjector<OneAlarmApplication?> {
//    fun inject(o: OneAlarmApplication?)
//    fun inject(o: MainActivity?)
//    fun inject(o: AlarmsRepo?)
//    fun inject(o: AlarmsFragment?)
//    fun inject(o: AddAlarmFragment?)
//    fun inject(o: TriggeredAlarmFragment?)
//    fun inject(o: SpotifySoundSourceFragment?)
//    fun inject(o: BaseFragment?)
//    fun inject(o: BaseAlarmService?)
//    fun inject(o: SpotifyAlarmService?)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: OneAlarmApplication, m2: SystemAlarmManagerModule, m3: DatabaseModule): ApplicationComponent
//        fun create(m2: SystemAlarmManagerModule, m3: DatabaseModule): ApplicationComponent
    }


    @get:ApplicationContext
    val context: Context?

    val application: Application?

    @get:DatabaseInfo
    val databaseName: String?
}