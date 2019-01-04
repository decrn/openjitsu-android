package com.openjitsu.android.openjitsu.di.modules

import android.util.Log
import com.openjitsu.android.openjitsu.data.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        Log.i("openjitsu/di", "Injecting Api...")
        return retrofit.create(Api::class.java)
    }
}
