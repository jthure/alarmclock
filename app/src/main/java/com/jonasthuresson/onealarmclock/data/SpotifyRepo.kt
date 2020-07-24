package com.jonasthuresson.onealarmclock.data

import com.jonasthuresson.onealarmclock.di.RequestHeaders
import com.jonasthuresson.onealarmclock.di.SpotifySearhResponse
import com.jonasthuresson.onealarmclock.di.SpotifyServices
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpotifyRepo @Inject constructor(
    private val accessTokenRetriever: SpotifyAccessTokenRetriever,
    private val spotifyService: SpotifyServices,
    private val requestHeaders: RequestHeaders
) {
    private var appRemote: SpotifyAppRemote? = null

    suspend fun init() {
        val accessToken = accessTokenRetriever.getAccessToken()
        requestHeaders.accessToken = accessToken
    }
    suspend fun initAppRemote(){
        appRemote = accessTokenRetriever.getAppRemote()
    }

    suspend fun search(text: String): SpotifySearhResponse {
        return getInitiatedSpotifyService().search(text)
    }
    suspend fun search2(text: String): Flow<SpotifySearhResponse> {
        return flow{
            val result = getInitiatedSpotifyService().search(text)
            emit(result)
        }
    }

    suspend fun playUri(uri: String){
        getInitiatedAppRemote()?.playerApi?.play(uri)
    }

    private suspend fun getInitiatedSpotifyService(): SpotifyServices {
        if (requestHeaders.accessToken == null) init()
        return spotifyService
    }

    private suspend fun getInitiatedAppRemote(): SpotifyAppRemote? {
        if (appRemote == null) initAppRemote()
        return appRemote
    }

    interface SpotifyAccessTokenRetriever {
        suspend fun getAccessToken(): String
        suspend fun getAppRemote(): SpotifyAppRemote
    }
}