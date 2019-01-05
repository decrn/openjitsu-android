package com.openjitsu.android.openjitsu.di.modules

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.openjitsu.android.openjitsu.util.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ApiModule::class])
class NetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOfflineInterceptor(context: Context): NetworkInterceptor {
        return NetworkInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideOKhttpClient(logger: HttpLoggingInterceptor, offlineInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(offlineInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideRxJava(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: GsonConverterFactory, okHttpClient: OkHttpClient, rxJava: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gson)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }
}
