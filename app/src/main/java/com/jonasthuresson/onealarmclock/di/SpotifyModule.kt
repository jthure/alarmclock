package com.jonasthuresson.onealarmclock.di

import com.jonasthuresson.onealarmclock.android.MainActivity
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyAccessTokenRetrieverImpl
import com.jonasthuresson.onealarmclock.data.SpotifyRepo
import dagger.Module
import dagger.Provides

@Module
class SpotifyModule {

    @Provides
    @ActivityScope
    fun provideSpotifyAccessTokenRetriever(activity: MainActivity): SpotifyRepo.SpotifyAccessTokenRetriever =
        SpotifyAccessTokenRetrieverImpl(activity)
}