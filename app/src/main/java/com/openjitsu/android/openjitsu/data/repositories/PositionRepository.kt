package com.openjitsu.android.openjitsu.data.repositories

import android.util.Log
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.data.db.Dao
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.util.NoConnectivityException
import javax.inject.Inject

class PositionRepository @Inject constructor(private val api: Api) {

    @Inject
    lateinit var dao: Dao

    init {
        Application.appComponent.inject(this)
    }

    // @SuppressLint("CheckResult")
    suspend fun getAllPositions(): List<Position> {
        return try {
            Log.i("openjitsu/repo", "Attempting to get from Positions Repository...")
            val items = api.getAllPositions().await()
            dao.insertPositions(items)
            items
        } catch (e: NoConnectivityException) {
            Log.i("openjitsu/repo", "No network connectivity, returning saved positions")
            dao.getAllPositions()
        }
    }
}