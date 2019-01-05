package com.openjitsu.android.openjitsu.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.openjitsu.android.openjitsu.data.db.entity.PositionEntity
import com.openjitsu.android.openjitsu.data.db.entity.SubmissionEntity

@Database(
        entities = [PositionEntity::class, SubmissionEntity::class],
        version = 1,
        exportSchema = false
)
abstract class Database: RoomDatabase() {

    abstract fun dao(): Dao
}
