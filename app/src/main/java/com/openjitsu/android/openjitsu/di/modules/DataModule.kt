package com.openjitsu.android.openjitsu.di.modules

import android.content.Context
import androidx.room.Room
import com.openjitsu.android.openjitsu.data.db.Dao
import com.openjitsu.android.openjitsu.data.db.Database
import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.repositories.PositionRepository
import com.openjitsu.android.openjitsu.data.repositories.SubmissionRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providePositionRepository(): PositionRepository {
        return PositionRepository()
    }

    @Singleton
    @Provides
    fun provideSubmissionRepository(): SubmissionRepository {
        return SubmissionRepository()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context.applicationContext, Database::class.java, "openjitsu").build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: Database): Dao {
        return db.dao()
    }
}