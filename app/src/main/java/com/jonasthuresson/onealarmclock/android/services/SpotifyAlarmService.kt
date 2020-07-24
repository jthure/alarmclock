package com.jonasthuresson.onealarmclock.android.services

import androidx.lifecycle.lifecycleScope
import com.jonasthuresson.onealarmclock.data.SpotifyRepo
import dagger.android.AndroidInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


class SpotifyAlarmService @Inject constructor(private val spotifyRepo: SpotifyRepo) : BaseAlarmService() {

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