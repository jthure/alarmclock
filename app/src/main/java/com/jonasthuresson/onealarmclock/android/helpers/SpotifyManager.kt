package com.jonasthuresson.onealarmclock.android.helpers

import android.content.Context
import android.util.Log
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Empty
import com.spotify.protocol.types.ListItems
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class SpotifyManager() {
    private lateinit var defferSpotifyAppRemote: Deferred<SpotifyAppRemote>
    suspend fun play(){
        appRemote()?.playerApi?.play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
    }
    suspend fun stop(){
        appRemote()?.playerApi?.pause()
    }

    suspend fun search(){
        val x = appRemote()?.call<ListItems, Empty>("browse/featured-playlists", Empty(), ListItems::class.java)
        Log.d("AppRemote", x.toString())
        x?.setResultCallback { Log.d("AppRemote", "Success: " +it.toString()) }
        x?.setErrorCallback { Log.d("AppRemote", "Error: " + it.toString()) }
    }


    private suspend fun appRemote(): SpotifyAppRemote? {
        defferSpotifyAppRemote.invokeOnCompletion {  }
        if (defferSpotifyAppRemote.isActive) defferSpotifyAppRemote.await()
        if (!defferSpotifyAppRemote.isCompleted) return null
        return defferSpotifyAppRemote.getCompleted()

    }
    companion object{
        private const val CLIENT_ID = "7310d321ef974502973b1808e37bbc14"
        private const val REDIRECT_URI = "http://onealarmclock.jonasthuresson.com/spotify/auth"

        fun newInstance(context: Context): SpotifyManager{
            val x = SpotifyManager()
            val y = CompletableDeferred<SpotifyAppRemote>()
            x.defferSpotifyAppRemote = y
            val connectionParams = ConnectionParams.Builder(CLIENT_ID)
                .setRedirectUri(REDIRECT_URI)
                .showAuthView(true)
                .build()
            SpotifyAppRemote.connect(context, connectionParams,
                object : Connector.ConnectionListener {
                    override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                        y.complete(spotifyAppRemote)
                        Log.d("MainActivity", "Connected! Yay!")
                    }

                    override fun onFailure(throwable: Throwable) {
                        Log.e("MainActivity", throwable.message, throwable)
                        y.completeExceptionally(throwable)
                        // Something went wrong when attempting to connect! Handle errors here
                    }
                })

            val builder: AuthenticationRequest.Builder =
                AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

            builder.setScopes(arrayOf("streaming"))
            val request: AuthenticationRequest = builder.build()

            //AuthenticationClient.openLoginActivity(this, 123, request)

            return x
        }
    }
}