package com.jonasthuresson.onealarmclock.android.services

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyManager
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


class SpotifyAlarmService : BaseAlarmService() {

    var mediaPlayer: MediaPlayer? = null

    @Inject
    lateinit var spotifyManager: SpotifyManager

    override fun onCreate() {
        super.onCreate()
        (application as OneAlarmApplication).getComponent()?.inject(this)
    }

    @ExperimentalCoroutinesApi
    override fun startSound() {
        lifecycleScope.launch {
            spotifyManager.play()
        }
//        if(spotifyManager.value != null) startSpotifySound(spotifyManager.value!!)
//        else spotifyManager.observe(this, Observer { mgr -> startSpotifySound(mgr) })
    }

    @ExperimentalCoroutinesApi
    override fun stopSound() {
        lifecycleScope.launch {
            spotifyManager.stop()
        }
    }

//    private suspend fun getSpotifyManager(): SpotifyManager? {
//        if (spotifyManager.isActive) spotifyManager.await()
//        if (!spotifyManager.isCompleted) return null
//        return spotifyManager.getCompleted()
//
//    }
}