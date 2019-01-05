package com.openjitsu.android.openjitsu.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.data.models.Submission

@Database(
        entities = [Position::class, Submission::class],
        version = 1,
        exportSchema = false
)
abstract class Database: RoomDatabase() {

    abstract fun dao(): Dao
}
