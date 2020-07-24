package com.jonasthuresson.onealarmclock.di

import android.app.Application
import android.content.Context
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBuilder::class,
        RetrofitModule::class,
//        MainActivityModule::class,
//        FragmentBuilder::class,
        DatabaseModule::class
//        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<OneAlarmApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            @BindsInstance application: Application,
            databaseModule: DatabaseModule
        ): ApplicationComponent
    }

    @get:DatabaseInfo
    val databaseName: String?
}