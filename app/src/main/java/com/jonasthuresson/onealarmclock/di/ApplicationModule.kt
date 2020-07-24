package com.jonasthuresson.onealarmclock.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module()
abstract class ApplicationModule {
    @Singleton
    @Binds
    @ApplicationContext
    abstract fun provideContext(application: Application): Context

    @Singleton
    @Binds
    abstract fun provideApplication(application: Application): Application

}