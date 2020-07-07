package com.jonasthuresson.onealarmclock.di

import android.app.Application
import android.content.Context
import com.jonasthuresson.onealarmclock.android.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class ApplicationModule() {
    @Singleton
    @Binds
    @ApplicationContext
    abstract fun provideContext(application: Application): Context

    @Singleton
    @Binds
    abstract fun provideApplication(application: Application): Application


}