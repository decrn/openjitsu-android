package com.openjitsu.android.openjitsu.data

import com.openjitsu.android.openjitsu.models.Position
import com.openjitsu.android.openjitsu.models.Submission
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import okhttp3.logging.HttpLoggingInterceptor



interface Api {

    @GET("positions")
    fun getAllPositions(): Single<List<Position>>

    // TODO: Update this _ to slash when we use an actual api and not github gists...
    @GET("position_{id}")
    fun getPositionById(@Path("id") id: String): Single<Position>

    @GET("submissions")
    fun getAllSubmissions(): Single<List<Submission>>


    // FACTORY

    companion object Factory {
        val BASE_URL = "https://gist.githubusercontent.com/decrn/9333d4c9fc9bab3084964d053b708561/raw/"
        fun create(): Api {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(Api::class.java)
        }
    }
}