package com.jonasthuresson.onealarmclock.android.helpers

import android.content.Intent
import android.util.Log
import com.jonasthuresson.onealarmclock.TAG
import com.jonasthuresson.onealarmclock.android.MainActivity
import com.jonasthuresson.onealarmclock.data.SpotifyRepo
import com.jonasthuresson.onealarmclock.toActivityResultCode
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject


class SpotifyAccessTokenRetrieverImpl @Inject constructor(private val activity: MainActivity) :
    SpotifyRepo.SpotifyAccessTokenRetriever,
    MainActivity.ActivityResultObserver {
    companion object {
        private val AUTHENTICATION_REQUEST_CODE = "spotify_authentication".toActivityResultCode()
        private const val CLIENT_ID = "7310d321ef974502973b1808e37bbc14"
        private const val REDIRECT_URI = "http://onealarmclock.jonasthuresson.com/spotify/auth"
    }

    private val deferredAccessToken = CompletableDeferred<String>()
    private val deferredAppRemote = CompletableDeferred<SpotifyAppRemote>()

    override suspend fun getAccessToken(): String {
        loginWebApi()
        return deferredAccessToken.await()
    }

    override suspend fun getAppRemote(): SpotifyAppRemote {
        loginAndroid()
        return deferredAppRemote.await()
    }

    private fun loginWebApi() {
        activity.addActivityResultObserver(this)
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
    }

    private fun loginAndroid(){
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(activity, connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    deferredAppRemote.complete(spotifyAppRemote)
                    Log.d(TAG, "Spotify AppRemote retrieval successful")

                }

                override fun onFailure(throwable: Throwable) {
                    Log.e(TAG, "Spotify AppRemote retrieval failed: ${throwable.message}", throwable)
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTHENTICATION_REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, data)

            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    deferredAccessToken.complete(response.accessToken)
                    Log.d(TAG, "Spotify Authentication successful: ${response.accessToken}")
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
}