package com.jonasthuresson.onealarmclock.di

import android.app.Activity
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SpotifyManagerModule(activity: Activity) {
    @ApplicationContext
    private val mActivity: Activity = activity

    @Singleton
    @Provides
    fun provideSpotifyManager(activity: Activity): SpotifyManager {
        return SpotifyManager.newInstance(activity)
    }

}