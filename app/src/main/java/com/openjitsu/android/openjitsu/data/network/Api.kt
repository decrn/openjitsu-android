package com.openjitsu.android.openjitsu.data.network

import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.data.models.Submission
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path



interface Api {

    @GET("positions")
    fun getAllPositions(): Deferred<List<Position>>

    // TODO: Update this _ to slash when we use an actual api and not github gists...
    @GET("position_{id}")
    fun getPositionById(@Path("id") id: String): Deferred<Position>

    @GET("submissions")
    fun getAllSubmissions(): Deferred<List<Submission>>
}
