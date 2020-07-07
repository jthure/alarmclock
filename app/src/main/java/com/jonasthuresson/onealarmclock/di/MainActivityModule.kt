package com.jonasthuresson.onealarmclock.di

import com.jonasthuresson.onealarmclock.android.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class MainActivityModule {
    @Singleton
    @Binds
    abstract fun provideMainView(mainActivity: MainActivity): MainActivity

}