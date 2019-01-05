package com.openjitsu.android.openjitsu.data.network

import com.openjitsu.android.openjitsu.data.network.response.Position
import com.openjitsu.android.openjitsu.data.network.response.Submission
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path



interface Api {

    @GET("positions")
    fun getAllPositions(): Single<List<Position>>

    // TODO: Update this _ to slash when we use an actual api and not github gists...
    @GET("position_{id}")
    fun getPositionById(@Path("id") id: String): Single<Position>

    @GET("submissions")
    fun getAllSubmissions(): Single<List<Submission>>
}
