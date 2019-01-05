package com.openjitsu.android.openjitsu.data.db

import androidx.room.Dao
import androidx.room.Query
import com.openjitsu.android.openjitsu.data.db.entity.PositionEntity
import com.openjitsu.android.openjitsu.data.db.entity.SubmissionEntity

@Dao
interface Dao {
    @Query("select * from positions")
    fun getAllPositions(): List<PositionEntity>

    @Query("select * from submissions")
    fun getAllSubmissions(): List<SubmissionEntity>
}
