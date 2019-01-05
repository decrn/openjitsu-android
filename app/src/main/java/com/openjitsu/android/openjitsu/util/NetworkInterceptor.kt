package com.openjitsu.android.openjitsu.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i("openjitsu/interceptor", "intercept network status")
        if (!isOnline()) {
            Log.i("openjitsu/interceptor", "is offline")
            throw NoConnectivityException()
        }
        Log.i("openjitsu/interceptor", "is online")
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

class NoConnectivityException : IOException()