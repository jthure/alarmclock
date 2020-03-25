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
import javax.inject.Singleton


@Module
class SpotifyManagerModule(@ApplicationContext context: Context) {
    @ApplicationContext
    private val mContext: Context = context
    private val CLIENT_ID = "7310d321ef974502973b1808e37bbc14"
    private val REDIRECT_URI = "http://onealarmclock.jonasthuresson.com/spotify/auth"

    @Singleton
    @Provides
    fun provideSpotifyManager(): LiveData<SpotifyManager> {
        val x = MutableLiveData<SpotifyManager>()
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()
        SpotifyAppRemote.connect(mContext, connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    x.value = SpotifyManager(spotifyAppRemote)
                    Log.d("MainActivity", "Connected! Yay!")
                    //mSpotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");

                    // Now you can start interacting with App Remote
                    //connected()
                }

                override fun onFailure(throwable: Throwable) {
                    Log.e("MainActivity", throwable.message, throwable)

                    // Something went wrong when attempting to connect! Handle errors here
                }
            })
        return x
    }

}