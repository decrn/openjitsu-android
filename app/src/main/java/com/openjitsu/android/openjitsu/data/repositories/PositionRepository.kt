package com.openjitsu.android.openjitsu.data.repositories

import android.util.Log
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.util.NoConnectivityException
import javax.inject.Inject

class PositionRepository: Repository() {

    init {
        Application.appComponent.inject(this)
    }

    suspend fun getAllPositions(): List<Position> {
        return try {
            Log.i("openjitsu/repo", "Attempting to get from Positions Repository...")
            val items = api.getAllPositions().await()
            if(sharedPreferences.getBoolean("submissions_db_inserted", true)) {
                dao.insertPositions(items)
                sharedPreferences.edit().putBoolean("", false).apply()
            }
            items
        } catch (e: NoConnectivityException) {
            Log.i("openjitsu/repo", "No network connectivity, returning saved positions")
            dao.getAllPositions()
        }
    }

    suspend fun getPositionById(id: String): Position {
        return try {
            Log.i("openjitsu/repo", "Attempting to get item id: '$id' from Positions Repository...")
            val item = api.getPositionById(id).await()
            item
        } catch (e: NoConnectivityException) {
            Log.i("openjitsu/repo", "No network connectivity, returning saved positions")
            dao.getPositionById(id)
        }
    }
}