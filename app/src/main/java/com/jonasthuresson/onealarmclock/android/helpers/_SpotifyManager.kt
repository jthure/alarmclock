package com.jonasthuresson.onealarmclock.android.helpers

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.jonasthuresson.onealarmclock.TAG
import com.jonasthuresson.onealarmclock.android.MainActivity
import com.jonasthuresson.onealarmclock.di.RequestHeaders
import com.jonasthuresson.onealarmclock.di.SpotifySearhResponse
import com.jonasthuresson.onealarmclock.di.SpotifyServices
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class SpotifyManager @Inject constructor(
    private val activity: MainActivity,
    private val spotifyServices: SpotifyServices,
    private val requestHeaders: RequestHeaders
) {

    //    private lateinit var defferSpotifyAppRemote: Deferred<SpotifyAppRemote>
    suspend fun play() {

    }

    suspend fun stop() {

    }

    suspend fun search(text: String): SpotifySearhResponse {
        val result = getSpotifyWebApi().search(text)
        Toast.makeText(activity, "Got Spotify api!", Toast.LENGTH_LONG).show()
        return result
    }
    suspend fun initSpotifyWebApi() = getSpotifyWebApi()


    private suspend fun getSpotifyWebApi(): SpotifyWebApi {
        return SpotifyWebApi.getInstance(activity, spotifyServices, requestHeaders).await()
    }

    class SpotifyWebApi(private val spotifyServices: SpotifyServices, private val requestHeaders: RequestHeaders) : MainActivity.ActivityResultObserver {
        companion object {
            private val AUTHENTICATION_REQUEST_CODE = "spotify_authentication".hashCode().and(0xF0)
            private const val CLIENT_ID = "7310d321ef974502973b1808e37bbc14"
            private const val REDIRECT_URI = "http://onealarmclock.jonasthuresson.com/spotify/auth"

            @Volatile private var INSTANCE: CompletableDeferred<SpotifyWebApi>? = null

            fun getInstance(activity: MainActivity, spotifyServices: SpotifyServices, requestHeaders: RequestHeaders): CompletableDeferred<SpotifyWebApi> =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: authenticate(activity, spotifyServices, requestHeaders).also {
                        INSTANCE = it
                    }
                }

            private fun authenticate(activity: MainActivity, spotifyServices: SpotifyServices, requestHeaders: RequestHeaders): CompletableDeferred<SpotifyWebApi>{
                val instance = SpotifyWebApi(spotifyServices, requestHeaders)
                activity.addActivityResultObserver(instance)
                val builder: AuthenticationRequest.Builder =
                    AuthenticationRequest.Builder(
                        CLIENT_ID,
                        AuthenticationResponse.Type.TOKEN,
                        REDIRECT_URI
                    )

                builder.setScopes(arrayOf("streaming"))
                val request: AuthenticationRequest = builder.build()
                AuthenticationClient.openLoginActivity(
                    activity,
                    AUTHENTICATION_REQUEST_CODE,
                    request
                )
//                LocalCon
                return CompletableDeferred()
            }
        }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (requestCode == AUTHENTICATION_REQUEST_CODE) {
                val response = AuthenticationClient.getResponse(resultCode, data);

                when (response.type) {
                    AuthenticationResponse.Type.TOKEN ->{
                        requestHeaders.accessToken = response.accessToken
                        INSTANCE?.complete(this)
                        Log.e(TAG, "Spotify Authentication successful: ${response.accessToken}")
                    }

                    AuthenticationResponse.Type.ERROR ->
                        Log.e(TAG, "Spotify Authentication failed: ${response.error}")
                    else ->
                        Log.e(
                            TAG,
                            "Spotify Authentication did not complete. Response info: ${response.state}"
                        )
                }
            }

        }

        suspend fun search(searchString: String) = spotifyServices.search(searchString)
    }
}