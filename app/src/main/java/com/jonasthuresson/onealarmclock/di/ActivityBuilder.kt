package com.jonasthuresson.onealarmclock.di

import com.jonasthuresson.onealarmclock.android.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module()
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            FragmentBuilder::class,
            ViewModelModule::class,
            SpotifyModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity?

}