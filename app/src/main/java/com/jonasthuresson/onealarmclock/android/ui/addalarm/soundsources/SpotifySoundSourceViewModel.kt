package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import androidx.lifecycle.*
import com.jonasthuresson.onealarmclock.data.SpotifyRepo
import com.jonasthuresson.onealarmclock.model.AlarmSound
import com.jonasthuresson.onealarmclock.model.SpotifySound
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpotifySoundSourceViewModel @Inject constructor(private val spotifyRepo: SpotifyRepo) :
    ViewModel() {
    init {
        viewModelScope.launch { spotifyRepo.init() }
    }

    var searchText: String = ""
        set(value) {
            field = value
            if (value.isNotEmpty()) search(value)
        }

    private val _searchResult: MutableLiveData<List<AlarmSound>> = MutableLiveData()
    val searchResult: LiveData<List<AlarmSound>>
        get() = _searchResult


//    private fun search2(text: String) {
//        searchResult = liveData {
//            spotifyRepo.search2(text)
//                .collect {
//                    emit(
//                        it.tracks.items.map { item ->
//                            AlarmSound(
//                                item.name
//                            )
//
//                        }
//                    )
//                }
//        }
//    }

    private fun search(text: String) {

        viewModelScope.launch {
            val response = spotifyRepo.search(text)
            _searchResult.value = response.tracks.items.map { item ->
                SpotifySound(
                    item.name,
                    ""
                )
            }
            val x = 3
        }
    }
}