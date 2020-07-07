package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyManager
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import javax.inject.Inject

//class SpotifySoundSourceViewModel@Inject constructor(private val spotifyManager: SpotifyManager) : ViewModel() {
    class SpotifySoundSourceViewModel @Inject constructor(): ViewModel() {
    var searchText: String = ""
    set(value) {
        field = value
        search(value)
    }

    private fun search(text: String){
        viewModelScope.launch {
//            spotifyManager.search()
        }
    }
}