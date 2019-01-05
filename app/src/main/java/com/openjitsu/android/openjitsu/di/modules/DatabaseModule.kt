package com.openjitsu.android.openjitsu.di.modules

import android.content.Context
import androidx.room.Room
import com.openjitsu.android.openjitsu.data.db.Dao
import com.openjitsu.android.openjitsu.data.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database {
        // TODO: create a util/constants.kt class with the db name instead of hardcoding it
        return Room.databaseBuilder(context.applicationContext, Database::class.java, "openjitsu").build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: Database): Dao {
        return db.dao()
    }
}