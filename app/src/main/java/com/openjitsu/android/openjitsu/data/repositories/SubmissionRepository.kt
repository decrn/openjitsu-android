package com.openjitsu.android.openjitsu.data.repositories

import android.content.SharedPreferences
import android.util.Log
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.data.db.Dao
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.models.Submission
import com.openjitsu.android.openjitsu.util.NoConnectivityException
import javax.inject.Inject

class SubmissionRepository @Inject constructor(private val api: Api) {

    @Inject
    lateinit var dao: Dao

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        Application.appComponent.inject(this)
    }

    // @SuppressLint("CheckResult")
    suspend fun getAllSubmissions(): List<Submission> {
        return try {
            Log.i("openjitsu/repo", "Attempting to get from Submissions Repository...")
            val items = api.getAllSubmissions().await()
            if(sharedPreferences.getBoolean("submissions_db_inserted", true)) {
                dao.insertSubmissions(items)
                sharedPreferences.edit().putBoolean("", false).apply()
            }
            items
        } catch (e: NoConnectivityException) {
            Log.i("openjitsu/repo", "No network connectivity, returning saved submissions")
            dao.getAllSubmissions()
        }
    }
}