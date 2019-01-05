package com.openjitsu.android.openjitsu.data.repositories

import android.annotation.SuppressLint
import android.util.Log
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.data.db.Dao
import com.openjitsu.android.openjitsu.data.db.entity.PositionEntity
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.network.response.Position
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
            Log.i("openjitsu/repository", "Attempting to get from Positions Repository...")
            val items = api.getAllPositions().await()
            dao.insertPositions(items.map { e -> PositionEntity(e.id, e.name, e.description, e.image, e.content) })
            items
        } catch (e: NoConnectivityException) {
            Log.i("openjitsu/repository", "No network connectivity, returning saved positions")
            dao.getAllPositions().map { e -> Position(e.id, e.name, e.description, e.image, e.content) }
        }
    }
}