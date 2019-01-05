package com.openjitsu.android.openjitsu.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "submissions")
data class SubmissionEntity(
        @PrimaryKey val id: String,
        val name: String,
        val description: String,
        val image: String,
        val content: String
)
