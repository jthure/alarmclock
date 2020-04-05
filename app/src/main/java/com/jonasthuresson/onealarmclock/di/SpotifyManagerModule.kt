package com.jonasthuresson.onealarmclock.di

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.itshaven.testapp.test_room.di.ApplicationContext
import com.itshaven.testapp.test_room.di.DatabaseInfo
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyManager
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.db.AlarmDao
import com.jonasthuresson.onealarmclock.db.AppDatabase
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import javax.inject.Singleton


@Module
class SpotifyManagerModule(@ApplicationContext context: Context) {
    @ApplicationContext
    private val mContext: Context = context

    @Singleton
    @Provides
    fun provideSpotifyManager(): SpotifyManager {
        return SpotifyManager.newInstance(mContext)
    }

}