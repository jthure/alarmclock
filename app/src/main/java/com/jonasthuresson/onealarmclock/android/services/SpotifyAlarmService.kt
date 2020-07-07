package com.jonasthuresson.onealarmclock.android.services

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyManager
import dagger.android.AndroidInjection
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


class SpotifyAlarmService : BaseAlarmService() {

//    @Inject
//    lateinit var spotifyManager: SpotifyManager

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    @ExperimentalCoroutinesApi
    override fun startSound() {
        lifecycleScope.launch {
//            spotifyManager.play()
        }

    }

    @ExperimentalCoroutinesApi
    override fun stopSound() {
        lifecycleScope.launch {
//            spotifyManager.stop()
        }
    }

}