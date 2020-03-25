package com.jonasthuresson.onealarmclock.di

import android.app.Application
import android.content.Context
import com.itshaven.testapp.test_room.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val mApplication: Application) {
    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

}