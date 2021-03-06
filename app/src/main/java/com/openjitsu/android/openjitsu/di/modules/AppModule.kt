package com.openjitsu.android.openjitsu.di.modules

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("openjitsu", Activity.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideMainCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main)
    }


}