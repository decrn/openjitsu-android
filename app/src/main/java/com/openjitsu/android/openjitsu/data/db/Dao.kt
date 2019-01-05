package com.openjitsu.android.openjitsu.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.data.models.Submission

@Dao
interface Dao {
    @Query("select * from positions")
    fun getAllPositions(): List<Position>

    // Update db with newest data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPositions(items: List<Position>)

    @Query("select * from submissions")
    fun getAllSubmissions(): List<Submission>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubmissions(items: List<Submission>) {

    }
}
