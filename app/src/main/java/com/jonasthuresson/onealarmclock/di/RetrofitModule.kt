package com.jonasthuresson.onealarmclock.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Module
class RetrofitModule {
    private val BASE_URL = "https://api.spotify.com"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideRequestHeaders(): RequestHeaders {
        return RequestHeaders(null, "application/json")
    }

    @Provides
    @Singleton
    fun providesRequestInterceptor(requestHeaders: RequestHeaders): RequestInterceptor {
        return RequestInterceptor(requestHeaders)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient.Builder,
        requestInterceptor: RequestInterceptor?
    ): Retrofit {
        //add logger
        val logging: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        requestInterceptor?.let { httpClient.addInterceptor(it) }

        //add retro builder
        val retroBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        retroBuilder.client(httpClient.build())

        //create retrofit - only this instance would be used in the entire application
        return retroBuilder.build()
    }

    // API Services
    @Provides
    @Singleton
    fun provideSpotifyServices(
        retrofit: Retrofit
    ): SpotifyServices {
        return retrofit.create(SpotifyServices::class.java)
    }
}

interface SpotifyServices {
    @GET("/v1/search")
    suspend fun search(
        @Query("q") searchString: String,
        @Query("type") type: String = "track",
        @Query("market") market: String = "SE",
        @Query("limit") limit: Int = 10
    ): SpotifySearhResponse
}

data class SpotifySearhResponse(val tracks: Tracks) {
    data class Tracks(val items: List<Item>)
    data class Item(val name: String)
}
//data class Tracks(val items: List<Item>)
//data class Item(val name: String)

class RequestInterceptor(private val requestHeaders: RequestHeaders) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
            .header("Authorization", "Bearer " + requestHeaders.accessToken)
            .header("Content-Type", requestHeaders.contentType)
//            .method(original.method(), original.body())
        val newRequest: Request = builder.build()

        return chain.proceed(newRequest)
    }

}

data class RequestHeaders(var accessToken: String?, var contentType: String)
