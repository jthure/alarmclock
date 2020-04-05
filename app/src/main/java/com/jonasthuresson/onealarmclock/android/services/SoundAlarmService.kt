package com.jonasthuresson.onealarmclock.android.services

import android.media.MediaPlayer
import com.jonasthuresson.onealarmclock.R


class SoundAlarmService: BaseAlarmService() {

    var mediaPlayer: MediaPlayer? = null

    override fun startSound(){
        mediaPlayer = MediaPlayer.create(this, R.raw.good_morning)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }
    override fun stopSound(){
        mediaPlayer?.release()
        mediaPlayer = null
    }
}