package com.openjitsu.android.openjitsu.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.openjitsu.android.openjitsu.data.db.Dao
import com.openjitsu.android.openjitsu.data.network.Api
import javax.inject.Inject

abstract class Repository {

        @Inject lateinit var api: Api

        @Inject lateinit var dao: Dao

        @Inject lateinit var context: Context

        @Inject lateinit var sharedPreferences: SharedPreferences
}